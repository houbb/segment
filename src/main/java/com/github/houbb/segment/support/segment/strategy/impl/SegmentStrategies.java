package com.github.houbb.segment.support.segment.strategy.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;

/**
 * 分词策略
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public final class SegmentStrategies {

    private SegmentStrategies(){}

    /**
     * 前缀树
     * @return 前缀树实现
     * @since 0.0.7
     */
    public static ISegmentStrategy tireTree() {
        return Instances.singleton(TireTreeSegmentStrategy.class);
    }

}
