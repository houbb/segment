package com.github.houbb.segment.api;

/**
 * 允许释放资源的
 *
 * @since 0.2.0
 * @author dh
 */
public interface IFreeAble {

    /**
     * 释放资源
     */
    void free();

}
