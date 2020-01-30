package com.github.houbb.segment.support.type.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.type.ISegmentWordType;

/**
 * 无词性
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class NoneSegmentWordType implements ISegmentWordType {

    @Override
    public String getWordType(final String word, final ISegmentContext context) {
        return StringUtil.EMPTY;
    }

}
