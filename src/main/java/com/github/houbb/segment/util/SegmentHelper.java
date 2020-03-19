package com.github.houbb.segment.util;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;

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
        return segment(string, SegmentModes.search());
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
        return segment(string, SegmentModes.search(), handler);
    }

    /**
     * 执行分词
     * @param string 原始字符串
     * @param segmentMode 分词模式
     * @return 分词结果
     * @since 0.1.5
     */
    public static List<ISegmentResult> segment(final String string, final ISegmentMode segmentMode) {
        return segment(string, segmentMode, SegmentResultHandlers.common());
    }

    /**
     * 执行分词
     * @param string 原始字符串
     * @param segmentMode 分词模式
     * @param handler 结果的处理方式
     * @return 分词结果
     * @since 0.1.5
     * @param <R> 结果
     */
    public static <R> R segment(final String string,
                                final ISegmentMode segmentMode,
                                final ISegmentResultHandler<R> handler) {
        return SegmentBs.newInstance()
                .segmentMode(segmentMode)
                .segment(string, handler);
    }

}
