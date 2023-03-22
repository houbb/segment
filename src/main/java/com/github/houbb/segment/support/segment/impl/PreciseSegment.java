package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.SegmentModeContext;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.util.CharUtils;

import java.util.List;

/**
 * 精确分词
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class PreciseSegment extends AbstractSegment {

    /**
     * 精确分词模式
     * （1）基于动态规划的频率
     * （2）基于 HMM 的新词预测
     * <p>
     * 这里要按照字符串的类型进行中断，避免句子过长。
     * <p>
     * 1. 中文
     * 2. 英文
     * 3. 数字
     * 4. 连接符
     * 其他的符号直接中断。
     *
     * @param string  字符串
     * @param context 上下文
     * @return 分词结果列表
     * @since 0.0.7
     */
    @Override
    public List<ISegmentResult> segment(String string, ISegmentContext context) {
        List<ISegmentResult> segmentList = Guavas.newArrayList();

        // 构建所有的分词结果
        final int length = string.length();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (CharUtils.isConsequent(c)) {
                // 如果是连续的，则加入
                stringBuilder.append(c);
            } else {
                // 连续的信息
                int consequentLen = stringBuilder.length();
                // 如果包含连续的信息，则优先处理连续信息
                // 这里的长度也很有趣，比如如果只有 1 个，实际上不需要处理。
                // 但是我们为了接口处理的统一，同时为1的概率也很低，此处把1当做特殊的场景处理。
                if (consequentLen > 0) {
                    int startIndex = i - consequentLen;
                    segmentList.addAll(getSegmentResultList(stringBuilder, startIndex, context));
                }

                // 非连续的符号，简单起见，全部当做单个字符处理。
                segmentList.addAll(singleCharSegment(string, i));
            }
        }

        // 最后的结果处理
        int consequentLen = stringBuilder.length();
        if (consequentLen > 0) {
            int startIndex = length - consequentLen;
            segmentList.addAll(getSegmentResultList(stringBuilder, startIndex, context));
        }

        return segmentList;
    }

    /**
     * 获取分词的结果列表
     * （1）这里是循环 i，然后构建所有可能的结果。
     * @param stringBuilder 字符串
     * @param startIndex 开始下标
     * @param segmentContext 上下文
     * @return 结果列表
     * @since 0.0.7
     */
    private List<ISegmentResult> getSegmentResultList(final StringBuilder stringBuilder,
                                                      final int startIndex,
                                                      final ISegmentContext segmentContext) {
        //1. 所有的 dict 分词结果
        final String text = stringBuilder.toString();
        final ISegmentMode segmentMode = segmentContext.mode();

        //2. 上下文
        SegmentModeContext modeContext = SegmentModeContext.newInstance()
                .string(text)
                .startIndex(startIndex)
                .segmentContext(segmentContext)
                ;

        // 3. 长度清空
        stringBuilder.setLength(0);

        // 4. 返回结果
        return segmentMode.select(modeContext);
    }

    /**
     * 单个字符的处理
     *
     * @param string     字符串
     * @param startIndex 开始下标
     * @return 结果
     * @since 0.0.7
     */
    private List<ISegmentResult> singleCharSegment(final String string,
                                                   final int startIndex) {
        SegmentModeContext currentCharContext = SegmentModeContext.newInstance()
                .string(string)
                .startIndex(startIndex);
        return SegmentModes.single().select(currentCharContext);
    }

}
