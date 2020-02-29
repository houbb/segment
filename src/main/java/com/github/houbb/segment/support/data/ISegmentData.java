package com.github.houbb.segment.support.data;

import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.model.WordProperty;
import com.github.houbb.segment.support.normalization.NormalizationResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分词数据接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentData {

    /**
     * 获取对应的单词列表
     * @return 单词列表
     * @since 0.1.0
     */
    Set<String> getWordSet();

    /**
     * 获取词性的 map
     *
     * （1）此处使用惰性加载，如果未开启，则不用加载对应的信息。
     *
     * @return 词性对应的 map
     * @since 0.0.2
     */
    Map<String, WordProperty> getWordTypeMap();

    /**
     * 获取频率
     *
     * （1）默认返回最低频率 {@link NormalizationResult#minFreq()}
     *
     * @param word 单词
     * @return 频率
     * @since 0.0.7
     */
    Double getFreq(final String word);

    /**
     * 获取最小的 freq
     * @return 最小的频率
     * @since 0.0.7
     */
    double getMinFreq();

    /**
     * 是否包含指定的词
     * @param word 词语
     * @return 是否包含
     * @since 0.1.0
     */
    boolean contains(final String word);

}
