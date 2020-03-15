package com.github.houbb.segment.support.tagging.pos.data;

import com.github.houbb.segment.model.SegmentPos;

import java.util.List;

/**
 * 词性字典接口
 * @author binbin.hou
 * @since 0.1.4
 */
public interface ISegmentPosData {

    /**
     * 获取词性列表
     * @param word 单词
     * @return 结果列表
     * @since 0.1.4
     */
    List<SegmentPos> posList(final String word);

}
