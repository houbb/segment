package com.github.houbb.segment.api;

import com.github.houbb.segment.data.phrase.api.ISegmentPhraseData;
import com.github.houbb.segment.data.pos.api.ISegmentPosData;
import com.github.houbb.segment.support.format.ISegmentFormat;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.tagging.pos.tag.ISegmentPosTagging;

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
     * 词性标注
     * @return 词性标注实现
     * @since 0.1.4
     */
    ISegmentPosTagging posTagging();

    /**
     * 词性标注数据
     * @return 数据
     * @since 0.1.4
     */
    ISegmentPosData posData();

    /**
     * 获取分词字典实现
     * @return 分词字典数据
     * @since 0.0.3
     */
    ISegmentPhraseData data();

    /**
     * 获取分词模式
     * @return 分词模式
     * @since 0.0.5
     */
    ISegmentMode mode();

    /**
     * 格式化信息
     * @return 格式化信息
     * @since 0.0.9
     */
    ISegmentFormat format();

}
