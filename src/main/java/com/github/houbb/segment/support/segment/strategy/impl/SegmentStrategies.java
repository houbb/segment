package com.github.houbb.segment.support.segment.strategy.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.hmm.HmmSegmentStrategy;

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

    /**
     * HMM 实现
     * @return HMM 实现
     * @since 0.1.0
     */
    public static ISegmentStrategy hmm() {
        return Instances.singleton(HmmSegmentStrategy.class);
    }

    /**
     * 简单的分词实现
     * @return 简单的分词实现
     * @since 0.1.1
     */
    public static ISegmentStrategy simple() {
        return Instances.singleton(SimpleSegmentStrategy.class);
    }

}
