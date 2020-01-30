package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.format.ISegmentFormat;

/**
 * 分词格式工具类
 * @author binbin.hou
 * @since 0.0.9
 */
public final class SegmentFormats {

    private SegmentFormats(){}

    /**
     * 默认格式化实现
     * @return 格式化实现
     * @since 0.0.9
     */
    public static ISegmentFormat defaults() {
        return Instances.singleton(SegmentFormatChain.class);
    }

    /**
     * 无格式化实现
     * @return 格式化实现
     * @since 0.0.9
     */
    public static ISegmentFormat none() {
        return Instances.singleton(NoneSegmentFormat.class);
    }

}
