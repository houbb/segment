package com.github.houbb.segment.support.type.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.type.ISegmentWordType;

/**
 * 词性工具类
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public final class WordTypes {

    private WordTypes(){}

    /**
     * 没有词性信息
     * @return 无词性实现
     * @since 0.0.7
     */
    public static ISegmentWordType none() {
        return Instances.singleton(NoneSegmentWordType.class);
    }

    /**
     * 默认选择第一个
     * @return 第一个数据信息
     * @since 0.0.7
     */
    public static ISegmentWordType first() {
        return Instances.singleton(FirstSegmentWordType.class);
    }

}
