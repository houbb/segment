package com.github.houbb.segment.api;

import java.util.List;

/**
 * 分词接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegment {

    /**
     * 执行分词的最后结果
     * @param string 原始信息
     * @param context 分词上下文
     * @return 分词后的结果
     * @since 0.0.1
     */
    List<ISegmentResult> segment(final String string,
                                 final ISegmentContext context);

}
