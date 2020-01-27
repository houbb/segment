package com.github.houbb.segment.support.segment;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;
import com.github.houbb.segment.support.segment.strategy.impl.SegmentStrategyChain;
import com.github.houbb.segment.support.type.IWordType;

import java.util.List;
import java.util.Map;

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

        // 选择结果处理最佳的策略
        Map<Integer, Double> routeMap = Guavas.newHashMap();
        // 这里是正向处理的
        routeMap.put(-1, 0.0);

        for (int i = 0; i < string.length(); i++) {
            // 防御编程，避免具体策略影响 i 的值。
            int startIndex = i;

            // 这里的结果已经排序过了。
            List<ISegmentResult> segmentResultList = Instances.singleton(SegmentStrategyChain.class).segment(string, startIndex, context);

            // 上下文构建
            SegmentModeContext segmentModeContext = SegmentModeContext.newInstance()
                    .segmentContext(context)
                    .string(string)
                    .startIndex(startIndex)
                    .resultList(segmentResultList)
                    .routeMap(routeMap);

            // 结果处理
            List<ISegmentResult> selectList = context
                    .segmentMode()
                    .select(segmentModeContext);
            segmentList.addAll(selectList);

            // 结果的词性启用
            fillWordType(selectList, context);

            // 更新 i 的信息
            // 按照最后一个 index 来
            ISegmentResult lastSegmentResult = selectList.get(selectList.size()-1);
            i = lastSegmentResult.endIndex()-1;
        }


        return segmentList;
    }

    /**
     * 填充结果信息
     * @param selectList 分词结果列表
     * @param context 上下文
     * @since 0.0.2
     */
    private void fillWordType(List<ISegmentResult> selectList, final ISegmentContext context) {
        final IWordType wordType = context.wordType();

        for(ISegmentResult segmentResult : selectList) {
            String word = segmentResult.word();
            String type = wordType.getWordType(word, context);
            segmentResult.type(type);
        }
    }

}
