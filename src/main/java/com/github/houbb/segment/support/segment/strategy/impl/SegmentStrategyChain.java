package com.github.houbb.segment.support.segment.strategy.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;

import java.util.Collections;
import java.util.List;

/**
 * 基于前缀树（DFA）的分词策略
 *
 * （1）如果没有匹配到关键词，则直接返回空列表。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SegmentStrategyChain implements ISegmentStrategy {

    @Override
    public List<ISegmentResult> segment(String string, int startIndex, ISegmentContext context) {
        List<ISegmentStrategy> strategyList = Guavas.newArrayList();
        strategyList.add(Instances.singleton(TireTreeSegmentStrategy.class));
        // 后期可以根据配置添加其他的各种策略
        // 这里获得了灵活性，但是损失了一点点性能。
        // 因为每次这个责任链的构建，都需要耗时
        List<ISegmentResult> resultList = Guavas.newArrayList();
        for(ISegmentStrategy segmentStrategy : strategyList) {
            resultList.addAll(segmentStrategy.segment(string, startIndex, context));
        }

        // 排序且去重
        CollectionUtil.distinct(resultList);
        Collections.sort(resultList);
        return resultList;
    }

}
