package com.github.houbb.segment.support.tagging.pos.tag.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.tagging.pos.tag.ISegmentPosTagging;

import java.util.List;

/**
 * 无词性标注实现
 * @author binbin.hou
 * @since 0.1.4
 */
@ThreadSafe
public class NoneSegmentPosTagging implements ISegmentPosTagging {

    @Override
    public void posTagging(List<ISegmentResult> selectList, ISegmentContext context) {
        //do nothing
    }

}
