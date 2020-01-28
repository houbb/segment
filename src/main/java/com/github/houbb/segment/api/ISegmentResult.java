package com.github.houbb.segment.api;

/**
 * 分词接口结果
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentResult {

    /**
     * 单个词
     * @return 词
     * @since 0.0.1
     */
    String word();

    /**
     * 设置词信息
     * @param word 词信息
     * @return this
     * @since 0.0.7
     */
    ISegmentResult word(final String word);

    /**
     * 开始下标
     * @return index
     * @since 0.0.1
     */
    int startIndex();

    /**
     * 开始下标
     * @param startIndex 开始下标
     * @return this
     * @since 0.0.7
     */
    ISegmentResult startIndex(final int startIndex);

    /**
     * 结束下标
     * @return index
     * @since 0.0.1
     */
    int endIndex();

    /**
     * 结束下标
     * @param endIndex 结束下标
     * @return this
     * @since 0.0.7
     */
    ISegmentResult endIndex(final int endIndex);

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
