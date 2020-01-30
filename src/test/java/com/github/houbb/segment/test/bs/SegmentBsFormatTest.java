package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.format.impl.SegmentFormats;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
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
     */
    @Test
    public void defaultsFormatTest() {
        String text = "阿Ｑ精神";
        List<ISegmentResult> segmentResults = SegmentBs.newInstance().segment(text);

        Assert.assertEquals("[阿Ｑ[0,2), 精神[2,4)]", segmentResults.toString());
    }

    @Test
    public void formatTest() {
        char c = SegmentFormats.defaults().format('Ｑ', null);
        Assert.assertEquals('q', c);
    }

}

