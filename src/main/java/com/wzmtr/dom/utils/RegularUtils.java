package com.wzmtr.dom.utils;

import java.util.regex.Pattern;

/**
 * 正则工具类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public class RegularUtils {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    /**
     * 数字判断
     * @return Pattern
     */
    public static Pattern getNumberPattern() {
        return NUMBER_PATTERN;
    }

}