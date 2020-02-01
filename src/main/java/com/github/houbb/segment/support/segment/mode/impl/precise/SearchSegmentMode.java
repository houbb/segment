package com.github.houbb.segment.support.segment.mode.impl.precise;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.segment.impl.SegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategies;

import java.util.List;
import java.util.Map;

/**
 * 动态规划-最大概率匹配
 *
 * 1. 选择动态规划概率最大的一个。
 * 2. 这个列表是排序过的，默认直接选择最后一个即可。
 *
 * 3. 结合 HMM 处理新词预测
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class SearchSegmentMode extends DictSegmentMode {

    @Override
    protected List<ISegmentResult> buildChineseSegments(String bufferWord, int actualStartIndex, ISegmentContext segmentContext) {
        ISegmentStrategy segmentStrategy = SegmentStrategies.hmm();
        return segmentStrategy.segment(bufferWord,
                actualStartIndex, segmentContext);
    }

}
