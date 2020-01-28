package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

import java.util.List;

/**
 * 动态规划-最大概率匹配
 *
 * 1. 选择动态规划概率最大的一个。
 * 2. 这个列表是排序过的，默认直接选择最后一个即可。
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class DefaultSegmentMode extends AbstractSegmentMode {

    @Override
    public boolean isFastMode() {
        return false;
    }

    @Override
    protected List<ISegmentResult> doSelect(final SegmentModeContext segmentModeContext) {
        return null;
    }

}
