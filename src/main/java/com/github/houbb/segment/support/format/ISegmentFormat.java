package com.github.houbb.segment.support.format;

import com.github.houbb.common.lifecycle.api.IDestroyAble;
import com.github.houbb.segment.api.ISegmentContext;

/**
 * 分词格式化
 *
 * 责任链模式
 *
 * （1）全角半角
 * （2）英文大小写。
 *
 * （3）中文繁简体替换。
 *
 * @author binbin.hou
 * @since 0.0.9
 */
public interface ISegmentFormat extends IDestroyAble {

    /**
     * 格式化
     * @param ch 字符
     * @param context 上下文
     * @return 结果
     * @since 0.0.9
     */
    char format(final char ch, final ISegmentContext context);

}
