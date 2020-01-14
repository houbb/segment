package com.github.houbb.segment.api;

/**
 * 分词接口结果
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentResult extends Comparable<ISegmentResult> {

    /**
     * 单个词
     * @return 词
     * @since 0.0.1
     */
    String word();

    /**
     * 开始下标
     * @return index
     * @since 0.0.1
     */
    int startIndex();

    /**
     * 结束下标
     * @return index
     * @since 0.0.1
     */
    int endIndex();

}
