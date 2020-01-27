package com.github.houbb.segment.support.normalization;

import java.util.Map;

/**
 * 标准化结果
 * @author binbin.hou
 * @since 0.0.7
 */
public class NormalizationResult {

    /**
     * 最小频次
     * @since 0.0.7
     */
    private long minCount;

    /**
     * 最大频次
     * @since 0.0.7
     */
    private long maxCount;

    /**
     * 最小频率
     * @since 0.0.7
     */
    private double minFreq;

    /**
     * 最大频率
     * @since 0.0.7
     */
    private double maxFreq;

    /**
     * 频率列表
     * @since 0.0.7
     */
    private Map<String, Double> freqMap;

    public long minCount() {
        return minCount;
    }

    public NormalizationResult minCount(long minCount) {
        this.minCount = minCount;
        return this;
    }

    public long maxCount() {
        return maxCount;
    }

    public NormalizationResult maxCount(long maxCount) {
        this.maxCount = maxCount;
        return this;
    }

    public double minFreq() {
        return minFreq;
    }

    public NormalizationResult minFreq(double minFreq) {
        this.minFreq = minFreq;
        return this;
    }

    public double maxFreq() {
        return maxFreq;
    }

    public NormalizationResult maxFreq(double maxFreq) {
        this.maxFreq = maxFreq;
        return this;
    }

    public Map<String, Double> freqMap() {
        return freqMap;
    }

    public NormalizationResult freqMap(Map<String, Double> freqMap) {
        this.freqMap = freqMap;
        return this;
    }

    @Override
    public String toString() {
        return "NormalizationResult{" +
                "minCount=" + minCount +
                ", maxCount=" + maxCount +
                ", minFreq=" + minFreq +
                ", maxFreq=" + maxFreq +
                ", freqMap=" + freqMap +
                '}';
    }

}
