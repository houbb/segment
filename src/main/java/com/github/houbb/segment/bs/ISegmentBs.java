package com.github.houbb.segment.bs;

import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 * 分词引导类接口
 *
 * 1. 便于其他依赖接口使用。
 * 避免重复创建。
 * @author binbin.hou
 * @since 0.1.6
 */
public interface ISegmentBs {

    /**
     * 直接执行分词
     * @param string 字符串
     * @return 结果
     * @since 0.1.6
     */
    List<ISegmentResult> segment(final String string);

}
