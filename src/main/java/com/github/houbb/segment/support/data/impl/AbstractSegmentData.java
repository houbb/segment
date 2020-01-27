package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.model.WordProperty;
import com.github.houbb.segment.support.data.ISegmentData;
import com.github.houbb.segment.support.normalization.INormalization;
import com.github.houbb.segment.support.normalization.NormalizationResult;
import com.github.houbb.segment.support.normalization.impl.LogNormalization;

import java.util.List;
import java.util.Map;

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
     * 获取静态词列表
     * @return 词列表
     * @since 0.0.3
     */
    protected abstract List<WordEntry> getStaticVolatileWordEntryList();

    /**
     * 获取静态词属性 map
     * @return 词属性 map
     * @since 0.0.3
     */
    protected abstract Map<String, WordProperty> getStaticVolatileWordTypeMap();

    /**
     * 静态异变的 NormalizationResult 结果
     * @since 0.0.7
     */
    private static volatile NormalizationResult normalizationResult;

    /**
     * 获取字典路径
     * @return 字典路径
     */
    protected abstract String getDictPath();

    @Override
    public List<WordEntry> getWordEntryList() {
        List<WordEntry> wordEntries = getStaticVolatileWordEntryList();
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
    public Map<String, WordProperty> getWordTypeMap() {
        Map<String, WordProperty> wordMap = getStaticVolatileWordTypeMap();
        if(MapUtil.isNotEmpty(wordMap)) {
            return wordMap;
        }

        // 首先加载一次字典列表
        synchronized (AbstractSegmentData.class) {
            if(MapUtil.isEmpty(wordMap)) {
                initWordTypeMap(wordMap);
            }
        }

        return wordMap;
    }

    @Override
    public Double getFreq(String word) {
        NormalizationResult normalizationResult = normalization();

        Double freq = normalizationResult.freqMap().get(word);

        // 如果为空，则默认返回最小的频率即可。
        if(ObjectUtil.isNull(freq)) {
            freq = normalizationResult.minFreq();
        }

        return freq;
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

        List<WordEntry> wordEntries = getWordEntryList();
        INormalization normalization = Instances.singleton(LogNormalization.class);
        normalizationResult = normalization.normalization(wordEntries);

        final long costTime = System.currentTimeMillis()-startTime;
        System.out.println("Normalization init finished, cost time : " + costTime + " ms!");
    }

    /**
     * 初始化词性 map
     * @param wordMap 单词属性
     * @since 0.0.3
     */
    private void initWordTypeMap(final Map<String, WordProperty> wordMap) {
        final long startTime = System.currentTimeMillis();

        final String dictPath = getDictPath();
        List<WordEntry> wordEntryList = getWordEntryList();

        for(WordEntry wordEntry : wordEntryList) {
            // 这里是为了后期的词频处理
            WordProperty property = WordProperty.of(wordEntry.count(), wordEntry.type());
            wordMap.put(wordEntry.word(), property);
        }

        final long costTime = System.currentTimeMillis()-startTime;
        System.out.println("[" + dictPath + "] dict init word-type-map finish, cost time : " + costTime + " ms!");
    }

    /**
     * 构建分词列表
     * （1）跳过空白行
     * （2）允许 2-3 列的内容为空，用默认值填充
     * （3）允许整体文件为空
     *
     * @param wordEntryList 单词明细列表
     * @since 0.0.3
     */
    private void initWordEntryList(final List<WordEntry> wordEntryList) {
        final long startTime = System.currentTimeMillis();
        final String dictPath = getDictPath();
        final List<String> allLines = StreamUtil.readAllLines(dictPath);

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

            WordEntry wordEntry = WordEntry.newInstance().word(word)
                    .count(count)
                    .type(type);
            wordEntryList.add(wordEntry);
        }

        final long costTime = System.currentTimeMillis()-startTime;
        System.out.println("[" + dictPath + "] dict init word-list finish, cost time : " + costTime + " ms!");
    }

}
