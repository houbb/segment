package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.segment.SegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 动态规划-最大概率匹配
 * <p>
 * 1. 选择概率最大的一个。
 * 2. 这个列表是排序过的，默认直接选择最后一个即可。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class MaxRateMode extends AbstractSegmentMode {

    @Override
    protected List<ISegmentResult> doSelect(final SegmentModeContext segmentModeContext) {
        // 基本信息
        final ISegmentData segmentData = segmentModeContext.segmentContext()
                .segmentData();
        final double minFreq = segmentData.getMinFreq();
        List<ISegmentResult> resultList = segmentModeContext.resultList();
        final Map<Integer, Double> routeMap = segmentModeContext.routeMap();
        final int startIndex = segmentModeContext.startIndex();

        // 处理
        ISegmentResult segmentResult = SegmentResult.newInstance()
                .startIndex(startIndex);

        // 最大概率
        Double maxFreq = null;

        System.out.println("resultList===" + resultList);

        for (ISegmentResult result : resultList) {
            final int endIndex = result.endIndex();
            final String word = result.word();

            // 获取当前字母的频率（动态规划）
            // 当前词的字典频率 + 当前词前一个的频率
            double routeFreq = getRouteFreq(routeMap, endIndex-1, minFreq);
            double freq = segmentData.getFreq(word) + routeFreq;

            if(ObjectUtil.isNull(maxFreq)) {
                maxFreq = freq;

                // 更新信息
                segmentResult.word(word)
                        .startIndex(startIndex)
                        .endIndex(endIndex)
                        .type(result.type());
            } else if (freq > maxFreq) {
                maxFreq = freq;
                // 更新信息
                segmentResult.word(word)
                        .startIndex(startIndex)
                        .endIndex(endIndex)
                        .type(result.type());
            }
        }

        // 将结果放在 map 中，用于下一次处理使用。
        routeMap.put(startIndex, maxFreq);

        // 结果测试
        return Collections.singletonList(segmentResult);
    }

    /**
     * 获取结果
     * @param routeMap 路由 map 信息
     * @param index 下标索引
     * @param minFreq 最小的频率
     * @return 结果
     * @since 0.0.7
     */
    private Double getRouteFreq(final Map<Integer, Double> routeMap,
                                final int index,
                                final double minFreq) {
        Double freq = routeMap.get(index);

        if(null == freq) {
            freq = minFreq;
        }

        return freq;
    }

}
