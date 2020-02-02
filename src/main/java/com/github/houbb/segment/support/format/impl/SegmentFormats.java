package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.segment.support.format.ISegmentFormat;

/**
 * 分词格式工具类
 * @author binbin.hou
 * @since 0.0.9
 */
public final class SegmentFormats {

    private SegmentFormats(){}

    /**
     * 默认格式化实现
     * @return 格式化实现
     * @since 0.0.9
     */
    public static ISegmentFormat defaults() {
        return chains(halfWidth(), lowerCase());
    }


    /**
     * 转换为半角
     * @return 半角
     * @since 0.1.2
     */
    public static ISegmentFormat halfWidth() {
        return Instances.singleton(HalfWidthSegmentFormat.class);
    }

    /**
     * 转换为小写
     * @return 小写
     * @since 0.1.2
     */
    public static ISegmentFormat lowerCase() {
        return Instances.singleton(LowerCaseSegmentFormat.class);
    }

    /**
     * 中文简体格式化
     * @return 格式化实现
     * @since 0.1.2
     */
    public static ISegmentFormat chineseSimple() {
        return Instances.singleton(ChineseTsSegmentFormat.class);
    }

    /**
     * 无格式化实现
     * @return 格式化实现
     * @since 0.0.9
     */
    public static ISegmentFormat none() {
        return Instances.singleton(NoneSegmentFormat.class);
    }

    /**
     * 责任链
     * @param formats 格式化
     * @return 结果
     * @since 0.1.2
     */
    public static ISegmentFormat chains(final ISegmentFormat ... formats) {
        if(ArrayUtil.isEmpty(formats)) {
            return none();
        }

        return new AbstractSegmentFormatInit() {
            @Override
            protected void init(Pipeline<ISegmentFormat> pipeline) {
                for(ISegmentFormat format : formats) {
                    pipeline.addLast(format);
                }
            }
        };
    }

}
