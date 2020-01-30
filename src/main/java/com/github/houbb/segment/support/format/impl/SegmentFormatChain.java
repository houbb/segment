package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.format.ISegmentFormat;

import java.util.List;

/**
 * 全角半角格式化处理
 * @author binbin.hou
 * @since 0.0.9
 */
@ThreadSafe
public class SegmentFormatChain implements ISegmentFormat {

    /**
     * 格式化责任链
     * @since 0.0.9
     */
    private static final List<ISegmentFormat> FORMAT_CHAIN = Guavas.newArrayList();

    static {
        FORMAT_CHAIN.add(Instances.singleton(HalfWidthSegmentFormat.class));
        FORMAT_CHAIN.add(Instances.singleton(LowerCaseSegmentFormat.class));
    }

    @Override
    public char format(char ch, ISegmentContext context) {
        char result = ch;
        for(ISegmentFormat format : FORMAT_CHAIN) {
            result = format.format(result, context);
        }
        return result;
    }

}
