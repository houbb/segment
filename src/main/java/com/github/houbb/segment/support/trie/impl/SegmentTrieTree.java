package com.github.houbb.segment.support.trie.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.model.WordEntry;
import com.github.houbb.segment.support.data.impl.SegmentData;
import com.github.houbb.segment.support.trie.ISegmentTrieTree;
import com.github.houbb.segment.support.segment.Segment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分词前缀树接口
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SegmentTrieTree implements ISegmentTrieTree {

    /**
     * 内部单词 map
     *
     * @since 0.0.1
     */
    private static final Map INNER_WORD_MAP;

    static {
        synchronized (Segment.class) {
            // 创建 map
            INNER_WORD_MAP = Guavas.newHashMap();

            // 初始化 map
            initInnerWordMap();
        }
    }

    /**
     * 基于前缀树初始化 Map
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
    private static void initInnerWordMap() {
        // 加载字典
        List<WordEntry> wordEntries = Instances.singleton(SegmentData.class)
                .getWordData();

        for (WordEntry wordEntry : wordEntries) {
            final String key = wordEntry.word();
            // 用来按照相应的格式保存敏感词库数据
            char[] chars = key.toCharArray();
            final int size = chars.length;

            // 每一个新词的循环，直接将结果设置为当前 map，所有变化都会体现在结果的 map 中
            Map currentMap = INNER_WORD_MAP;

            for (int i = 0; i < size; i++) {
                // 截取敏感词当中的字，在敏感词库中字为HashMap对象的Key键值
                char charKey = chars[i];
                // 如果集合存在
                Object wordMap = currentMap.get(charKey);

                // 如果集合存在
                if (ObjectUtil.isNotNull(wordMap)) {
                    // 直接将获取到的 map 当前当前 map 进行继续的操作
                    currentMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个新的map，同时将isEnd设置为0，因为他不是最后一
                    Map<String, Boolean> newWordMap = new HashMap<>(8);
                    newWordMap.put(SegmentConst.IS_END, false);

                    // 将新的节点放入当前 map 中
                    currentMap.put(charKey, newWordMap);

                    // 将新节点设置为当前节点，方便下一次节点的循环。
                    currentMap = newWordMap;
                }

                // 判断是否为最后一个，添加是否结束的标识。
                if (i == size - 1) {
                    currentMap.put(SegmentConst.IS_END, true);
                }
            }
        }
    }

    @Override
    public Map getTrieTree() {
        return INNER_WORD_MAP;
    }

    /**
     * 判断是否结束
     * BUG-FIX: 避免出现敏感词库中没有的文字。
     * @param map map 信息
     * @return 是否结束
     * @since 0.0.1
     */
    public static boolean isEnd(final Map map) {
        if(ObjectUtil.isNull(map)) {
            return false;
        }

        Object value = map.get(SegmentConst.IS_END);
        if(ObjectUtil.isNull(value)) {
            return false;
        }

        return (boolean)value;
    }

}
