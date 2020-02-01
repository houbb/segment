package com.github.houbb.segment.support.segment.strategy.impl;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.impl.SegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;

import java.util.List;

/**
 * 抽象分词策略
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public abstract class AbstractSegmentStrategy implements ISegmentStrategy {

    /**
     * 获取分词后的结束下标
     * @param string 字符串
     * @param startIndex 开始下标
     * @param context 上下文
     * @return 结果
     * @since 0.0.7
     */
    protected abstract List<Integer> doSegment(final String string,
                                             final int startIndex,
                                             final ISegmentContext context);

    @Override
    public List<ISegmentResult> segment(final String string,
                                        final int startIndex,
                                        final ISegmentContext context) {
        List<Integer> endIndexList = doSegment(string, startIndex, context);

        // 构建结果的 index 列表
        return buildSegmentResultList(string, startIndex, endIndexList);
    }

    /**
     * 构建分词结果列表
     * @param text 文本
     * @param startIndex 开始下标
     * @param endIndexList 结果列表
     * @return 结果列表
     * @since 0.1.0
     */
    private List<ISegmentResult> buildSegmentResultList(final String text,
                                                        final int startIndex,
                                                        final List<Integer> endIndexList) {
        List<ISegmentResult> resultList = Guavas.newArrayList(endIndexList.size());

        for(int endIndex : endIndexList) {
            String word = text.substring(startIndex, endIndex);
            ISegmentResult segmentResult = SegmentResult.newInstance()
                    .startIndex(startIndex)
                    .endIndex(endIndex)
                    .word(word);

            resultList.add(segmentResult);
        }
        return resultList;
    }

}
