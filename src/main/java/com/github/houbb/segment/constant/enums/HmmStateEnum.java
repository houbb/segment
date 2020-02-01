package com.github.houbb.segment.constant.enums;

/**
 * HMM 状态枚举
 * @author binbin.hou
 * @since 0.1.0
 */
public enum HmmStateEnum {
    /**
     * 各种状态
     * @since 0.1.0
     */
    BEGIN(0, 'B'),
    MIDDLE(1, 'M'),
    END(2, 'E'),
    SINGLE(3, 'S'),
    ;

    private final int index;

    private final char state;

    HmmStateEnum(int index, char state) {
        this.index = index;
        this.state = state;
    }

    public int index() {
        return index;
    }

    public char state() {
        return state;
    }
}
