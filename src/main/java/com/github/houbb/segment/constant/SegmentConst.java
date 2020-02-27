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
     * 分词发射概率路径
     * @since 0.1.2
     */
    public static final String SEGMENT_EMIT_PROB_PATH = "/segment_emit_prob.txt";

    /**
     * 分词中文繁体转简体路径
     * @since 0.1.2
     */
    public static final String SEGMENT_CHINESE_TS_CHAR_PATH = "/segment_chinese_ts_char.txt";

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

    /**
     * 停顿词默认文件路径
     * @since 0.1.3
     */
    public static final String SEGMENT_STOP_WORDS_PATH = "/segment_stop_words.txt";

}
