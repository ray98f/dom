package com.wzmtr.dom.enums;

/**
 * token状态枚举
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public enum TokenStatus {
    /**
     * 过期的
     */
    EXPIRED("EXPIRED"),
    /**
     * 无效的
     */
    INVALID("INVALID"),
    /**
     * 有效的
     */
    VALID("VALID");

    private final String status;

    TokenStatus(String status) {
        this.status = status;
    }

    public String value() {
        return this.status;
    }
}