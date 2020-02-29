package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.model.WordProperty;

import java.util.List;
import java.util.Map;

/**
 * 自定义分词数据
 * <p> project: segment-SegmentDefineData </p>
 * <p> create on 2020/1/18 20:05 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
@ThreadSafe
public class SegmentDefineData extends AbstractSegmentData {

    /**
     * 默认的内置行
     *
     * @since 0.0.1
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
    protected List<String> readDictLines() {
        return StreamUtil.readAllLines(SegmentConst.SEGMENT_DEFINE_DICT_PATH);
    }

}
