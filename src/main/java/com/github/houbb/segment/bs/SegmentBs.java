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
     * 直接执行分词
     * @param string 字符串
     * @return 结果
     * @since 0.0.1
     */
    public List<ISegmentResult> segment(final String string) {
        final ISegmentContext context = buildContext();
        return segment.segment(string, context);
    }

    /**
     * 分词
     * @param string 字符串
     * @return 分词后的词信息
     * @since 0.0.1
     * @see #segment(String) 分词
     */
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
                .segmentData(segmentData);
    }

}
