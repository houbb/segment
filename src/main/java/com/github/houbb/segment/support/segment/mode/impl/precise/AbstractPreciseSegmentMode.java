package com.github.houbb.segment.support.segment.mode.impl.precise;

import com.github.houbb.segment.support.segment.mode.ISegmentMode;

/**
 * 抽象精确实现类
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public abstract class AbstractPreciseSegmentMode implements ISegmentMode {

    @Override
    public boolean isFastMode() {
        return false;
    }

}
