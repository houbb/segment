package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.type.ISegmentWordType;
import com.github.houbb.segment.support.type.impl.NoneSegmentWordType;

import java.util.List;

/**
 * 前缀树分词实现
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class Segment implements ISegment {

    @Override
    public List<ISegmentResult> segment(String string, ISegmentContext context) {
        // 这里使用责任链模式
        List<ISegmentResult> segmentList = Guavas.newArrayList();

        //fast-return
        if (StringUtil.isEmpty(string)) {
            return segmentList;
        }

        // 分词模式
        final ISegment segment = getSegment(context);
        segmentList = segment.segment(string, context);

        // 结果的词性处理
        fillWordType(segmentList, context);

        return segmentList;
    }

    /**
     * 获取具体的分词实现
     * @param context 上下文
     * @return 分词实现
     * @since 0.0.7
     */
    private ISegment getSegment(final ISegmentContext context) {
        final ISegmentMode segmentMode = context.mode();

        // 这里后续建议优化掉，直接将当前类作为实现。
        if(segmentMode.isFastMode()) {
            return Segments.fast();
        }

        return Segments.precise();
    }

    /**
     * 填充结果信息
     *
     * @param selectList 分词结果列表
     * @param context    上下文
     * @since 0.0.2
     */
    private void fillWordType(List<ISegmentResult> selectList, final ISegmentContext context) {
        final ISegmentWordType wordType = context.wordType();

        // 如果是 none，直接返回
        if(NoneSegmentWordType.class.equals(wordType.getClass())) {
            return;
        }

        for (ISegmentResult segmentResult : selectList) {
            String word = segmentResult.word();
            String type = wordType.getWordType(word, context);
            segmentResult.type(type);
        }
    }

}
