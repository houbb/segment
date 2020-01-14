package com.github.houbb.segment.support.segment.result.impl;

import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 *
 * 贪心匹配
 *
 * 1. 选择长度最长的一个。
 * 2. 这个列表是排序过的，默认直接选择最后一个即可。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class GreedySegmentResultSelector extends AbstractSegmentResultSelector {

    @Override
    protected ISegmentResult doSelect(String string, int startIndex, List<ISegmentResult> resultList) {
        // 直接选择最后一个
        return resultList.get(resultList.size()-1);
    }

}
