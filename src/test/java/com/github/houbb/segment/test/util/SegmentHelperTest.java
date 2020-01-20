package com.github.houbb.segment.test.util;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.constant.enums.SegmentModeEnum;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.util.SegmentHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我[20,21), 爱[21,22), 北京[22,24), ，[24,25), 我[25,26), 爱[26,27), 学习[27,29), 。[29,30)]", resultList.toString());
    }

    /**
     * 分词测试
     * @since 0.0.6
     */
    @Test
    public void segmentWordTypeTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<ISegmentResult> resultList = SegmentHelper.segment(string, true);
        Assert.assertEquals("[这[0,1)/r, 是[1,2)/v, 一个[2,4)/m, 伸手不见五指[4,10)/i, 的[10,11)/uj, 黑夜[11,13)/n, 。[13,14)/un, 我[14,15)/r, 叫[15,16)/v, 孙悟空[16,19)/nr, ，[19,20)/un, 我[20,21)/r, 爱[21,22)/v, 北京[22,24)/ns, ，[24,25)/un, 我[25,26)/r, 爱[26,27)/v, 学习[27,29)/v, 。[29,30)/un]", resultList.toString());
    }

    /**
     * 分词测试
     * @since 0.0.6
     */
    @Test
    public void segmentCommonTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<ISegmentResult> resultList = SegmentHelper.segment(string, SegmentResultHandlers.common());
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我[20,21), 爱[21,22), 北京[22,24), ，[24,25), 我[25,26), 爱[26,27), 学习[27,29), 。[29,30)]", resultList.toString());
    }

    /**
     * 分词测试
     * @since 0.0.6
     */
    @Test
    public void segmentWordsTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<String> resultList = SegmentHelper.segment(string, SegmentResultHandlers.word());
        Assert.assertEquals("[这, 是, 一个, 伸手不见五指, 的, 黑夜, 。, 我, 叫, 孙悟空, ，, 我, 爱, 北京, ，, 我, 爱, 学习, 。]", resultList.toString());
    }

    /**
     * 贪婪匹配模式测试
     * @since 0.0.6
     */
    @Test
    public void greedyModeTest() {
        final String string = "这是一个伸手不见五指的黑夜。";

        List<ISegmentResult> resultList = SegmentHelper.segment(string, SegmentModeEnum.GREEDY);
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
    }

    /**
     * 全匹配模式测试
     * @since 0.0.5
     */
    @Test
    public void allModeTest() {
        final String string = "这是一个伸手不见五指的黑夜。";

        List<ISegmentResult> resultList = SegmentHelper.segment(string, SegmentModeEnum.ALL);
        Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手[4,6), 伸手不见[4,8), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
    }

}
