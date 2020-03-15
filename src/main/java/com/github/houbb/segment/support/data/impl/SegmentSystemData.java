package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.SegmentWordEntry;

import java.util.List;

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
    private static volatile List<SegmentWordEntry> segmentWordEntryList = Guavas.newArrayList();

    @Override
    protected List<SegmentWordEntry> getStaticVolatileWordEntryList() {
        return segmentWordEntryList;
    }

    @Override
    protected List<String> readDictLines() {
        return StreamUtil.readAllLines(SegmentConst.SEGMENT_DICT_PATH);
    }

}
