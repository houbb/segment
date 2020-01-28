package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

import java.util.List;

/**
 * 抽象实现类
 * <p>
 * 1. 如果结果集合为空，则默认返回单个字的选择策略。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public abstract class AbstractSegmentMode implements ISegmentMode {

    /**
     * 列表为空，则强制使用单个字选择器。
     *
     * @param segmentModeContext 分词模式上下文
     * @return 最佳匹配结果
     * @since 0.0.1
     */
    @Override
    public List<ISegmentResult> select(final SegmentModeContext segmentModeContext) {
        final List<ISegmentResult> resultList = segmentModeContext.resultList();

        if (CollectionUtil.isEmpty(resultList)) {
            return SegmentModes.single()
                    .select(segmentModeContext);
        }

        // 单个的处理
        int size = resultList.size();
        if(1 == size) {
            return resultList;
        }

        // 具体的实现
        return doSelect(segmentModeContext);
    }

    /**
     * 执行选择
     *
     * @param segmentModeContext 分词模式上下文
     * @return 结果列表
     * @since 0.0.1
     */
    protected abstract List<ISegmentResult> doSelect(final SegmentModeContext segmentModeContext);

    @Override
    public boolean isFastMode() {
        return true;
    }

}
