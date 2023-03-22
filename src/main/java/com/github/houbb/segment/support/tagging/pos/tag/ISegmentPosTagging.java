package com.github.houbb.segment.support.tagging.pos.tag;

import com.github.houbb.segment.api.IFreeAble;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;

import java.util.List;

/**
 * 词性标注接口
 * @author binbin.hou
 * @since 0.1.4
 */
public interface ISegmentPosTagging extends IFreeAble {

    /**
     * 设置获取对应的词性
     * （1）如果不存在，则直接返回 "un"
     * @param selectList 单词选择的列表信息
     * @param context 上下文
     * @since 0.1.4
     */
    void posTagging(List<ISegmentResult> selectList, final ISegmentContext context);

}
