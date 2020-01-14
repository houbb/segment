package com.github.houbb.segment.bs;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
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
     * 直接执行分词
     * @param string 字符串
     * @return 结果
     * @since 0.0.1
     */
    public List<ISegmentResult> segment(final String string) {
        ISegmentContext segmentContext = buildContext();
        return segment.segment(string, segmentContext);
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
     * @since 0.0.1
     */
    private ISegmentContext buildContext() {
        ISegmentContext context = SegmentContext.newInstance();
        return context;
    }

}
