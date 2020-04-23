package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.data.ISegmentData;

/**
 * 自定义分词数据
 * <p> project: segment-SegmentDefineData </p>
 * <p> create on 2020/1/18 20:05 </p>
 *
 * @author binbin.hou
 * @since 0.0.3
 */
public final class SegmentDatas {

    private SegmentDatas(){}

    /**
     * 获取系统分词数据
     * @return 系统分词数据
     * @since 0.0.3
     */
    public static ISegmentData system() {
        return Instances.singleton(SegmentSystemData.class);
    }

    /**
     * 获取自定义分词数据
     * @return 系统自定义数据
     * @since 0.0.3
     */
    public static ISegmentData define() {
        return Instances.singleton(SegmentDefineData.class);
    }

    /**
     * 获取混合模式分词数据
     * @return 系统自定义数据
     * @since 0.1.3
     */
    public static ISegmentData mixed() {
        return Instances.singleton(SegmentMixedData.class);
    }

}
