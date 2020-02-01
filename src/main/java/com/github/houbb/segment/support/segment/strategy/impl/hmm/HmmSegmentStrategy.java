package com.github.houbb.segment.support.segment.strategy.impl.hmm;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.support.segment.impl.SegmentResult;
import com.github.houbb.segment.support.segment.strategy.ISegmentStrategy;

import java.util.List;

/**
 * HMM 分词策略
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class HmmSegmentStrategy implements ISegmentStrategy {

    @Override
    public List<ISegmentResult> segment(String string, int startIndex, ISegmentContext context) {
        List<String> wordList = Guavas.newArrayList();

        // TODO: 所有地方 char 的处理，都应该使用统一的 char-mapping 处理。
        // 这里后期其实除了繁简体，没有必要处理符号信息。因为和字典无关。
        StringBuilder chineseBuffer = new StringBuilder();
        StringBuilder otherBuffer = new StringBuilder();
        for(char c : string.toCharArray()) {
            if(CharUtil.isChinese(c)) {
                chineseBuffer.append(c);
                // 处理其他符号
                processOtherChars(otherBuffer, wordList);
            } else {
                otherBuffer.append(c);
                // 处理中文
            }
        }

        // 中文
        processChineseChars(chineseBuffer, wordList);

        // 英文
        processOtherChars(otherBuffer, wordList);

        // 构建结果列表
        List<ISegmentResult> resultList = Guavas.newArrayList(wordList.size());
        int actualStartIndex = startIndex;
        for(String word : wordList) {
            ISegmentResult segmentResult = SegmentResult.newInstance()
                    .word(word)
                    .startIndex(actualStartIndex)
                    .endIndex(actualStartIndex+word.length());

            actualStartIndex += word.length();
            resultList.add(segmentResult);
        }
        return resultList;
    }

    /**
     * 执行处理中文字符信息
     * @param buffer 缓存信息
     * @param wordList 结果列表
     * @since 0.1.0
     */
    private void processChineseChars(final StringBuilder buffer,
                                     final List<String> wordList) {
        final int length = buffer.length();
        if(length <= 0) {
            return;
        }

        String text = buffer.toString();
        List<String> segments = Viterbi.segment(text);
        wordList.addAll(segments);

        // 清空长度
        buffer.setLength(0);
    }

    /**
     * 处理其他字符
     * （1）数字
     * （2）英文
     * （3）数字+英文
     *
     * 其他字符全部当做单个信息处理。
     *
     * TODO: 这个处理的流程一次又一次的重复，应该想办法抽象一下。
     * @param buffer 缓冲信息
     * @param wordList 字典列表
     * @since 0.1.0
     */
    private void processOtherChars(final StringBuilder buffer,
                                   final List<String> wordList) {
        final int length = buffer.length();
        if(length <= 0) {
            return;
        }

        if(1 == length) {
            wordList.add(buffer.toString());
        } else {
            // 避免使用 Regex
            StringBuilder wordBuffer = new StringBuilder();

            for(int i = 0; i < buffer.length(); i++) {
                char c = buffer.charAt(i);
                if(CharUtil.isDigitOrLetter(c)) {
                    wordBuffer.append(c);
                } else {
                    // 如果不是
                    if(wordBuffer.length() > 0) {
                        wordList.add(wordBuffer.toString());
                        wordBuffer.setLength(0);
                    }

                    // 其他字符，直接添加
                    wordList.add(String.valueOf(c));
                }
            }

            // 是否存在剩余的 buffer
            if(wordBuffer.length() > 0) {
                wordList.add(wordBuffer.toString());
                wordBuffer.setLength(0);
            }
        }

        buffer.setLength(0);
    }

}
