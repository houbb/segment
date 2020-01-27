package com.github.houbb.segment.support.segment;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.api.ISegmentResult;

import java.util.Objects;

/**
 * 默认分词结果
 * @author binbin.hou
 * @since 0.0.1
 */
public class SegmentResult implements ISegmentResult {

    /**
     * 单词
     * @since 0.0.1
     */
    private String word;

    /**
     * 开始下标
     * @since 0.0.1
     */
    private int startIndex;

    /**
     * 结束下标
     * @since 0.0.1
     */
    private int endIndex;

    @Override
    public String type() {
        return type;
    }

    @Override
    public SegmentResult type(String type) {
        this.type = type;
        return this;
    }

    /**
     * 词性
     * @since 0.0.2
     */
    private String type;

    /**
     * 新建对象实例
     * @return 对象实例
     * @since 0.0.1
     */
    public static SegmentResult newInstance() {
        return new SegmentResult();
    }

    @Override
    public String word() {
        return word;
    }

    @Override
    public SegmentResult word(String word) {
        this.word = word;
        return this;
    }

    @Override
    public int startIndex() {
        return startIndex;
    }

    @Override
    public SegmentResult startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    @Override
    public int endIndex() {
        return endIndex;
    }

    @Override
    public SegmentResult endIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    @Override
    public int compareTo(ISegmentResult o) {
        return endIndex - o.endIndex();
    }

    /**
     * 对于特定句子的分词而言，开始+结束就可以唯一确定对应的信息
     * @param o 元素
     * @return 是否相等
     * @since 0.0.1
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentResult that = (SegmentResult) o;
        return startIndex == that.startIndex &&
                endIndex == that.endIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIndex, endIndex);
    }

    @Override
    public String toString() {
        if(StringUtil.isEmpty(type)) {
            return word+"["+startIndex+","+endIndex+")";
        }

        return word+"["+startIndex+","+endIndex+")/"+type;
    }

}
