package com.github.houbb.segment.support.segment.strategy.impl.hmm;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.support.format.ISegmentFormat;
import com.github.houbb.segment.support.segment.strategy.impl.SimpleSegmentStrategy;

import java.util.List;

/**
 * HMM 分词策略
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class HmmSegmentStrategy extends SimpleSegmentStrategy {

    @Override
    protected List<String> getChineseSegments(String text, final ISegmentContext context) {
        final String formatText = formatText(text, context);
        return context.viterbi().viterbi(text, formatText);
    }

    /**
     * 获取格式化后的文本
     * @param text 原始文本
     * @return 格式化后的文本
     * @since 0.1.2
     */
    private String formatText(final String text, final ISegmentContext context) {
        // 长度不大于1，直接返回。没有 HMM 的必要
        if(StringUtil.isEmpty(text) || text.length() == 1) {
            return text;
        }

        StringBuilder stringBuilder = new StringBuilder(text.length());
        final ISegmentFormat segmentFormat = context.format();
        for(char ch : text.toCharArray()) {
            char formatChar = segmentFormat.format(ch, context);
            stringBuilder.append(formatChar);
        }

        return stringBuilder.toString();
    }

}
