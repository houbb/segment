package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

import java.util.Collections;
import java.util.List;

/**
 *
 * 贪心最大长度匹配
 *
 * 1. 选择长度最长 的一个。
 * 2. 这个列表是排序过的，默认直接选择最后一个即可。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class MaxLengthMode extends AbstractSegmentMode {

    @Override
    protected List<ISegmentResult> doSelect(final SegmentModeContext context) {
        final List<ISegmentResult> resultList = context.resultList();

        // 直接选择最后一个
        ISegmentResult lastResult = resultList.get(resultList.size()-1);
        return Collections.singletonList(lastResult);
    }

}
