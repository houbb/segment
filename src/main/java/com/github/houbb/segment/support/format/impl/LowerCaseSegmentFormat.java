package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.format.ISegmentFormat;

/**
 * 英文大小写格式化处理
 * @author binbin.hou
 * @since 0.0.9
 */
@ThreadSafe
public class LowerCaseSegmentFormat extends AbstractSegmentFormat {

    @Override
    public char format(char ch, ISegmentContext context) {
        return Character.toLowerCase(ch);
    }

}
