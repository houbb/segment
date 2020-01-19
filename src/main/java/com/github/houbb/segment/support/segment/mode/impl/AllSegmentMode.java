package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.condition.ICondition;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 *
 * 全匹配模式
 *
 * 返回所有的匹配结果
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class AllSegmentMode extends AbstractSegmentMode {

    @Override
    protected List<ISegmentResult> doSelect(String string, int startIndex, List<ISegmentResult> segmentList) {
        int size = segmentList.size();
        if(1 == size) {
            return segmentList;
        }

        List<ISegmentResult> resultList = Guavas.newArrayList();
        for(ISegmentResult segmentResult : segmentList) {
            String word = segmentResult.word();

            // 剔除掉长度为1的分词
            if(word.length() == 1) {
                continue;
            }

            resultList.add(segmentResult);
        }
        return resultList;
    }

}
