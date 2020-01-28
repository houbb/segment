package com.github.houbb.segment.support.segment.strategy.impl;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;

import java.util.List;

/**
 * 抽象分词策略
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public abstract class AbstractSegmentStrategy implements ISegmentStrategy {

    /**
     * 执行分词
     * @param string 字符串
     * @param startIndex 开始下标
     * @param context 上下文
     * @return 结果
     * @since 0.0.7
     */
    protected abstract List<ISegmentResult> doSegment(final String string,
                                             final int startIndex,
                                             final ISegmentContext context);

    @Override
    public List<ISegmentResult> segment(final String string,
                                        final int startIndex,
                                        final ISegmentContext context) {
        List<ISegmentResult> segmentResultList = doSegment(string, startIndex, context);

        // 去重
        return CollectionUtil.distinct(segmentResultList);
    }

}
