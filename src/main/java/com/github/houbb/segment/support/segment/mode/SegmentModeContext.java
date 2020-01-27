package com.github.houbb.segment.support.segment.mode;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;
import java.util.Map;

/**
 * 分词模式上下文
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public class SegmentModeContext {

    /**
     * 字符串
     * @since 0.0.7
     */
    private String string;

    /**
     * 开始下标
     * @since 0.0.7
     */
    private int startIndex;

    /**
     * 分词结果列表
     * @since 0.0.7
     */
    private List<ISegmentResult> resultList;

    /**
     * 分词上下文
     * @since 0.0.7
     */
    private ISegmentContext segmentContext;

    /**
     * 路由 map
     * @since 0.0.7
     */
    private Map<Integer, Double> routeMap;

    /**
     * 创建新的实例
     * @return 实例
     * @since 0.0.7
     */
    public static SegmentModeContext newInstance() {
        return new SegmentModeContext();
    }

    public String string() {
        return string;
    }

    public SegmentModeContext string(String string) {
        this.string = string;
        return this;
    }

    public int startIndex() {
        return startIndex;
    }

    public SegmentModeContext startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public List<ISegmentResult> resultList() {
        return resultList;
    }

    public SegmentModeContext resultList(List<ISegmentResult> resultList) {
        this.resultList = resultList;
        return this;
    }

    public ISegmentContext segmentContext() {
        return segmentContext;
    }

    public SegmentModeContext segmentContext(ISegmentContext segmentContext) {
        this.segmentContext = segmentContext;
        return this;
    }

    public Map<Integer, Double> routeMap() {
        return routeMap;
    }

    public SegmentModeContext routeMap(Map<Integer, Double> routeMap) {
        this.routeMap = routeMap;
        return this;
    }

    @Override
    public String toString() {
        return "SegmentModeContext{" +
                "string='" + string + '\'' +
                ", startIndex=" + startIndex +
                ", resultList=" + resultList +
                ", segmentContext=" + segmentContext +
                ", routeMap=" + routeMap +
                '}';
    }

}
