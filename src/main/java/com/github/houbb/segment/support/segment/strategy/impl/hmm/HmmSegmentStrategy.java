package com.github.houbb.segment.support.segment.strategy.impl.hmm;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.impl.SegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;
import com.github.houbb.segment.support.segment.strategy.impl.SimpleSegmentStrategy;
import com.github.houbb.segment.util.CharUtils;

import java.util.List;

/**
 * HMM 分词策略
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class HmmSegmentStrategy extends SimpleSegmentStrategy {

    @Override
    protected List<String> getChineseSegments(String text) {
        return Viterbi.segment(text);
    }

}
