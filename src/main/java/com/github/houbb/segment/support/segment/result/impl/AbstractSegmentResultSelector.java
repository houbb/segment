package com.github.houbb.segment.support.segment.result.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.result.ISegmentResultSelector;

import java.util.List;

/**
 * 抽象实现类
 *
 * 1. 如果结果集合为空，则默认返回单个字的选择策略。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public abstract class AbstractSegmentResultSelector implements ISegmentResultSelector {

    /**
     * 列表为空，则强制使用单个字选择器。
     *
     * @param string 当前字符串
     * @param startIndex 开始下标
     * @param resultList 结果列表
     * @return 最佳匹配结果
     * @since 0.0.1
     */
    @Override
    public ISegmentResult select(final String string,
                                final int startIndex,
                                final List<ISegmentResult> resultList) {

        if(CollectionUtil.isEmpty(resultList)) {
            return Instances.singleton(SingleSegmentResultSelector.class)
                    .select(string, startIndex, resultList);
        }

        // 具体的实现
        return doSelect(string, startIndex, resultList);
    }

    /**
     * 执行选择
     * @param string 字符串
     * @param startIndex 开始下标
     * @param resultList 结果列表
     * @return 结果
     * @since 0.0.1
     */
    protected abstract ISegmentResult doSelect(final String string,
                                      final int startIndex,
                                      final List<ISegmentResult> resultList);

}
