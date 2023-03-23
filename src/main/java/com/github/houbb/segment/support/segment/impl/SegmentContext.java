package com.github.houbb.segment.support.segment.impl;

import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.data.phrase.api.ISegmentPhraseData;
import com.github.houbb.segment.data.pos.api.ISegmentPosData;
import com.github.houbb.segment.support.format.ISegmentFormat;
import com.github.houbb.segment.support.segment.mode.ISegmentMode;
import com.github.houbb.segment.support.tagging.pos.tag.ISegmentPosTagging;
import com.github.houbb.segment.support.trie.ISegmentTrieTree;
import com.github.houbb.segment.support.viterbi.IViterbi;

/**
 * 分词上下文
 * @author binbin.hou
 * @since 0.0.1
 */
public class SegmentContext implements ISegmentContext {

    /**
     * 词性标注
     * @since 0.1.4
     */
    private ISegmentPosTagging posTagging;

    /**
     * 词性标注数据
     * @since 0.1.4
     */
    private ISegmentPosData posData;

    /**
     * 分词字典
     * @since 0.0.3
     */
    private ISegmentPhraseData data;

    /**
     * 分词模式
     * @since 0.0.5
     */
    private ISegmentMode mode;

    /**
     * 格式化
     * @since 0.0.9
     */
    private ISegmentFormat format;

    /**
     * 分词前缀树
     * @since 0.2.0
     */
    private ISegmentTrieTree segmentTrieTree;

    /**
     * 维特比算法
     * @since 0.2.0
     */
    private IViterbi viterbi;

    /**
     * 创建对象实例
     * @return 实例
     * @since 0.0.1
     */
    public static SegmentContext newInstance() {
        return new SegmentContext();
    }

    @Override
    public ISegmentPosTagging posTagging() {
        return posTagging;
    }

    public SegmentContext posTagging(ISegmentPosTagging posTagging) {
        this.posTagging = posTagging;
        return this;
    }

    @Override
    public ISegmentPosData posData() {
        return posData;
    }

    public SegmentContext posData(ISegmentPosData posData) {
        this.posData = posData;
        return this;
    }

    @Override
    public ISegmentPhraseData data() {
        return data;
    }

    public SegmentContext data(ISegmentPhraseData data) {
        this.data = data;
        return this;
    }

    @Override
    public ISegmentMode mode() {
        return mode;
    }

    public SegmentContext mode(ISegmentMode mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public ISegmentFormat format() {
        return format;
    }

    public SegmentContext format(ISegmentFormat format) {
        this.format = format;
        return this;
    }

    @Override
    public ISegmentTrieTree segmentTrieTree() {
        return segmentTrieTree;
    }

    public SegmentContext segmentTrieTree(ISegmentTrieTree segmentTrieTree) {
        this.segmentTrieTree = segmentTrieTree;
        return this;
    }

    @Override
    public IViterbi viterbi() {
        return viterbi;
    }

    public SegmentContext viterbi(IViterbi viterbi) {
        this.viterbi = viterbi;
        return this;
    }
}
