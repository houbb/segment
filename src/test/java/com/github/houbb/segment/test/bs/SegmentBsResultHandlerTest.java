package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 分词引导类-结果处理类实现
 * @author binbin.hou
 * @since 0.0.4
 */
public class SegmentBsResultHandlerTest {

    /**
     * 分词测试-普通的结果返回
     * @since 0.0.4
     */
    @Test
    public void segmentCommonTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<ISegmentResult> resultList = SegmentBs.newInstance()
                .segment(string, SegmentResultHandlers.common());
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());
    }

    /**
     * 分词测试-只返回分词
     * @since 0.0.4
     */
    @Test
    public void segmentWordsTest() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<String> resultList = SegmentBs.newInstance()
                .segment(string, SegmentResultHandlers.word());
        Assert.assertEquals("[这是, 一个, 伸手不见五指, 的, 黑夜, 。, 我, 叫, 孙悟空, ，, 我爱, 北京, ，, 我爱, 学习, 。]",
                resultList.toString());
    }

}
