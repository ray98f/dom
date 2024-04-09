package com.wzmtr.dom.enums;

import lombok.Getter;

/**
 * @Author: Li.Wang
 * Date: 2024/4/8 10:16
 */
@Getter
public enum DataType {
    DAY("1", "日"),
    WEEK("2", "周"),
    YEAR("3", "年");
    private final String code;
    private final String desc;

    DataType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static DataType getByCode(Integer code) {
        for (DataType value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String value() {
        return desc;
    }

    public String getId() {
        return code;
    }
}