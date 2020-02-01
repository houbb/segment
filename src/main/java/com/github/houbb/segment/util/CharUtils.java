package com.github.houbb.segment.util;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;

/**
 * 字符工具类
 * @author binbin.hou
 * @since 0.0.7
 */
public final class CharUtils {

    private CharUtils(){}

    /**
     * 连接符号
     * @since 0.0.7
     */
    private static final char[] CONNECTOR_CHARS = "+#&.-_'".toCharArray();

    /**
     * 是否为连续的信息
     * @param ch 字符串
     * @return 结果
     * @since 0.0.7
     */
    public static boolean isConsequent(final char ch) {
        return CharUtil.isChinese(ch)
                || CharUtil.isDigitOrLetter(ch)
                || CharUtil.isDigit(ch)
                || isConnectorChars(ch);
    }

    /**
     * 是否为连接符号
     * @param ch 字符
     * @return 结果
     * @since 0.1.0
     */
    public static boolean isConnectorChars(final char ch) {
        return ArrayPrimitiveUtil.contains(CONNECTOR_CHARS, ch);
    }

    /**
     * 是英文或者连接符号
     * @param ch 字符
     * @return 是否
     * @since 0.1.0
     */
    public static boolean isLetterOrConnector(final char ch) {
        return ch == '\'' || CharUtil.isEnglish(ch);
    }

}
