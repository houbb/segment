package com.github.houbb.segment.support.normalization;

import com.github.houbb.segment.model.WordEntry;

import java.util.List;

/**
 * 标准化接口
 * @author binbin.hou
 * @since 0.0.7
 */
public interface INormalization {

    /**
     * 标准化
     * @param wordEntries 单词明细
     * @return 标准化的结果
     * @since 0.0.7
     */
    NormalizationResult normalization(final List<WordEntry> wordEntries);

}
