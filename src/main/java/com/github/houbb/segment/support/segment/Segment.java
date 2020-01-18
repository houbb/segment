package com.github.houbb.segment.support.segment;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.result.impl.GreedySegmentResultSelector;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategyChain;
import com.github.houbb.segment.support.type.impl.DictWordType;

import java.util.List;

/**
 * 前缀树分词实现
 * @author binbin.hou
 * @since 0.0.1
 */
public class Segment implements ISegment {

    @Override
    public List<ISegmentResult> segment(String string, ISegmentContext context) {
        // 这里使用责任链模式
        List<ISegmentResult> segmentList = Guavas.newArrayList();

        //fast-return
        if(StringUtil.isEmpty(string)) {
            return segmentList;
        }

        for (int i = 0; i < string.length(); i++) {
            // 防御编程，避免具体策略影响 i 的值。
            int startIndex = i;

            List<ISegmentResult> segmentResultList = Instances.singleton(SegmentStrategyChain.class).segment(string, startIndex, context);

            // 选择结果处理最佳的策略
            // 这里选择贪心匹配策略
            ISegmentResult segmentResult = Instances.singleton(GreedySegmentResultSelector.class)
                    .select(string, startIndex, segmentResultList);
            segmentList.add(segmentResult);

            // 结果的词性启用
            if(context.wordType()) {
                fillWordType(segmentResult, context);
            }

            // 更新 i 的信息
            i = segmentResult.endIndex()-1;
        }


        return segmentList;
    }

    /**
     * 填充结果信息
     * @param segmentResult 分词结果
     * @since 0.0.2
     */
    private void fillWordType(ISegmentResult segmentResult, final ISegmentContext context) {
        String word = segmentResult.word();
        String type = Instances.singleton(DictWordType.class).getWordType(word, context);
        segmentResult.type(type);
    }

}
