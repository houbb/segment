package com.github.houbb.segment.support.viterbi;

import com.github.houbb.heaven.support.instance.impl.Instances;

/**
 * 工具
 * @since 0.2.0
 * @author dh
 */
public final class Viterbis {

    private Viterbis(){}

    /**
     * 默认策略
     *
     * @return 实现
     * @since 0.2.0
     */
    public static IViterbi defaults() {
        return Instances.singleton(Viterbi.class);
    }

}
