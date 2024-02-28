package com.github.houbb.segment.test.bs;

import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.data.phrase.core.data.SegmentPhraseDatas;
import com.github.houbb.segment.data.pos.core.data.SegmentPosDatas;
import com.github.houbb.segment.support.format.impl.SegmentFormats;
import com.github.houbb.segment.support.segment.impl.Segments;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.support.tagging.pos.tag.impl.SegmentPosTaggings;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 分词引导类实现
 * @author binbin.hou
 * @since 0.0.3
 */
public class SegmentBsDefineTest {

    /**
     * 自定义分词测试
     * @since 0.0.3
     */
    @Test
    public void segmentTest() {
        final String text = "自定义一个很长的分词，开心！";

        List<ISegmentResult> resultList = SegmentBs.newInstance()
                // 分词实现策略
                .segment(Segments.defaults())
                // 分词词组数据
                .segmentData(SegmentPhraseDatas.mixed())
                // 分词模式
                .segmentMode(SegmentModes.dict())
                // 格式化处理
                .segmentFormat(SegmentFormats.defaults())
                // 词性标注实现
                .posTagging(SegmentPosTaggings.simple())
                // 词性标注数据
                .posData(SegmentPosDatas.mixed())
                // 对文本进行分词处理
                .segment(text, SegmentResultHandlers.common());
        Assert.assertEquals("[自定义一个很长的分词[0,10)/un, ，[10,11)/un, 开心[11,13)/a, ！[13,14)/un]", resultList.toString());
    }

}
