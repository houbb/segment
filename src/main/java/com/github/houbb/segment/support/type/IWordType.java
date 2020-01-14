package com.github.houbb.segment.support.type;

/**
 * 词性
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IWordType {

    /**
     * 获取对应的词性
     * （1）如果不存在，则直接返回 "un"
     * @param word 单词
     * @return 词性
     * @since 0.0.2
     */
    String getWordType(final String word);

}
