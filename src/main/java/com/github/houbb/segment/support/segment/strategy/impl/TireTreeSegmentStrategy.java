package com.github.houbb.segment.support.segment.strategy.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.SegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.tire.impl.SegmentTireTree;

import java.util.List;
import java.util.Map;

/**
 * 基于前缀树（DFA）的分词策略
 *
 * （1）保留所有的匹配词
 * （2）常规的信息是没有任何匹配词，或者只有一个匹配词。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class TireTreeSegmentStrategy implements ISegmentStrategy {

    @Override
    public List<ISegmentResult> segment(String string, int startIndex, ISegmentContext context) {
        Map nowMap = Instances.singleton(SegmentTireTree.class).getTireTree();

        List<ISegmentResult> resultList = Guavas.newArrayList();

        for (int i = startIndex; i < string.length(); i++) {
            // 获取当前的 map 信息
            nowMap = getCurrentMap(nowMap, string, i, context);

            if (ObjectUtil.isNotNull(nowMap)) {
                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                boolean isEnd = SegmentTireTree.isEnd(nowMap);
                if (isEnd) {
                    // 这里存放所有的匹配词，便于后期选择使用。
                    int endIndex = i+1;
                    String word = string.substring(startIndex, endIndex);

                    //TODO: 这里需要添加对应的词性处理。
                    ISegmentResult result = SegmentResult.newInstance()
                            .startIndex(startIndex)
                            .endIndex(endIndex)
                            .word(word);
                    resultList.add(result);
                }
            } else {
                // 直接跳出循环
                break;
            }
        }

        return resultList;
    }

    /**
     * 获取当前的 Map
     * （1）这个方法独立出来，便于后期各种拓展。
     *
     * 比如：
     *
     * 数字格式化
     * 英文格式化
     * 大小写
     * 全角半角
     * 停顿词
     *
     * @param nowMap 原始的当前 map
     * @param txt 文本信息
     * @param index 下标
     * @param context 上下文
     * @return 实际的当前 map
     * @since 0.0.7
     */
    private Map getCurrentMap(Map nowMap,
                              final String txt,
                              final int index,
                              final ISegmentContext context) {
        char mappingChar = txt.charAt(index);

        // 这里做一次重复词的处理
        Map currentMap = (Map) nowMap.get(mappingChar);

        // 启用忽略重复&当前下标不是第一个

        // 这里后期可以添加各种格式化策略
        // 比如字符串的映射
        // 比如忽略重复词等等。

        return currentMap;
    }

}
