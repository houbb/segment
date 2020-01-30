package com.github.houbb.segment.test.benchmark;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Benchmark 作为基准测试，确保公平：
 *
 * （1）相同的硬件环境，不同的机器测试情况会有所不同。
 * （2）预热时间不算入耗时内，二者对比都在 dict 加载完成之后开始计时。
 * （3）benchmark 仅供参考，一切还是以实际使用效果为准。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class BenchmarkTest {

    /**
     * 1w 次
     * @since 0.0.1
     */
    private static final int TIMES = 10000;

    /**
     * 耗时：8809
     */
    @Test
    public void jiebaAnalysisTest() {
        String text = getText();
        JiebaSegmenter segmenter = new JiebaSegmenter();

        // 预热
        segmenter.sentenceProcess(text);

        // 开始验证
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < TIMES; i++) {
            segmenter.sentenceProcess(text);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("jieba cost: " + (endTime-startTime));
    }

    /**
     * Segment cost: 7435
     */
    @Test
    public void segmentTest() {
        String text = getText();
        SegmentBs segmenter = SegmentBs.newInstance();

        // 预热
        segmenter.segment(text);

        // 开始验证
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < TIMES; i++) {
            segmenter.segment(text);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Segment cost: " + (endTime-startTime));
    }

    /**
     * Segment cost: 3050
     */
    @Test
    public void segmentGreedyLengthTest() {
        String text = getText();
        SegmentBs segmenter = SegmentBs.newInstance()
                .segmentMode(SegmentModes.greedyLength());

        // 预热
        segmenter.segment(text);

        // 开始验证
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < TIMES; i++) {
            segmenter.segment(text);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Segment cost: " + (endTime-startTime));
    }

    /**
     * 获取文本内容
     * @return 内容
     * @since 0.0.1
     */
    private String getText() {
        return StreamUtil.getFileContent("永远的夏娃.txt");
    }

}
