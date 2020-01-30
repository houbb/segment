package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategies;

import java.util.List;

/**
 * 快速分词
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class FastSegment implements ISegment {

    /**
     * 快速分词模式
     *
     * 这里的时间复杂度是 O(n) 的。
     *
     * 这里也可以采用类似上述的分治的思想，将句子拆分，单个的处理。
     * 并且结合多线程进行处理。
     *
     * @param string  字符串
     * @param context 上下文
     * @return 分词结果列表
     * @since 0.0.7
     */
    @Override
    public List<ISegmentResult> segment(String string, ISegmentContext context) {
        List<ISegmentResult> segmentList = Guavas.newArrayList();

        final ISegmentStrategy tireTreeStrategy = SegmentStrategies.tireTree();
        for (int i = 0; i < string.length(); i++) {
            // 防御编程，避免具体策略影响 i 的值。
            int startIndex = i;

            // 这里的结果已经去重过了。
            List<ISegmentResult> segmentResultList = tireTreeStrategy
                    .segment(string, startIndex, context);

            // 上下文构建
            SegmentModeContext segmentModeContext = SegmentModeContext.newInstance()
                    .segmentContext(context)
                    .string(string)
                    .startIndex(startIndex)
                    .resultList(segmentResultList);

            // 结果处理
            List<ISegmentResult> selectList = context
                    .mode()
                    .select(segmentModeContext);
            segmentList.addAll(selectList);

            // 更新 i 的信息
            // 按照最后一个 index 来
            ISegmentResult lastSegmentResult = selectList.get(selectList.size() - 1);
            i = lastSegmentResult.endIndex() - 1;
        }

        return segmentList;
    }

}
