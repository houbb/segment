package com.github.houbb.segment.constant;

/**
 * 分词常量
 * @author binbin.hou
 * @since 0.1.4
 */
public final class SegmentPosTaggingConst {

    private SegmentPosTaggingConst(){}

    /**
     * 分词的词性标注系统字典
     * @since 0.1.4
     */
    public static final String SEGMENT_POS_TAG_SYSTEM_PATH = "/segment_pos_tagging_system.txt";

    /**
     * 分词的词性标注系统字典
     * @since 0.1.4
     */
    public static final String SEGMENT_POS_TAG_DEFINE_PATH = "/segment_pos_tagging_define.txt";

    /**
     * 分词的自定义字典路径
     * @since 0.1.4
     */
    public static final String SEGMENT_POS_TAG_PROB_PATH = "/segment_pos_tagging_prob.txt";

    /**
     * 默认的词数统计
     * @since 0.1.4
     */
    public static final int DEFAULT_TAG_COUNT = 1;

    /**
     * 默认的词性
     * @since 0.1.4
     */
    public static final String DEFAULT_POS = "un";

}
