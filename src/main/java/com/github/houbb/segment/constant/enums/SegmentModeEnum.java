package com.github.houbb.segment.constant.enums;

/**
 * 分词模式枚举
 *
 * <p> project: segment-SegmentModeEnum </p>
 * <p> create on 2020/1/19 23:21 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
public enum SegmentModeEnum {

    /**
     * 贪婪模式
     *
     * 返回最长的匹配文本
     *
     * ps: 我们将贪婪模式作为默认模式，因为这个的性能较好，一般也够用。
     *
     * @since 0.0.5
     */
    GREEDY,

    /**
     * 全分词模式
     *
     * 返回所有匹配的分词
     *
     * @since 0.0.5
     */
    ALL,

//    /**
//     * 精准模式
//     *
//     * 基于词频统计计算，暂时不支持。
//     */
//    PRECISE,
//
//    /**
//     * 搜索引擎模式
//     *
//     * 基于【精准模式】之后，对长词进行再一次的分词。提高召回率，适合用于搜索引擎分词。
//     *
//     * 暂时不支持。
//     */
//    SEARCH,

    ;

}
