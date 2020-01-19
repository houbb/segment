package com.github.houbb.segment.support.segment.result;

import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 * 分词结果处理接口
 * @author binbin.hou
 * @param <R> 泛型
 * @since 0.0.4
 */
public interface ISegmentResultHandler<R> {

    /**
     * 对于
     * @param resultList 结果列表
     * @return 结果
     * @since 0.0.4
     */
    R handler(final List<ISegmentResult> resultList);

}
