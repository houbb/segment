package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.model.WordProperty;

import java.util.List;
import java.util.Map;

/**
 * 分词数据实现
 * （1）惰性加载
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class SegmentSystemData extends AbstractSegmentData {

    /**
     * 默认的内置行
     *
     * @since 0.0.3
     */
    private static volatile List<WordEntry> wordEntryList = Guavas.newArrayList();

    /**
     * 词对应的 map
     * @since 0.0.2
     */
    private static volatile Map<String, WordProperty> wordMap = Guavas.newHashMap();

    @Override
    protected List<WordEntry> getStaticVolatileWordEntryList() {
        return wordEntryList;
    }

    @Override
    protected Map<String, WordProperty> getStaticVolatileWordTypeMap() {
        return wordMap;
    }

    @Override
    protected String getDictPath() {
        return SegmentConst.SEGMENT_DICT_PATH;
    }

}
