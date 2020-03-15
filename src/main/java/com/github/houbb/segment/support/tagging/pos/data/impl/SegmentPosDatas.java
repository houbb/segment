package com.github.houbb.segment.support.tagging.pos.data.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.tagging.pos.data.ISegmentPosData;

/**
 * 分词数据抽象父类实现
 * （1）惰性加载
 *
 * @author binbin.hou
 * @since 0.1.4
 */
public final class SegmentPosDatas {

    private SegmentPosDatas(){}

    /**
     * 系统内置实现
     * @return 实现
     * @since 0.1.4
     */
    public static ISegmentPosData system() {
        return Instances.singleton(SegmentPosDataSystem.class);
    }

    /**
     * 自定义实现
     * @return 实现
     * @since 0.1.4
     */
    public static ISegmentPosData define() {
        return Instances.singleton(SegmentPosDataDefine.class);
    }

    /**
     * 混合模式实现
     * @return 实现
     * @since 0.1.4
     */
    public static ISegmentPosData mixed() {
        return Instances.singleton(SegmentPosDataMixed.class);
    }

}
