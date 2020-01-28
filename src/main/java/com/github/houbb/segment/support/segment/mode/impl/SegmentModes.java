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
     * 默认-全局最大概率匹配模式
     * @return 分词实现
     * @since 0.0.7
     */
    public static ISegmentMode search() {
        return Instances.singleton(SearchSegmentMode.class);
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
     * 贪心模式-最大长度匹配模式
     * @return 分词实现
     * @since 0.0.5
     */
    public static ISegmentMode greedyLength() {
        return Instances.singleton(GreedyLengthSegmentMode.class);
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
     * 全模式分词
     * @return 分词模式实现
     * @since 0.0.5
     */
    public static ISegmentMode all() {
        return Instances.singleton(AllSegmentMode.class);
    }

    /**
     * 全词组模式分词
     * @return 分词模式实现
     * @since 0.0.7
     */
    public static ISegmentMode allPhrase() {
        return Instances.singleton(AllPhraseSegmentMode.class);
    }


}
