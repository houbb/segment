package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.SegmentWordEntry;

import java.util.List;

/**
 * 混合模式分词数据实现
 * @author binbin.hou
 * @since 0.1.3
 */
@ThreadSafe
public class SegmentMixedData extends AbstractSegmentData {

    /**
     * 默认的内置行
     *
     * @since 0.1.3
     */
    private static volatile List<SegmentWordEntry> segmentWordEntryList = Guavas.newArrayList();

    @Override
    protected List<SegmentWordEntry> getStaticVolatileWordEntryList() {
        return segmentWordEntryList;
    }

    @Override
    protected List<String> readDictLines() {
        List<String> systemLines = StreamUtil.readAllLines(SegmentConst.SEGMENT_DICT_PATH);
        List<String> defineLines = StreamUtil.readAllLines(SegmentConst.SEGMENT_DEFINE_DICT_PATH);

        systemLines.addAll(defineLines);
        return systemLines;
    }

}
