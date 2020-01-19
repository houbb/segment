package com.github.houbb.segment.support.segment.result.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;

import java.util.List;

/**
 * 默认分词结果处理
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class SegmentResultHandler implements ISegmentResultHandler<List<ISegmentResult>> {

    @Override
    public List<ISegmentResult> handler(List<ISegmentResult> resultList) {
        return resultList;
    }

}
