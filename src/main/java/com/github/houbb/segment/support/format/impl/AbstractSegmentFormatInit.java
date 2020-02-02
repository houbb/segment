package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.format.ISegmentFormat;

import java.util.List;

/**
 * 全角半角格式化处理
 * @author binbin.hou
 * @since 0.1.2
 */
@ThreadSafe
public abstract class AbstractSegmentFormatInit implements ISegmentFormat {

    @Override
    public char format(char ch, ISegmentContext context) {
        Pipeline<ISegmentFormat> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        List<ISegmentFormat> formatList = pipeline.list();

        char result = ch;
        for(ISegmentFormat format : formatList) {
            result = format.format(result, context);
        }

        return result;
    }

    /**
     * 初始化列表
     *
     * @param pipeline     当前列表泳道
     * @since 0.1.2
     */
    protected abstract void init(final Pipeline<ISegmentFormat> pipeline);

}
