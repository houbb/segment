package com.github.houbb.segment.support.segment.mode.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;

import java.util.List;

/**
 *
 * 返回所有词组信息模式
 *
 * （1）会剔除长度为 1 的词
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class IndexSegmentMode extends AbstractSegmentMode {

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
