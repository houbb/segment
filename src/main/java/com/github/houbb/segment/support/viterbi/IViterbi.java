package com.github.houbb.segment.support.viterbi;

import com.github.houbb.common.lifecycle.api.IDestroyAble;

import java.util.List;

/**
 * 维特比算法
 * @since 0.2.0
 * @author dh
 */
public interface IViterbi extends IDestroyAble {

    /**
     * 基于 v 分词
     * @param text 文本
     * @param format 格式化
     * @return 结果
     * @since 0.2.0
     */
    List<String> viterbi(String text, String format);

}
