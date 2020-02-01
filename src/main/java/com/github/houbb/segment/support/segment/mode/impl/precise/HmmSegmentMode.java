package com.github.houbb.segment.support.segment.mode.impl.precise;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategies;

import java.util.List;
import java.util.Map;

/**
 * HMM 分词
 * @author binbin.hou
 * @since 0.1.1
 */
@ThreadSafe
public class HmmSegmentMode extends DictSegmentMode {

    /**
     * 这里的 routeMap 简单构建即可。
     *
     * （1）默认会自动选择下一个。
     *
     * @param text 文本
     * @param context 上下文
     * @return 结果
     * @since 0.1.1
     */
    @Override
    protected Map<Integer, Pair<Integer, Double>> buildRouteMap(String text, ISegmentContext context) {
        return Guavas.newHashMap();
    }

    /**
     * hmm 没有词典，默认不包含
     * @param bufferWord 单词
     * @param segmentContext 上下文
     * @return 不包含
     * @since 0.1.1
     */
    @Override
    protected boolean dictContainsWord(String bufferWord, ISegmentContext segmentContext) {
        return false;
    }

    @Override
    protected List<ISegmentResult> buildChineseSegments(String bufferWord, int actualStartIndex, ISegmentContext segmentContext) {
        ISegmentStrategy segmentStrategy = SegmentStrategies.hmm();
        return segmentStrategy.segment(bufferWord,
                actualStartIndex, segmentContext);
    }

}
