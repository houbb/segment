package com.github.houbb.segment.support.segment.result.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;

import java.util.List;

/**
 * 只获取词的分词结果处理
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class SegmentResultWordHandler implements ISegmentResultHandler<List<String>> {

    @Override
    public List<String> handler(List<ISegmentResult> resultList) {
        return CollectionUtil.toList(resultList, new IHandler<ISegmentResult, String>() {
            @Override
            public String handle(ISegmentResult iSegmentResult) {
                return iSegmentResult.word();
            }
        });
    }

}
