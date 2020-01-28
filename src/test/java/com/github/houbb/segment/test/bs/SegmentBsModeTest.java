package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 分词模式实现
 * @author binbin.hou
 * @since 0.0.5
 */
@Ignore
public class SegmentBsModeTest {

    /**
     * 贪婪频率模式测试
     * @since 0.0.7
     */
    @Test
    public void greedyFrequencyModeTest() {
        final String string = "这是一个伸手不见五指的黑夜。";

        List<ISegmentResult> resultList = SegmentBs.newInstance()
                .segmentMode(SegmentModes.greedyFrequency())
                .segment(string);
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手[4,6), 不见[6,8), 五指[8,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
    }

    /**
     * 贪婪匹配模式测试
     * @since 0.0.5
     */
    @Test
    public void greedyLengthModeTest() {
        final String string = "这是一个伸手不见五指的黑夜。";

        List<ISegmentResult> resultList = SegmentBs.newInstance()
                .segmentMode(SegmentModes.greedyLength())
                .segment(string);
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
    }

    /**
     * 全匹配模式测试
     * @since 0.0.5
     */
    @Test
    public void allModeTest() {
        final String string = "这是一个伸手不见五指的黑夜。";

        List<ISegmentResult> resultList = SegmentBs.newInstance()
                .segmentMode(SegmentModes.all())
                .segment(string);
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手[4,6), 伸手不见[4,8), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
    }

}

