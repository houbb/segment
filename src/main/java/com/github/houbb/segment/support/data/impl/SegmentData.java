package com.github.houbb.segment.support.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.support.data.ISegmentData;

import java.util.List;

/**
 * 分词数据实现
 * （1）惰性加载
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SegmentData implements ISegmentData {

    /**
     * 默认的内置行
     *
     * @since 0.0.1
     */
    private static final List<WordEntry> WORD_ENTRY_LIST;

    static {
        synchronized (SegmentData.class) {
            WORD_ENTRY_LIST = Guavas.newArrayList();
            long startTime = System.currentTimeMillis();
            List<String> allLines = StreamUtil.readAllLines(SegmentConst.SEGMENT_DICT_PATH);
            WORD_ENTRY_LIST.addAll(buildWordEntryList(allLines));

            long endTime = System.currentTimeMillis();
            System.out.println("Segment data loaded!, cost time: " + (endTime - startTime) + "ms");
        }
    }

    /**
     * 构建分词列表
     * （1）跳过空白行
     * （2）允许 2-3 列的内容为空，用默认值填充
     * （3）允许整体文件为空
     *
     * @param allLines 所有的内容
     * @return 分词结果
     * @since 0.0.1
     */
    private static List<WordEntry> buildWordEntryList(final List<String> allLines) {
        List<WordEntry> resultList = Guavas.newArrayList(allLines.size());

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
                count = Long.valueOf(splitList.get(1));
            }
            if(splitList.size() >= 3) {
                type = splitList.get(2);
            }

            WordEntry wordEntry = WordEntry.newInstance().word(word)
                    .count(count)
                    .type(type);
            resultList.add(wordEntry);
        }

        return resultList;
    }

    @Override
    public List<WordEntry> getData() {
        return WORD_ENTRY_LIST;
    }

}
