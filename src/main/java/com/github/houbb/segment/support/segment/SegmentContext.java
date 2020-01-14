package com.github.houbb.segment.support.segment;

import com.github.houbb.segment.api.ISegmentContext;

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
    private boolean wordType;

    /**
     * 创建对象实例
     * @return 实例
     * @since 0.0.1
     */
    public static SegmentContext newInstance() {
        return new SegmentContext();
    }

    @Override
    public boolean wordType() {
        return wordType;
    }

    @Override
    public SegmentContext wordType(boolean wordType) {
        this.wordType = wordType;
        return this;
    }

}
