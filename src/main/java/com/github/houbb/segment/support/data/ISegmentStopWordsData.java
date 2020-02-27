package com.github.houbb.segment.support.data;

/**
 * 分词停止词数据接口
 * @author binbin.hou
 * @since 0.1.3
 */
public interface ISegmentStopWordsData {

    /**
     * 是否包含指定的词
     * @param word 词语
     * @return 是否包含
     * @since 0.1.3
     */
    boolean contains(final String word);

}
