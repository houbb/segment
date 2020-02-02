package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.format.impl.SegmentFormats;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.util.CharUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 分词格式化实现
 * @author binbin.hou
 * @since 0.0.9
 */
public class SegmentBsFormatTest {

    /**
     * 默认的格式化处理
     * （1）支持大小写
     * （2）支持全角半角
     * @since 0.0.9
     */
    @Test
    public void defaultsFormatTest() {
        String text = "阿Ｑ精神";
        List<ISegmentResult> segmentResults = SegmentBs.newInstance().segment(text);

        Assert.assertEquals("[阿Ｑ[0,2), 精神[2,4)]", segmentResults.toString());
    }

    /**
     * 默认的格式化处理
     * （1）支持大小写
     * （2）支持全角半角
     * @since 0.0.9
     */
    @Test
    public void defaultsFormatTest2() {
        char c = SegmentFormats.defaults().format('Ｑ', null);
        Assert.assertEquals('q', c);
    }

    /**
     * 中文简体格式化分词测试
     * @since 0.1.2
     */
    @Test
    public void chineseSimpleFormatSegTest() {
        String text = "這是一個伸手不見五指的黑夜";

        List<String> defaultWords = SegmentBs.newInstance()
                .segment(text, SegmentResultHandlers.word());
        Assert.assertEquals("[這是, 一, 個, 伸手, 不見, 五指, 的, 黑夜]", defaultWords.toString());
    }

    /**
     * @since 0.1.2
     */
    @Test
    public void chineseSimpleFormatSegTest2() {
        String text = "這是一個伸手不見五指的黑夜";

        List<String> defaultWords = SegmentBs.newInstance()
                .segmentFormat(SegmentFormats.chineseSimple())
                .segment(text, SegmentResultHandlers.word());
        Assert.assertEquals("[這是, 一個, 伸手不見五指, 的, 黑夜]", defaultWords.toString());
    }

    /**
     * 格式化责任链
     * @since 0.1.2
     */
    @Test
    public void formatChainsTest() {
        final String text = "阿Ｑ，這是一個伸手不見五指的黑夜";

        List<String> defaultWords = SegmentBs.newInstance()
                .segmentFormat(SegmentFormats.chains(SegmentFormats.defaults(),
                        SegmentFormats.chineseSimple()))
                .segment(text, SegmentResultHandlers.word());
        Assert.assertEquals("[阿Ｑ, ，, 這是, 一個, 伸手不見五指, 的, 黑夜]", defaultWords.toString());
    }

}

