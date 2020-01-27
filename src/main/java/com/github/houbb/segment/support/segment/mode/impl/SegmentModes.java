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
     * 贪心模式-最大长度匹配模式
     * @return 分词实现
     * @since 0.0.5
     */
    public static ISegmentMode maxLength() {
        return Instances.singleton(MaxLengthMode.class);
    }

    /**
     * 最大概率匹配模式
     * @return 分词实现
     * @since 0.0.7
     */
    public static ISegmentMode maxRate() {
        return Instances.singleton(MaxRateMode.class);
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
