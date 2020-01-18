package com.github.houbb.segment.support.type;

import com.github.houbb.segment.api.ISegmentContext;

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
     * @param context 上下文
     * @return 词性
     * @since 0.0.2
     */
    String getWordType(final String word,
                       final ISegmentContext context);

}
