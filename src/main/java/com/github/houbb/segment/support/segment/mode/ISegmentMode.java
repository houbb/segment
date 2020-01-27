package com.github.houbb.segment.support.segment.mode;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 * 分词模式接口
 *
 * （1）如果为空，直接返回单个结果
 * （2）不为空主要有两种策略：
 *
 * 2.1 默认贪心返回最长的结果
 * 2.2 后期通过动态规划+DAG 循环图+概率匹配=最佳匹配结果
 *
 * （3）词性标注应该是一个独立的接口
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentMode {

    /**
     * 选择最佳的匹配结果
     * @param segmentModeContext 分词模式上下文
     * @return 匹配结果列表
     * @since 0.0.1
     */
    List<ISegmentResult> select(final SegmentModeContext segmentModeContext);

}
