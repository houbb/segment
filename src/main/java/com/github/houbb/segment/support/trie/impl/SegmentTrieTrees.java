package com.github.houbb.segment.support.trie.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.segment.support.trie.ISegmentTrieTree;

/**
 * 分词前缀树
 * @author dh
 * @since 0.1.0
 */
public final class SegmentTrieTrees {

    private SegmentTrieTrees(){}

    /**
     * 默认策略
     * @return 实现
     * @since 0.1.0
     */
    public static ISegmentTrieTree defaults() {
        return Instances.singleton(SegmentTrieTree.class);
    }

}
