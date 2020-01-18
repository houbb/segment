package com.github.houbb.segment.constant;

/**
 * 分词常量
 * @author binbin.hou
 * @since 0.0.1
 */
public final class SegmentConst {

    private SegmentConst(){}

    /**
     * 分词的字典路径
     * @since 0.0.1
     */
    public static final String SEGMENT_DICT_PATH = "/segment_dict.txt";

    /**
     * 分词的自定义字典路径
     * @since 0.0.3
     */
    public static final String SEGMENT_DEFINE_DICT_PATH = "/segment_define_dict.txt";

    /**
     * 字典分隔类
     * @since 0.0.1
     */
    public static final String SEGMENT_DICT_SPLITTER = " ";

    /**
     * 默认的词数统计
     * @since 0.0.1
     */
    public static final int DEFAULT_WORD_COUNT = 3;

    /**
     * 默认的词性
     * @since 0.0.1
     */
    public static final String DEFAULT_WORD_TYPE = "un";

    /**
     * 是否为结束的标识
     * @since 0.0.1
     */
    public static final String IS_END = "ED";

}
