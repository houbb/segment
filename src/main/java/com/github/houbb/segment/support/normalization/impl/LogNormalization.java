package com.github.houbb.segment.support.normalization.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.model.SegmentWordEntry;
import com.github.houbb.segment.support.normalization.INormalization;
import com.github.houbb.segment.support.normalization.NormalizationResult;

import java.util.List;
import java.util.Map;

/**
 * 标准化接口
 *
 * <pre>
 *  x* = log10(x) / log10(max)
 * </pre>
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class LogNormalization implements INormalization {

    /**
     * 标准化
     * @return 标准化的结果
     * @since 0.0.7
     */
    @Override
    public NormalizationResult normalization(final List<SegmentWordEntry> wordEntries) {
        // fail-fast
        NormalizationResult result = new NormalizationResult();
        if(CollectionUtil.isEmpty(wordEntries)) {
            return result;
        }

        // 默认值
        long minCount = Long.MAX_VALUE;
        long maxCount = Long.MIN_VALUE;
        double minFreq = Double.MAX_VALUE;
        double maxFreq = Double.MIN_VALUE;

        // 遍历计算
        for(SegmentWordEntry segmentWordEntry : wordEntries) {
            long count = segmentWordEntry.count();
            if(minCount > count) {
                minCount = count;
            }
            if(maxCount < count) {
                maxCount = count;
            }
        }

        // 计算频率
        Map<String, Double> freqMap = Guavas.newHashMap(wordEntries.size());
        for(SegmentWordEntry segmentWordEntry : wordEntries) {
            // 标准化 [0,1]
            double freq = Math.log(segmentWordEntry.count()*1.0)/Math.log(maxCount*1.0);
            if(minFreq > freq) {
                minFreq = freq;
            }
            if(maxFreq < freq) {
                maxFreq = freq;
            }

            freqMap.put(segmentWordEntry.word(), freq);
        }

        return result.minCount(minCount)
                .maxCount(maxCount)
                .minFreq(minFreq)
                .maxFreq(maxFreq)
                .freqMap(freqMap);
    }

}
