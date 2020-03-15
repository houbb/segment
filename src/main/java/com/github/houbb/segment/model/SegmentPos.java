package com.github.houbb.segment.model;

/**
 * 单词属性信息
 * @author binbin.hou
 * @since 0.1.4
 */
public class SegmentPos {

    /**
     * 次数
     * @since 0.0.2
     */
    private long count;

    /**
     * 词性
     * @since 0.0.2
     */
    private String pos;

    /**
     * 新建一个实例
     * @param pos 词性
     * @param count 词频
     * @return 实例
     * @since 0.0.2
     */
    public static SegmentPos of(final String pos,
                                final long count) {
        SegmentPos segmentPos = new SegmentPos();
        segmentPos.count(count);
        segmentPos.pos(pos);
        return segmentPos;
    }

    public long count() {
        return count;
    }

    public SegmentPos count(long count) {
        this.count = count;
        return this;
    }

    public String pos() {
        return pos;
    }

    public SegmentPos pos(String pos) {
        this.pos = pos;
        return this;
    }

    @Override
    public String toString() {
        return "SegmentPos{" +
                "count=" + count +
                ", pos='" + pos + '\'' +
                '}';
    }

}
