package com.github.houbb.segment.support.segment.result;

import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 * 分词结果选择
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
public interface ISegmentResultSelector {

    /**
     * 选择最佳的匹配结果
     * @param string 当前字符串
     * @param startIndex 开始下标
     * @param resultList 结果列表
     * @return 最佳匹配结果
     * @since 0.0.1
     */
    ISegmentResult select(final String string,
                         final int startIndex,
                         final List<ISegmentResult> resultList);

}
