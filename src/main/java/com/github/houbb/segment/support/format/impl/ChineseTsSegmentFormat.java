package com.github.houbb.segment.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.support.format.ISegmentFormat;

import java.util.List;
import java.util.Map;

/**
 * 中文繁体转简体测试
 * <p>
 * （1）主要用于繁体分词
 * （2）基于字典的处理
 * （3）基于 HMM 的处理。
 *
 * @author binbin.hou
 * @since 0.1.2
 */
@ThreadSafe
public class ChineseTsSegmentFormat extends AbstractSegmentFormat {

    /**
     * 繁体=》简体
     *
     * @since 0.1.2
     */
    private static volatile Map<Character, Character> TS_CHAR_MAP = Guavas.newHashMap();

    /**
     * DLC 初始化
     */
    private synchronized void initDataMap() {
        if(MapUtil.isNotEmpty(TS_CHAR_MAP)) {
            return;
        }
        synchronized (TS_CHAR_MAP) {
            if(MapUtil.isEmpty(TS_CHAR_MAP)) {
                List<String> lines = StreamUtil.readAllLines(SegmentConst.SEGMENT_CHINESE_TS_CHAR_PATH);
                for (String line : lines) {
                    String[] entries = line.split(StringUtil.BLANK);
                    char tChar = entries[0].charAt(0);
                    char sChar = entries[1].charAt(0);
                    TS_CHAR_MAP.put(tChar, sChar);
                }
            }
        }
    }

    @Override
    public char format(char ch, ISegmentContext context) {
        // map 初始化
        if(MapUtil.isEmpty(TS_CHAR_MAP)) {
            this.initDataMap();
        }

        Character simple = TS_CHAR_MAP.get(ch);
        if (ObjectUtil.isNull(simple)) {
            // 返回本身
            return ch;
        }

        // 返回简体
        return simple;
    }

    @Override
    public void free() {
        synchronized (TS_CHAR_MAP) {
            TS_CHAR_MAP.clear();
        }
    }

}
