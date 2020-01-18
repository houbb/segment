package com.github.houbb.segment.support.data;

import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.model.WordProperty;

import java.util.List;
import java.util.Map;

/**
 * 分词数据接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentData {

    /**
     * 获取对应的词信息
     *
     * ps: 即使列表为空，依然可以分词。
     * 没有 HMM 之前，就相当于与全部为单个字。
     *
     * @return 词信息列表
     * @since 0.0.1
     */
    List<WordEntry> getWordEntryList();

    /**
     * 获取词性的 map
     *
     * （1）此处使用惰性加载，如果未开启，则不用加载对应的信息。
     *
     * @return 词性对应的 map
     * @since 0.0.2
     */
    Map<String, WordProperty> getWordTypeMap();

}
