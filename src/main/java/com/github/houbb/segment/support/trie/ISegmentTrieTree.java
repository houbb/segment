package com.github.houbb.segment.support.trie;

import com.github.houbb.common.lifecycle.api.IDestroyAble;
import com.github.houbb.segment.api.ISegmentContext;

import java.util.Map;

/**
 * 分词前缀树接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISegmentTrieTree extends IDestroyAble {

    /**
     * 获取对应的词信息
     *
     * ps: 即使列表为空，依然可以分词。
     * 没有 HMM 之前，就相当于与全部为单个字。
     *
     * @param context 上下文
     * @return 词信息列表
     * @since 0.0.1
     */
    Map getTrieTree(final ISegmentContext context);

}
