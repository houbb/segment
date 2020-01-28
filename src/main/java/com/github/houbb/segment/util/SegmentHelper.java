package com.github.houbb.segment.util;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;

import java.util.List;

/**
 * 分词工具类
 * @author binbin.hou
 * @since 0.0.6
 */
public final class SegmentHelper {

    private SegmentHelper(){}

    /**
     * 执行分词
     * @param string 原始字符串
     * @return 分词结果
     * @since 0.0.6
     */
    public static List<ISegmentResult> segment(final String string) {
        return SegmentBs.newInstance()
                .segment(string);
    }

    /**
     * 执行分词
     * @param string 原始字符串
     * @param handler 结果的处理方式
     * @return 分词结果
     * @since 0.0.7
     * @param <R> 结果
     */
    public static <R> R segment(final String string, final ISegmentResultHandler<R> handler) {
        return SegmentBs.newInstance()
                .segment(string, handler);
    }

}
