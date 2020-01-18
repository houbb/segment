package com.github.houbb.segment.model;

/**
 * 单词属性信息
 * @author binbin.hou
 * @since 0.0.2
 */
public class WordProperty {

    /**
     * 次数
     * @since 0.0.2
     */
    private long count;

    /**
     * 词性
     * @since 0.0.2
     */
    private String type;

    /**
     * 新建一个实例
     * @param type 词性
     * @param count 词频
     * @return 实例
     * @since 0.0.2
     */
    public static WordProperty of(final long count,
                                  final String type) {
        WordProperty wordProperty = new WordProperty();
        wordProperty.count(count);
        wordProperty.type(type);
        return wordProperty;
    }

    public long count() {
        return count;
    }

    public WordProperty count(long count) {
        this.count = count;
        return this;
    }

    public String type() {
        return type;
    }

    public WordProperty type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "WordProperty{" +
                "count=" + count +
                ", type='" + type + '\'' +
                '}';
    }

}
