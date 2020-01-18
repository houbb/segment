package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.data.impl.SegmentDatas;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 分词引导类实现
 * @author binbin.hou
 * @since 0.0.3
 */
@Ignore
public class SegmentBsDefineTest {

    /**
     * 自定义分词测试
     * @since 0.0.3
     */
    @Test
    public void segmentTest() {
        final String text = "自定义一个很长的分词，开心！";

        List<ISegmentResult> resultList = SegmentBs.newInstance().segmentData(SegmentDatas.define()).segment(text);
        Assert.assertEquals("[自定义一个很长的分词[0,10), ，[10,11), 开[11,12), 心[12,13), ！[13,14)]", resultList.toString());
    }

}
