package com.github.houbb.segment.util;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;

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

}
