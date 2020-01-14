package com.github.houbb.segment.support.segment.strategy;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 * 分词策略
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentStrategy {

    /**
     * 执行分词的最后结果
     * @param string 原始信息
     * @param startIndex 开始下标
     * @param context 分词上下文
     * @return 分词后的结果
     * @since 0.0.1
     */
    List<ISegmentResult> segment(final String string,
                                 final int startIndex,
                                 final ISegmentContext context);

}
