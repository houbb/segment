package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.SegmentWordEntry;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.normalization.INormalization;
import com.github.houbb.segment.support.normalization.NormalizationResult;
import com.github.houbb.segment.support.normalization.impl.LogNormalization;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分词数据抽象父类实现
 * （1）惰性加载
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public abstract class AbstractSegmentData implements ISegmentData {

    /**
     * 静态异变的 NormalizationResult 结果
     * @since 0.0.7
     */
    private static volatile NormalizationResult normalizationResult;

    /**
     * 获取静态词列表
     * @return 词列表
     * @since 0.0.3
     */
    protected abstract List<SegmentWordEntry> getStaticVolatileWordEntryList();

    /**
     * 获取行内容
     * @return 字典行内容
     * @since 0.1.3
     */
    protected abstract List<String> readDictLines();

    /**
     * 获取对应的词信息
     *
     * ps: 即使列表为空，依然可以分词。
     * 没有 HMM 之前，就相当于与全部为单个字。
     *
     * @return 词信息列表
     * @since 0.0.1
     */
    private List<SegmentWordEntry> getWordEntryList() {
        List<SegmentWordEntry> wordEntries = getStaticVolatileWordEntryList();
        if(CollectionUtil.isNotEmpty(wordEntries)) {
            return wordEntries;
        }

        synchronized (AbstractSegmentData.class) {
            if(CollectionUtil.isEmpty(wordEntries)) {
                // 设置一次
                initWordEntryList(wordEntries);
            }
        }

        return wordEntries;
    }

    @Override
    public Set<String> getWordSet() {
        return getFreqMap().keySet();
    }

    @Override
    public Double getFreq(String word) {
        Map<String, Double> freqMap = getFreqMap();

        Double freq = freqMap.get(word);

        // 如果为空，则默认返回最小的频率即可。
        if(ObjectUtil.isNull(freq)) {
            freq = normalizationResult.minFreq();
        }

        return freq;
    }

    /**
     * 获取频率对应的 Map
     * @return 频率 map
     * @since 0.1.0
     */
    private Map<String, Double> getFreqMap() {
        NormalizationResult normalizationResult = normalization();

        return normalizationResult.freqMap();
    }

    @Override
    public double getMinFreq() {
        NormalizationResult normalizationResult = normalization();

        return normalizationResult.minFreq();
    }

    /**
     * 标准化的结果
     * @return 标准化
     * @since 0.0.7
     */
    private NormalizationResult normalization() {
        if(ObjectUtil.isNotNull(normalizationResult)) {
            return normalizationResult;
        }

        synchronized (NormalizationResult.class) {
            if(ObjectUtil.isNull(normalizationResult)) {
                initNormalizationResult();
            }
        }
        return normalizationResult;
    }

    /**
     * 初始化标准化结果
     * @since 0.0.7
     */
    private void initNormalizationResult() {
        final long startTime = System.currentTimeMillis();

        List<SegmentWordEntry> wordEntries = getWordEntryList();
        INormalization normalization = Instances.singleton(LogNormalization.class);
        normalizationResult = normalization.normalization(wordEntries);

        //清空原始列表
        wordEntries.clear();

        final long costTime = System.currentTimeMillis()-startTime;
        System.out.println("Normalization init finished, cost time : " + costTime + " ms!");
    }

    @Override
    public boolean contains(String word) {
        NormalizationResult normalizationResult = normalization();
        return normalizationResult.freqMap().containsKey(word);
    }



    /**
     * 构建分词列表
     * （1）跳过空白行
     * （2）允许 2-3 列的内容为空，用默认值填充
     * （3）允许整体文件为空
     *
     * @param segmentWordEntryList 单词明细列表
     * @since 0.0.3
     */
    private void initWordEntryList(final List<SegmentWordEntry> segmentWordEntryList) {
        final long startTime = System.currentTimeMillis();
        final List<String> allLines = this.readDictLines();

        for(String line : allLines) {
            if(StringUtil.isEmptyTrim(line)) {
                // 跳过空白行
                continue;
            }

            List<String> splitList = StringUtil.splitToList(line, SegmentConst.SEGMENT_DICT_SPLITTER);
            String word = splitList.get(0);
            long count = SegmentConst.DEFAULT_WORD_COUNT;
            String type = SegmentConst.DEFAULT_WORD_TYPE;

            if(splitList.size() >= 2) {
                count = Long.parseLong(splitList.get(1));
            }
            if(splitList.size() >= 3) {
                type = splitList.get(2);
            }

            SegmentWordEntry segmentWordEntry = SegmentWordEntry.newInstance().word(word)
                    .count(count)
                    .type(type);
            segmentWordEntryList.add(segmentWordEntry);
        }

        final long costTime = System.currentTimeMillis()-startTime;
        System.out.println("[Segment] dict init word-list finish, cost time : " + costTime + " ms!");
    }

}
