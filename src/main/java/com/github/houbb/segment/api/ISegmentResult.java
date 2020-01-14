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

    /**
     * 词性
     * @return 词性
     * @since 0.0.2
     */
    String type();

    /**
     * 设置词性
     * @param type 词性
     * @return this
     * @since 0.0.2
     */
    ISegmentResult type(final String type);

}
