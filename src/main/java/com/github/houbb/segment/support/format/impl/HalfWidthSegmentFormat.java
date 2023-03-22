package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.format.ISegmentFormat;

/**
 * 全角半角格式化处理
 * @author binbin.hou
 * @since 0.0.9
 */
@ThreadSafe
public class HalfWidthSegmentFormat extends AbstractSegmentFormat {

    @Override
    public char format(char ch, ISegmentContext context) {
        return CharUtil.toHalfWidth(ch);
    }

}
