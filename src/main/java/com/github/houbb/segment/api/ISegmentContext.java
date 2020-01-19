package com.github.houbb.segment.api;

import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;

/**
 * 分词接口上下文
 *
 * （1）开启数字分词
 * （2）开启邮箱分词
 * （3）开启链接分词
 *
 * 后期：
 *
 * （1）是否启用基于字典的分词
 * （2）是否基于词频率的分词
 * （3）是否基于 HMM 进行分词
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentContext {

    /**
     * 是否启用词性
     * @return 词性
     * @since 0.0.2
     */
    boolean wordType();

    /**
     * 是否启用词性
     * @param wordType 是否启用词性
     * @return 词性
     * @since 0.0.2
     */
    ISegmentContext wordType(final boolean wordType);

    /**
     * 获取分词字典实现
     * @return 分词字典数据
     * @since 0.0.3
     */
    ISegmentData segmentData();

    /**
     * 获取分词模式
     * @return 分词模式
     * @since 0.0.5
     */
    ISegmentMode segmentMode();

}
