package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 分词引导类实现-资源释放
 * @author binbin.hou
 * @since 0.2.0
 */
public class SegmentBsDestroyTest {

    /**
     * 多次调用
     */
    @Test
    public void multiTest() {
        // 基本特性
        final SegmentBs segmentBs = SegmentBs.newInstance();

        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";
        List<ISegmentResult> resultList = segmentBs.segment(string);
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());

        // 重新处理
        List<ISegmentResult> resultList2 = segmentBs.segment(string);
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList2.toString());
    }

    /**
     * 分词测试
     */
    @Test
    public void destroyTest() {
        // 基本特性
        final SegmentBs segmentBs = SegmentBs.newInstance();

        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";
        List<ISegmentResult> resultList = segmentBs.segment(string);
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());

        // 资源释放
        segmentBs.destroy();

        // 重新处理
        List<ISegmentResult> resultList2 = segmentBs.segment(string);
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList2.toString());
    }


}
