package com.github.houbb.segment.support.trie.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.segment.api.ISegmentContext;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.data.phrase.api.ISegmentPhraseData;
import com.github.houbb.segment.support.trie.ISegmentTrieTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private static volatile Map innerWordMap;

    @Override
    public Map getTrieTree(final ISegmentContext context) {
        if(ObjectUtil.isNotNull(innerWordMap)) {
            return innerWordMap;
        }

        synchronized(SegmentTrieTree.class) {
            final ISegmentPhraseData segmentData = context.data();
            initInnerWordMap(segmentData);
        }

        return innerWordMap;
    }

    /**
     * 基于前缀树初始化 Map
     * @param segmentData 分词数据实现
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
    private void initInnerWordMap(final ISegmentPhraseData segmentData) {
        // 创建 map
        innerWordMap = Guavas.newHashMap();

        // 加载字典
        Set<String> wordSet = segmentData.getPhraseSet();

        for (String key : wordSet) {
            // 用来按照相应的格式保存敏感词库数据
            char[] chars = key.toCharArray();
            final int size = chars.length;

            // 每一个新词的循环，直接将结果设置为当前 map，所有变化都会体现在结果的 map 中
            Map currentMap = innerWordMap;

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

}
