package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

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
    protected List<ISegmentResult> doSelect(SegmentModeContext segmentModeContext) {
        List<ISegmentResult> resultList = Guavas.newArrayList();
        for(ISegmentResult segmentResult : segmentModeContext.resultList()) {
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
