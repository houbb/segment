package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.api.ISegment;

/**
 * 分词实现工具类
 * @author binbin.hou
 * @since 0.0.7
 */
public final class Segments {

    private Segments(){}

    /**
     * 默认实现
     * @return 结果
     * @since 0.0.7
     */
    public static ISegment defaults() {
        return Instances.singleton(Segment.class);
    }

    /**
     * 快速分词模式
     * @return 快速分词
     * @since 0.0.7
     */
    public static ISegment fast() {
        return Instances.singleton(FastSegment.class);
    }

    /**
     * 精确分词模式
     * @return 精确分词
     * @since 0.0.7
     */
    public static ISegment precise() {
        return Instances.singleton(PreciseSegment.class);
    }

}
