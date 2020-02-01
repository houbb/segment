package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.segment.impl.SegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategies;

import java.util.List;
import java.util.Map;

/**
 * 动态规划-最大概率匹配
 *
 * 1. 选择动态规划概率最大的一个。
 * 2. 这个列表是排序过的，默认直接选择最后一个即可。
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class SearchSegmentMode extends AbstractPreciseSegmentMode {

    /**
     * TODO: 这些下标的转换过于复杂，建议简化掉。
     * （1）all index 模式需要结果
     * （2）精确模式其实不需要这些属性，可以从后往前递推。
     * @param segmentModeContext 分词模式上下文
     * @return 结果列表
     */
    @Override
    public List<ISegmentResult> select(final SegmentModeContext segmentModeContext) {
        //1. 基本属性
        final ISegmentContext context = segmentModeContext.segmentContext();
        final String text = segmentModeContext.string();
        final int startIndex = segmentModeContext.startIndex();

        //2. 概率 routeMap
        Map<Integer, List<ISegmentResult>> segmentMap = getSegmentMap(text, context);
        Map<Integer, Pair<Integer, Double>> routeMap = calcRouteMap(segmentMap, context);

        //3. 循环处理
        // 这里需要考虑下对应的下标问题，相对转换为绝对下标即可。

        List<ISegmentResult> resultList = Guavas.newArrayList();

        // 存放临时的字符串
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            final int routeIndex = getRouteIndex(routeMap, i);

            // 单词的长度
            int wordLength = routeIndex - i;
            String word = text.substring(i, routeIndex);

            if(wordLength == 1) {
                stringBuffer.append(word);
            } else {
                // 处理前面的 buffer
                final int actualBufferIndex = startIndex+i;
                bufferToResultList(actualBufferIndex, stringBuffer, resultList, context);

                // 如果是词组，则可以直接添加。
                final int actualStartIndex = startIndex+i;
                ISegmentResult result = buildSegmentResult(word, actualStartIndex);
                resultList.add(result);
            }
            // 这里需要引入 HMM 分词
            //1. 如果 word.length==1，则连接起来。比如 good 这种英文单词。
            //2. 如果 dict 包含这个词，则直接返回
            //3. 如果不包含，则进入 HMM 分词处理。

            // 更新下标
            i = routeIndex - 1;
        }

        // 最后的 Buffer 处理。
        if(stringBuffer.length() > 0) {
            final int actualIndex = startIndex + text.length();
            bufferToResultList(actualIndex, stringBuffer, resultList, context);
        }

        return resultList;
    }

    /**
     * buffer 转换为结果列表
     * @param startIndex 开始下标
     * @param stringBuffer buffer
     * @param resultList 结果列表
     * @since 0.1.0
     */
    private void bufferToResultList(final int startIndex,
                        final StringBuilder stringBuffer,
                        final List<ISegmentResult> resultList,
                        final ISegmentContext segmentContext) {
        final int bufferLength = stringBuffer.length();
        if(bufferLength <= 0) {
            return;
        }

        String bufferWord = stringBuffer.toString();
        final int actualStartIndex = startIndex-bufferLength;

        //1. 长度为1，直接添加
        if(bufferWord.length() == 1) {
            resultList.add(buildSegmentResult(bufferWord, actualStartIndex));
        } else {
            //2. 字典中有，直接添加（会出现这个场景吗？）
            final ISegmentData segmentData = segmentContext.data();
            if(segmentData.contains(bufferWord)) {
                resultList.add(buildSegmentResult(bufferWord, actualStartIndex));
            } else {
                //3. 使用 HMM 分词
                ISegmentStrategy segmentStrategy = SegmentStrategies.hmm();
                List<ISegmentResult> segmentResults = segmentStrategy.segment(bufferWord,
                        actualStartIndex, segmentContext);
                resultList.addAll(segmentResults);
            }
        }

        // 清空 buffer
        stringBuffer.setLength(0);
    }

    /**
     * 构建分词结果
     * @param word 词语信息
     * @param startIndex 开始下标
     * @return 结果
     * @since 0.1.0
     */
    private ISegmentResult buildSegmentResult(final String word,
                                              final int startIndex) {

        return SegmentResult.newInstance()
                .word(word)
                .startIndex(startIndex)
                .endIndex(startIndex + word.length());
    }

    /**
     * 获取分词的 Map 结果
     *
     * @param text    字符串
     * @param context 上下文
     * @return 结果
     * @since 0.0.7
     */
    private Map<Integer, List<ISegmentResult>> getSegmentMap(final String text,
                                                             final ISegmentContext context) {
        //1. 所有的可能分词结果
        // startIndex==>list
        final int length = text.length();
        Map<Integer, List<ISegmentResult>> segmentMap = Guavas.newLinkedHashMap(length);

        // 理论上倒排，精确度会更加的高，因为汉语的中心一般在后面。
        final ISegmentStrategy segmentStrategy = SegmentStrategies.tireTree();
        for (int i = 0; i < length; i++) {
            List<ISegmentResult> segmentResults = segmentStrategy.segment(text, i, context);
            segmentMap.put(i, segmentResults);
        }

        return segmentMap;
    }

    /**
     * 计算路由 map
     *
     * @param segmentMap 分词 map
     * @param context    上下文
     * @return 结果
     */
    private Map<Integer, Pair<Integer, Double>> calcRouteMap(final Map<Integer, List<ISegmentResult>> segmentMap, final ISegmentContext context) {
        // 基本信息
        final ISegmentData segmentData = context.data();
        final double minFreq = segmentData.getMinFreq();

        // 初始化
        Map<Integer, Pair<Integer, Double>> routeMap = Guavas.newLinkedHashMap();
        routeMap.put(-1, Pair.of(0, 0.0));

        for (Integer index : segmentMap.keySet()) {
            List<ISegmentResult> segmentResults = segmentMap.get(index);

            // 获取当前字母的频率（动态规划）
            // 当前词的字典频率 + 当前词前一个的频率
            Pair<Integer, Double> candidate = null;
            for (ISegmentResult result : segmentResults) {
                final int endIndex = result.endIndex();

                //DP 的核心公式
                double routeFreq = getRouteFreq(routeMap, index - 1, minFreq);
                double freq = segmentData.getFreq(result.word()) + routeFreq;

                candidate = routeMap.get(index);
                if (ObjectUtil.isNull(candidate)) {
                    // 初始化为空
                    candidate = Pair.of(endIndex, freq);
                } else {
                    // 不为空，且频率比当前的高，则更新
                    if (freq > candidate.getValueTwo()) {
                        candidate = Pair.of(endIndex, freq);
                    }
                }
            }

            // 设置到结果 map 中。
            routeMap.put(index, candidate);
        }

        return routeMap;
    }

    /**
     * 获取前一个频率
     *
     * @param routeMap 路由 map 信息
     * @param index    下标索引
     * @param minFreq  最小的频率
     * @return 结果
     * @since 0.0.7
     */
    private Double getRouteFreq(final Map<Integer, Pair<Integer, Double>> routeMap,
                                final int index,
                                final double minFreq) {
        Pair<Integer, Double> pair = routeMap.get(index);

        if (ObjectUtil.isNotNull(pair)) {
            return pair.getValueTwo();
        }

        // 默认返回最小值
        return minFreq;
    }

    /**
     * 获取路由下标
     * @param routeMap 路由集合
     * @param index 下标
     * @return 结果
     * @since 0.0.7
     */
    private int getRouteIndex(final Map<Integer, Pair<Integer, Double>> routeMap,
                              final int index) {
        Pair<Integer, Double> pair = routeMap.get(index);
        if(ObjectUtil.isNotNull(pair)) {
            return pair.getValueOne();
        }

        return index+1;
    }


}
