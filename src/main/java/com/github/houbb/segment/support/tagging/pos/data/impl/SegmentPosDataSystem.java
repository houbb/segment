package com.github.houbb.segment.support.tagging.pos.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.constant.SegmentPosTaggingConst;
import com.github.houbb.segment.model.SegmentPos;
import com.github.houbb.segment.support.tagging.pos.data.ISegmentPosData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 分词数据抽象父类实现
 * （1）惰性加载
 *
 * @author binbin.hou
 * @since 0.1.4
 */
@ThreadSafe
public class SegmentPosDataSystem extends AbstractSegmentPosData {

    /**
     * 词性 map
     * @since 0.1.4
     */
    private static final Map<String, List<SegmentPos>> POS_MAP = Guavas.newHashMap();

    @Override
    protected Map<String, List<SegmentPos>> getStaticVolatilePosMap() {
        return POS_MAP;
    }

    @Override
    protected List<String> readDictLines() {
        return StreamUtil.readAllLines(SegmentPosTaggingConst.SEGMENT_POS_TAG_SYSTEM_PATH);
    }

}
