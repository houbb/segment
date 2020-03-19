package com.github.houbb.segment.support.segment.result.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;

import java.util.List;
import java.util.Map;

/**
 * 分词结果处理工具类
 *
 * @author binbin.hou
 * @since 0.0.4
 */
public final class SegmentResultHandlers {

    private SegmentResultHandlers(){}

    /**
     * 普通的结果
     * @return 结果
     * @since 0.0.4
     */
    public static ISegmentResultHandler<List<ISegmentResult>> common() {
        return Instances.singleton(SegmentResultHandler.class);
    }

    /**
     * 只返回对应的分词信息
     * @return 处理类
     * @since 0.0.4
     */
    public static ISegmentResultHandler<List<String>> word() {
        return Instances.singleton(SegmentResultHandlerWord.class);
    }

    /**
     * 相同的词进行归类
     * @return 处理类
     * @since 0.1.5
     */
    public static ISegmentResultHandler<Map<String, Integer>> wordCount() {
        return Instances.singleton(SegmentResultHandlerWordCount.class);
    }

}
