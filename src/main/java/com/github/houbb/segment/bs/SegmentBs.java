package com.github.houbb.segment.bs;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.segment.api.ISegment;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.data.phrase.api.ISegmentPhraseData;
import com.github.houbb.segment.data.phrase.core.data.SegmentPhraseDatas;
import com.github.houbb.segment.data.pos.api.ISegmentPosData;
import com.github.houbb.segment.data.pos.core.data.SegmentPosDatas;
import com.github.houbb.segment.support.format.ISegmentFormat;
import com.github.houbb.segment.support.format.impl.SegmentFormats;
import com.github.houbb.segment.support.segment.impl.SegmentContext;
import com.github.houbb.segment.support.segment.impl.Segments;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.ISegmentResultHandler;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.support.tagging.pos.tag.ISegmentPosTagging;
import com.github.houbb.segment.support.tagging.pos.tag.impl.SegmentPosTaggings;
import com.github.houbb.segment.support.trie.ISegmentTrieTree;
import com.github.houbb.segment.support.trie.impl.SegmentTrieTrees;
import com.github.houbb.segment.support.viterbi.IViterbi;
import com.github.houbb.segment.support.viterbi.Viterbis;

import java.util.List;

/**
 * 分词引导类
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public final class SegmentBs implements ISegmentBs {

    /**
     * 分词实现
     * @since 0.0.1
     */
    private ISegment segment = Segments.defaults();

    /**
     * 分词数据来源
     * 1. 默认使用混合模式的字典(v1.0.3)
     * @since 0.0.3
     */
    private ISegmentPhraseData segmentData = SegmentPhraseDatas.mixed();

    /**
     * 分词模式
     *
     * 1. 默认使用贪婪模式
     *
     * @since 0.0.5
     */
    private ISegmentMode segmentMode = SegmentModes.search();

    /**
     * 格式化信息
     * @since 0.0.9
     */
    private ISegmentFormat segmentFormat = SegmentFormats.defaults();

    /**
     * 词性信息实现
     * @since 0.1.4
     */
    private ISegmentPosTagging posTagging = SegmentPosTaggings.none();

    /**
     * 词性数据实现
     * @since 0.1.4
     */
    private ISegmentPosData posData = SegmentPosDatas.mixed();

    /**
     * 分词前缀树
     * @since 0.2.0
     */
    private final ISegmentTrieTree segmentTrieTree = SegmentTrieTrees.defaults();

    /**
     * 维特比算法
     * @since 0.2.0
     */
    private final IViterbi viterbi = Viterbis.defaults();

    /**
     * 引导类
     * @since 0.0.1
     */
    private SegmentBs(){}

    /**
     * 创建一个新的实例
     * @return this
     * @since 0.0.1
     */
    public static SegmentBs newInstance() {
        return new SegmentBs();
    }

    /**
     * 设置分词实现
     * @since 0.4.0
     * @param segment 分词实现
     * @return this
     */
    public SegmentBs segment(ISegment segment) {
        ArgUtil.notNull(segment, "segment");

        this.segment = segment;
        return this;
    }

    /**
     * 指定分词的数据实现
     * @param segmentData 分词数据
     * @return this
     * @since 0.0.3
     */
    public SegmentBs segmentData(final ISegmentPhraseData segmentData) {
        ArgUtil.notNull(segmentData, "segmentData");
        this.segmentData = segmentData;
        return this;
    }

    /**
     * 设置分词模式
     * @param segmentMode 分词模式
     * @return this
     * @since 0.0.5
     */
    public SegmentBs segmentMode(ISegmentMode segmentMode) {
        ArgUtil.notNull(segmentMode, "segmentMode");
        this.segmentMode = segmentMode;
        return this;
    }

    /**
     * 设置数据格式化
     * @param segmentFormat 格式化
     * @return this
     */
    public SegmentBs segmentFormat(final ISegmentFormat segmentFormat) {
        ArgUtil.notNull(segmentFormat, "segmentFormat");
        this.segmentFormat = segmentFormat;
        return this;
    }

    /**
     * 设置词性标注实现
     * @param posTagging 实现
     * @return this
     * @since 0.1.4
     */
    public SegmentBs posTagging(ISegmentPosTagging posTagging) {
        this.posTagging = posTagging;
        return this;
    }

    /**
     * 设置词性标注数据
     * @param posData 实现
     * @return this
     * @since 0.1.4
     */
    public SegmentBs posData(ISegmentPosData posData) {
        this.posData = posData;
        return this;
    }

    /**
     * 直接执行分词
     * @param string 字符串
     * @return 结果
     * @since 0.0.1
     */
    @Override
    public List<ISegmentResult> segment(final String string) {
        return segment(string, SegmentResultHandlers.common());
    }

    /**
     * 分词处理
     * @param string 原始字符串
     * @param handler 处理类
     * @param <R> 泛型
     * @return 处理后的结果
     * @since 0.0.4
     */
    public <R> R segment(final String string, final ISegmentResultHandler<R> handler) {
        final ISegmentContext context = buildContext();
        List<ISegmentResult> segmentResults = segment.segment(string, context);
        return handler.handler(segmentResults);
    }

    /**
     * 构建上下文
     * @return 上下文
     * @since 0.0.3
     */
    private ISegmentContext buildContext() {
        return SegmentContext.newInstance()
                .data(segmentData)
                .mode(segmentMode)
                .format(segmentFormat)
                .posTagging(posTagging)
                .posData(posData)
                .segmentTrieTree(segmentTrieTree)
                .viterbi(viterbi)
                ;
    }

    @Override
    public synchronized void destroy() {
        this.segmentData.destroy();
        this.segmentFormat.destroy();
        this.posData.destroy();
        this.segmentTrieTree.destroy();
        this.viterbi.destroy();
    }

}
