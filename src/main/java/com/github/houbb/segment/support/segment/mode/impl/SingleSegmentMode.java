package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.SegmentResult;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 单个字结果匹配
 *
 * 1. 直接忽略原来的结果
 * 2. 默认直接返回单个结果
 *
 * 词性的获取时另外的方法，暂时不提供支持。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SingleSegmentMode implements ISegmentMode {

    /**
     * 直接构建结果列表
     * @param string 当前字符串
     * @param startIndex 开始下标
     * @param resultList 结果列表
     * @return 最佳匹配结果
     * @since 0.0.1
     */
    @Override
    public List<ISegmentResult> select(final String string,
                                final int startIndex,
                                final List<ISegmentResult> resultList) {
        String word = string.substring(startIndex, startIndex+1);
        ISegmentResult segmentResult = SegmentResult.newInstance()
                .word(word)
                .startIndex(startIndex)
                .endIndex(startIndex+1);

        return Collections.singletonList(segmentResult);
    }

}
