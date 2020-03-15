package com.github.houbb.segment.support.tagging.pos.data.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.segment.model.SegmentPos;
import com.github.houbb.segment.support.tagging.pos.data.ISegmentPosData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 分词数据抽象父类实现
 * （1）惰性加载
 *
 * @author binbin.hou
 * @since 0.1.4
 */
@ThreadSafe
public abstract class AbstractSegmentPosData implements ISegmentPosData {

    /**
     * 获取静态词列表
     * @return 词列表
     * @since 0.1.4
     */
    protected abstract Map<String, List<SegmentPos>> getStaticVolatilePosMap();

    /**
     * 获取行内容
     * @return 字典行内容
     * @since 0.1.3
     */
    protected abstract List<String> readDictLines();

    @Override
    public List<SegmentPos> posList(String word) {
        if(StringUtil.isEmptyTrim(word)) {
            return Collections.emptyList();
        }

        Map<String, List<SegmentPos>> posMap = getPosMap();
        return posMap.get(word);
    }

    /**
     * 获取词性集合
     * @return 集合
     * @since 0.1.4
     */
    private Map<String, List<SegmentPos>> getPosMap() {
        Map<String, List<SegmentPos>> map = getStaticVolatilePosMap();
        if(MapUtil.isNotEmpty(map)) {
            return map;
        }

        final long startMills = System.currentTimeMillis();
        // 初始化 DLC
        synchronized (AbstractSegmentPosData.class) {
            if(MapUtil.isEmpty(map)) {
                List<String> lines = readDictLines();

                for(String line : lines) {
                    if(StringUtil.isEmptyTrim(line)) {
                        continue;
                    }

                    String[] strings = line.split(StringUtil.BLANK);
                    String word = strings[0];

                    List<String> posStringList = StringUtil.splitToList(strings[1], PunctuationConst.COMMA);
                    List<SegmentPos> posList = Guavas.newArrayList(posStringList.size());
                    for(String pos : posStringList) {
                        String[] poss = pos.split(PunctuationConst.COLON);
                        String name = poss[0];
                        long count = Long.parseLong(poss[1]);
                        posList.add(SegmentPos.of(name, count));
                    }

                    map.put(word, posList);
                }
            }
        }

        final long endMills = System.currentTimeMillis();
        System.out.println("[Segment Pos] segment pos dict init finish, cost " + (endMills-startMills) + " ms!");
        return map;
    }

}
