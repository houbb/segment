package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.impl.precise.DictSegmentMode;
import com.github.houbb.segment.support.segment.mode.impl.precise.HmmSegmentMode;
import com.github.houbb.segment.support.segment.mode.impl.precise.SearchSegmentMode;

/**
 * <p> project: segment-SegmentModes </p>
 * <p> create on 2020/1/19 23:37 </p>
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public final class SegmentModes {

    private SegmentModes(){}

    /**
     * 默认-全局最大概率匹配模式
     * @return 分词实现
     * @since 0.0.7
     */
    public static ISegmentMode search() {
        return Instances.singleton(SearchSegmentMode.class);
    }

    /**
     * 全词组模式分词
     * @return 分词模式实现
     * @since 0.0.7
     */
    public static ISegmentMode index() {
        return Instances.singleton(IndexSegmentMode.class);
    }

    /**
     * 贪心模式-最大长度匹配模式
     * @return 分词实现
     * @since 0.0.5
     */
    public static ISegmentMode greedyLength() {
        return Instances.singleton(GreedyLengthSegmentMode.class);
    }

    /**
     * 只依赖于字典的分词实现
     * @return 分词实现
     * @since 0.1.1
     */
    public static ISegmentMode dict() {
        return Instances.singleton(DictSegmentMode.class);
    }

    /**
     * 只依赖于 HMM 算法的分词实现
     * @return 分词实现
     * @since 0.1.1
     */
    public static ISegmentMode hmm() {
        return Instances.singleton(HmmSegmentMode.class);
    }

    /**
     * 贪心模式-最大频率匹配模式
     * @return 分词实现
     * @since 0.0.7
     */
    public static ISegmentMode greedyFrequency() {
        return Instances.singleton(GreedyFrequencySegmentMode.class);
    }

    /**
     * 单个结果处理
     * 备注：这里仅用于没有结果的处理情况，仅供内部使用。
     * @return 实例
     */
    public static ISegmentMode single() {
        return Instances.singleton(SingleSegmentMode.class);
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
