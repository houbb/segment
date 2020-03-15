package com.github.houbb.segment.model;

/**
 * 单词明细信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class SegmentWordEntry {

    /**
     * 单词
     * @since 0.0.1
     */
    private String word;

    /**
     * 次数
     * @since 0.0.1
     */
    private long count;

    /**
     * 词性
     * @since 0.0.1
     */
    private String type;

    /**
     * 新建一个实例
     * @return 实例
     * @since 0.0.1
     */
    public static SegmentWordEntry newInstance() {
        return new SegmentWordEntry();
    }

    public String word() {
        return word;
    }

    public SegmentWordEntry word(String word) {
        this.word = word;
        return this;
    }

    public long count() {
        return count;
    }

    public SegmentWordEntry count(long count) {
        this.count = count;
        return this;
    }

    public String type() {
        return type;
    }

    public SegmentWordEntry type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "WordEntry{" +
                "word='" + word + '\'' +
                ", count=" + count +
                ", type='" + type + '\'' +
                '}';
    }

}
