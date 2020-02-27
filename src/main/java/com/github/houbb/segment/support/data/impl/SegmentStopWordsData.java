package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.support.data.ISegmentStopWordsData;

import java.util.List;

/**
 * 分词停止词数据接口
 * @author binbin.hou
 * @since 0.1.3
 */
@ThreadSafe
public class SegmentStopWordsData implements ISegmentStopWordsData {

    /**
     * 行信息
     * @since 0.1.3
     */
    private static final List<String> LINES;

    static {
        LINES = StreamUtil.readAllLines(SegmentConst.SEGMENT_STOP_WORDS_PATH);
    }
    /**
     * 是否包含指定的词
     *
     * @param word 词语
     * @return 是否包含
     * @since 0.1.3
     */
    @Override
    public boolean contains(final String word) {
        return false;
    }

}
