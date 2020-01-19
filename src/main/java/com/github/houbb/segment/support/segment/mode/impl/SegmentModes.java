package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;

/**
 * <p> project: segment-SegmentModes </p>
 * <p> create on 2020/1/19 23:37 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
public final class SegmentModes {

    private SegmentModes(){}

    /**
     * 单个结果处理
     * 备注：这里仅用于没有结果的处理情况，仅供内部使用。
     * @return 实例
     */
    static ISegmentMode single() {
        return Instances.singleton(SingleSegmentMode.class);
    }

    /**
     * 贪婪分词模式
     * @return 分词实现
     * @since 0.0.5
     */
    public static ISegmentMode greedy() {
        return Instances.singleton(GreedySegmentMode.class);
    }

    /**
     * 全模式分词
     * @return 分词模式实现
     * @since 0.0.5
     */
    public static ISegmentMode all() {
        return Instances.singleton(AllSegmentMode.class);
    }

}
