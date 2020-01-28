package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategies;
import com.github.houbb.segment.util.CharUtils;

import java.util.List;
import java.util.Map;

/**
 * 精确分词
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class PreciseSegment implements ISegment {

    /**
     * 精确分词模式
     * （1）基于动态规划的频率
     * （2）基于 HMM 的新词预测
     * <p>
     * 这里要按照字符串的类型进行中断，避免句子过长。
     * <p>
     * 1. 中文
     * 2. 英文
     * 3. 数字
     * 4. 连接符
     * 其他的符号直接中断。
     *
     * @param string  字符串
     * @param context 上下文
     * @return 分词结果列表
     * @since 0.0.7
     */
    @Override
    public List<ISegmentResult> segment(String string, ISegmentContext context) {
        List<ISegmentResult> segmentList = Guavas.newArrayList();

        // 构建所有的分词结果
        final int length = string.length();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // TODO: 这里需要一个字符的格式化
            char c = string.charAt(i);
            if (CharUtils.isConsequent(c)) {
                // 如果是连续的，则加入
                stringBuilder.append(c);
            } else {
                // 连续的信息
                int consequentLen = stringBuilder.length();
                // 如果包含连续的信息，则优先处理连续信息
                // 这里的长度也很有趣，比如如果只有 1 个，实际上不需要处理。
                // 但是我们为了接口处理的统一，同时为1的概率也很低，此处把1当做特殊的场景处理。
                if (consequentLen > 0) {
                    int startIndex = i - consequentLen;
                    segmentList.addAll(consequentStrSegment(stringBuilder, startIndex, context));
                }

                // 非连续的符号，简单起见，全部当做单个字符处理。
                segmentList.addAll(singleCharSegment(string, i));
            }
        }


        // 最后的结果处理
        int consequentLen = stringBuilder.length();
        if (consequentLen > 0) {
            int startIndex = length - consequentLen;
            segmentList.addAll(consequentStrSegment(stringBuilder, startIndex, context));
        }

        return segmentList;
    }


    /**
     * 连续信息的分词
     * <p>
     * 最后的结果 index = startIndex + offset
     *
     * @param stringBuilder 连续的字符串
     * @param startIndex    开始下标
     * @param context       上下文
     * @return 结果列表
     * @since 0.0.1
     */
    private List<ISegmentResult> consequentStrSegment(final StringBuilder stringBuilder,
                                                      final int startIndex,
                                                      final ISegmentContext context) {

        //1. 所有的 dict 分词结果
        final String text = stringBuilder.toString();
        Map<Integer, List<ISegmentResult>> segmentMap = getSegmentMap(text, context);

        //2. 概率 routeMap
        Map<Integer, Pair<Integer, Double>> routeMap = calcRouteMap(segmentMap, context);

        // 先死后活，首先代码写死，然后进行处理。
        //3. 循环处理
        // 这里需要考虑下对应的下标问题，相对转换为绝对下标即可。
        List<ISegmentResult> resultList = Guavas.newArrayList();
        for (int i = 0; i < text.length(); i++) {
            int routeIndex = getRouteIndex(routeMap, i);
            String word = text.substring(i, routeIndex);

            ISegmentResult result = SegmentResult.newInstance()
                    .word(word)
                    .startIndex(startIndex + i)
                    .endIndex(startIndex + routeIndex);
            resultList.add(result);

            // 更新下标
            i = routeIndex - 1;
        }

        // 长度清空
        stringBuilder.setLength(0);
        return resultList;
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
        // 这里后续可以基于 HMM 进行拓展
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
        final ISegmentData segmentData = context.segmentData();
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
     * 单个字符的处理
     *
     * @param string     字符串
     * @param startIndex 开始下标
     * @return 结果
     * @since 0.0.7
     */
    private List<ISegmentResult> singleCharSegment(final String string,
                                                   final int startIndex) {
        SegmentModeContext currentCharContext = SegmentModeContext.newInstance()
                .string(string)
                .startIndex(startIndex);
        return SegmentModes.single().select(currentCharContext);
    }

}
