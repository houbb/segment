package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.format.ISegmentFormat;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.type.ISegmentWordType;

/**
 * 分词上下文
 * @author binbin.hou
 * @since 0.0.1
 */
public class SegmentContext implements ISegmentContext {

    /**
     * 词性
     * @since 0.0.2
     */
    private ISegmentWordType wordType;

    /**
     * 分词字典
     * @since 0.0.3
     */
    private ISegmentData data;

    /**
     * 分词模式
     * @since 0.0.5
     */
    private ISegmentMode mode;

    /**
     * 格式化
     * @since 0.0.9
     */
    private ISegmentFormat format;

    /**
     * 创建对象实例
     * @return 实例
     * @since 0.0.1
     */
    public static SegmentContext newInstance() {
        return new SegmentContext();
    }

    @Override
    public ISegmentWordType wordType() {
        return wordType;
    }

    @Override
    public SegmentContext wordType(ISegmentWordType wordType) {
        this.wordType = wordType;
        return this;
    }

    @Override
    public ISegmentData data() {
        return data;
    }

    public SegmentContext data(ISegmentData data) {
        this.data = data;
        return this;
    }

    @Override
    public ISegmentMode mode() {
        return mode;
    }

    public SegmentContext mode(ISegmentMode mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public ISegmentFormat format() {
        return format;
    }

    public SegmentContext format(ISegmentFormat format) {
        this.format = format;
        return this;
    }
}
