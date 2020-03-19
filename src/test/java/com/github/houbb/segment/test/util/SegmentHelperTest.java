package com.github.houbb.segment.test.util;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.util.SegmentHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 分词工具类测试
 * @author binbin.hou
 * @since 0.0.6
 */
public class SegmentHelperTest {

    /**
     * 分词测试
     * @since 0.0.6
     */
    @Test
    public void segmentTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<ISegmentResult> resultList = SegmentHelper.segment(string);
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());
    }

    /**
     * 分词词语测试
     * @since 0.0.7
     */
    @Test
    public void segmentWordTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<String> resultList = SegmentHelper.segment(string, SegmentResultHandlers.word());
        Assert.assertEquals("[这是, 一个, 伸手不见五指, 的, 黑夜, 。, 我, 叫, 孙悟空, ，, 我爱, 北京, ，, 我爱, 学习, 。]", resultList.toString());
    }

    /**
     * 分词测试-002
     * @since 0.0.7
     */
    @Test
    public void segmentTest2() {
        final String string = "学。习我的最爱";
        List<ISegmentResult> resultList = SegmentHelper.segment(string);
        Assert.assertEquals("[学[0,1), 。[1,2), 习[2,3), 我[3,4), 的[4,5), 最爱[5,7)]", resultList.toString());
    }

    /**
     * 默认的格式化处理
     * （1）支持大小写
     * （2）支持全角半角
     * @since 0.0.9
     */
    @Test
    public void defaultsFormatTest() {
        String text = "阿Ｑ精神";
        List<ISegmentResult> segmentResults = SegmentHelper.segment(text);

        Assert.assertEquals("[阿Ｑ[0,2), 精神[2,4)]", segmentResults.toString());
    }

    @Test
    public void englishTest() {
        String text = "good 学生爱学习";
        List<ISegmentResult> segmentResults = SegmentHelper.segment(text);

        Assert.assertEquals("[good[0,4),  [4,5), 学生[5,7), 爱[7,8), 学习[8,10)]", segmentResults.toString());
    }

    /**
     * 对于连接符号 ' 的测试
     * @since 0.1.0
     */
    @Test
    public void englishTest2() {
        String text = "iT's time to sleep.";

        List<ISegmentResult> segmentResults = SegmentHelper.segment(text);

        System.out.println(segmentResults.toString());

        Assert.assertEquals("[iT's[0,4),  [4,5), time[5,9),  [9,10), to[10,12),  [12,13), sleep[13,18), .[18,19)]", segmentResults.toString());
    }

    /**
     * 单词个数统计处理
     * @since 0.1.5
     */
    @Test
    public void wordCountTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        Map<String, Integer> wordCount = SegmentHelper.segment(string, SegmentResultHandlers.wordCount());
        Assert.assertEquals(2, wordCount.get("我爱").intValue());
        Assert.assertEquals(1, wordCount.get("黑夜").intValue());
    }

}
