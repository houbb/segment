package com.github.houbb.segment.util;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.constant.enums.SegmentModeEnum;
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
        return segment(string, false);
    }

    /**
     * 执行分词
     * @param string 原始字符串
     * @param wordType 是否返回词性
     * @return 分词结果
     * @since 0.0.6
     */
    public static List<ISegmentResult> segment(final String string,
                                               final boolean wordType) {
        return SegmentBs.newInstance()
                .wordType(wordType)
                .segment(string);
    }

    /**
     * 分词处理
     * @param string 原始字符串
     * @param modeEnum 分词模式
     * @return 处理后的结果
     * @since 0.0.6
     */
    public static List<ISegmentResult> segment(final String string, final SegmentModeEnum modeEnum) {
        return segment(string, false, modeEnum, SegmentResultHandlers.common());
    }

    /**
     * 分词处理
     * @param string 原始字符串
     * @param handler 处理类
     * @param <R> 泛型
     * @return 处理后的结果
     * @since 0.0.6
     */
    public static <R> R segment(final String string, final ISegmentResultHandler<R> handler) {
        return segment(string, false, SegmentModeEnum.GREEDY, handler);
    }

    /**
     * 分词处理
     * @param string 原始字符串
     * @param wordType 是否返回词性
     * @param modeEnum 分词模式
     * @param handler 处理类
     * @param <R> 泛型
     * @return 处理后的结果
     * @since 0.0.6
     */
    public static <R> R segment(final String string,
                         final boolean wordType,
                         final SegmentModeEnum modeEnum,
                         final ISegmentResultHandler<R> handler) {
        ArgUtil.notNull(modeEnum, "modeEnum");
        ArgUtil.notNull(handler, "handler");

        final ISegmentMode segmentMode = getSegmentMode(modeEnum);
        return SegmentBs.newInstance()
                .wordType(wordType)
                .segmentMode(segmentMode)
                .segment(string, handler);
    }

    /**
     * 获取分词模式
     * @param modeEnum 模式枚举
     * @return 分词模式实现
     * @since 0.0.6
     */
    private static ISegmentMode getSegmentMode(final SegmentModeEnum modeEnum) {
        if(SegmentModeEnum.ALL.equals(modeEnum)) {
            return SegmentModes.all();
        }

        return SegmentModes.greedy();
    }

}
