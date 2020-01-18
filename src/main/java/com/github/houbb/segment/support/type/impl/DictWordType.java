package com.github.houbb.segment.support.type.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.constant.enums.WordTypeEnum;
import com.github.houbb.segment.model.WordProperty;
import com.github.houbb.segment.support.data.impl.SegmentSystemData;
import com.github.houbb.segment.support.type.IWordType;

import java.util.Map;

/**
 * 基于分词字典获取词性
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class DictWordType implements IWordType {

    /**
     * 获取对应的词性
     * （1）如果不存在，则直接返回 "un"
     * @param word 单词
     * @param context 上下文
     * @return 词性
     * @since 0.0.2
     */
    @Override
    public String getWordType(final String word, final ISegmentContext context) {
        Map<String, WordProperty> propertyMap = context.segmentData().getWordTypeMap();

        WordProperty wordProperty = propertyMap.get(word);
        if(ObjectUtil.isNotNull(wordProperty)) {
            return wordProperty.type();
        }

        return WordTypeEnum.UN.code();
    }

}
