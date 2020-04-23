package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.data.phrase.api.ISegmentPhraseData;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * 贪心最大频率匹配
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class GreedyFrequencySegmentMode extends AbstractSegmentMode {

    @Override
    protected List<ISegmentResult> doSelect(final SegmentModeContext context) {
        final ISegmentPhraseData segmentData = context.segmentContext().data();

        ISegmentMode allSegmentMode = SegmentModes.index();
        final List<ISegmentResult> resultList = allSegmentMode.select(context);

        // 按照概率再次排序
        Collections.sort(resultList, new Comparator<ISegmentResult>() {
            @Override
            public int compare(ISegmentResult o1, ISegmentResult o2) {
                Double freqOne = segmentData.getFreq(o1.word());
                Double freqTwo = segmentData.getFreq(o2.word());
                return freqOne.compareTo(freqTwo);
            }
        });

        ISegmentResult lastResult = resultList.get(resultList.size()-1);
        return Collections.singletonList(lastResult);
    }

}
