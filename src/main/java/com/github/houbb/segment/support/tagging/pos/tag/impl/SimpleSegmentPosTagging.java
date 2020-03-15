package com.github.houbb.segment.support.tagging.pos.tag.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.constant.enums.SegmentPosEnum;
import com.github.houbb.segment.model.SegmentPos;
import com.github.houbb.segment.support.tagging.pos.data.ISegmentPosData;
import com.github.houbb.segment.support.tagging.pos.tag.ISegmentPosTagging;

import java.util.List;

/**
 * 无词性标注实现
 * @author binbin.hou
 * @since 0.1.4
 */
@ThreadSafe
public class SimpleSegmentPosTagging implements ISegmentPosTagging {

    @Override
    public void posTagging(List<ISegmentResult> selectList, ISegmentContext context) {
        final ISegmentPosData posData = context.posData();

        //do nothing
        for(ISegmentResult result : selectList) {
            List<SegmentPos> posList = posData.posList(result.word());
            if(CollectionUtil.isNotEmpty(posList)) {
                SegmentPos pos = posList.get(0);
                result.pos(pos.pos());
            } else {
                result.pos(SegmentPosEnum.UN.code());
            }
        }
    }

}
