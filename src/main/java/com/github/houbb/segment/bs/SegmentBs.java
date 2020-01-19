package com.github.houbb.segment.bs;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.data.impl.SegmentDatas;
import com.github.houbb.segment.support.segment.Segment;
import com.github.houbb.segment.support.segment.SegmentContext;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;

import java.util.List;

/**
 * 分词引导类
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public final class SegmentBs {

    /**
     * 分词实现
     * @since 0.0.1
     */
    private ISegment segment = Instances.singleton(Segment.class);

    /**
     * 分词数据来源
     * 1. 默认使用系统的字典
     * @since 0.0.3
     */
    private ISegmentData segmentData = SegmentDatas.system();

    /**
     * 分词模式
     *
     * 1. 默认使用贪婪模式
     *
     * @since 0.0.5
     */
    private ISegmentMode segmentMode = SegmentModes.greedy();

    /**
     * 是否返回词性
     * @since 0.0.3
     */
    private boolean wordType = false;

    /**
     * 引导类
     * @since 0.0.1
     */
    private SegmentBs(){}

    /**
     * 创建一个新的实例
     * @return this
     * @since 0.0.1
     */
    public static SegmentBs newInstance() {
        return new SegmentBs();
    }

    /**
     * 指定启用词性
     * @param wordType 指定启用词性
     * @return this
     * @since 0.0.2
     */
    public SegmentBs wordType(final boolean wordType) {
        this.wordType = wordType;
        return this;
    }

    /**
     * 指定分词的数据实现
     * @param segmentData 分词数据
     * @return this
     * @since 0.0.3
     */
    public SegmentBs segmentData(final ISegmentData segmentData) {
        this.segmentData = segmentData;
        return this;
    }

    /**
     * 设置分词模式
     * @param segmentMode 分词模式
     * @return this
     * @since 0.0.5
     */
    public SegmentBs segmentMode(ISegmentMode segmentMode) {
        this.segmentMode = segmentMode;
        return this;
    }

    /**
     * 直接执行分词
     * @param string 字符串
     * @return 结果
     * @since 0.0.1
     */
    public List<ISegmentResult> segment(final String string) {
        return segment(string, SegmentResultHandlers.common());
    }

    /**
     * 分词处理
     * @param string 原始字符串
     * @param handler 处理类
     * @param <R> 泛型
     * @return 处理后的结果
     * @since 0.0.4
     */
    public <R> R segment(final String string, final ISegmentResultHandler<R> handler) {
        final ISegmentContext context = buildContext();
        List<ISegmentResult> segmentResults = segment.segment(string, context);
        return handler.handler(segmentResults);
    }

    /**
     * 分词
     *
     * 缺陷：无法保证接口的统一性，不便于以后拓展。
     *
     * 这个最多是放在 {@link com.github.houbb.segment.util.SegmentHelper} 层的一个方法。
     * @param string 字符串
     * @return 分词后的词信息
     * @since 0.0.1
     * @see #segment(String) 分词
     */
    @Deprecated
    public List<String> segmentWords(final String string) {
        List<ISegmentResult> segmentResultList = segment(string);

        return CollectionUtil.toList(segmentResultList, new IHandler<ISegmentResult, String>() {
            @Override
            public String handle(ISegmentResult iSegmentResult) {
                return iSegmentResult.word();
            }
        });
    }

    /**
     * 构建上下文
     * @return 上下文
     * @since 0.0.3
     */
    private ISegmentContext buildContext() {
        return SegmentContext.newInstance()
                .wordType(wordType)
                .segmentData(segmentData)
                .segmentMode(segmentMode);
    }

}
