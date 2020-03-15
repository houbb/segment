package com.github.houbb.segment.support.tagging.pos.tag.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.tagging.pos.tag.ISegmentPosTagging;

/**
 * 无词性标注实现
 * @author binbin.hou
 * @since 0.1.4
 */
public final class SegmentPosTaggings {

    private SegmentPosTaggings(){}

    /**
     * 不进行词性标注
     * @return 实现
     * @since 0.1.4
     */
    public static ISegmentPosTagging none() {
        return Instances.singleton(NoneSegmentPosTagging.class);
    }

    /**
     * 简单的词性标注
     * @return 实现
     * @since 0.1.4
     */
    public static ISegmentPosTagging simple() {
        return Instances.singleton(SimpleSegmentPosTagging.class);
    }

}
