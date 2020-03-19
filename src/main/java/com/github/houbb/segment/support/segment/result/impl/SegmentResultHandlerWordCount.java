package com.github.houbb.segment.support.segment.result.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 将相同的词进行归类
 *
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SegmentResultHandlerWordCount implements ISegmentResultHandler<Map<String, Integer>> {

    @Override
    public Map<String, Integer> handler(List<ISegmentResult> resultList) {
        if(CollectionUtil.isEmpty(resultList)) {
            return Collections.emptyMap();
        }

        Map<String, Integer> countMap = Guavas.newHashMap();
        for(ISegmentResult result : resultList) {
            final String word = result.word();

            Integer count = countMap.get(word);
            if(ObjectUtil.isNull(count)) {
                count = 0;
            }

            count++;
            countMap.put(word, count);
        }

        return countMap;
    }

}
