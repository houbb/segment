package com.github.houbb.segment.support.segment;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.type.IWordType;

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
    private IWordType wordType;

    /**
     * 分词字典
     * @since 0.0.3
     */
    private ISegmentData segmentData;

    /**
     * 分词模式
     * @since 0.0.5
     */
    private ISegmentMode segmentMode;

    /**
     * 创建对象实例
     * @return 实例
     * @since 0.0.1
     */
    public static SegmentContext newInstance() {
        return new SegmentContext();
    }

    @Override
    public IWordType wordType() {
        return wordType;
    }

    @Override
    public SegmentContext wordType(IWordType wordType) {
        this.wordType = wordType;
        return this;
    }

    @Override
    public ISegmentData segmentData() {
        return segmentData;
    }

    public SegmentContext segmentData(ISegmentData segmentData) {
        this.segmentData = segmentData;
        return this;
    }

    @Override
    public ISegmentMode segmentMode() {
        return segmentMode;
    }

    public SegmentContext segmentMode(ISegmentMode segmentMode) {
        this.segmentMode = segmentMode;
        return this;
    }

}
