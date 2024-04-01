package com.wzmtr.dom.enums;

import lombok.Getter;

/**
 * 热线类别
 * @Author: Li.Wang
 * Date: 2024/3/21 14:37
 */
@Getter
public enum HotLineType {
    COMPLAIN(1, "投诉"),
    PRAISE(2, "表扬"),
    SUGGEST(3, "建议"),
    CONSULT(4, "咨询"),
    HELP(5, "求助"),
    TRANSFER(6, "转接S1"),
    ELSE(7, "其他"),
    APP(8, "温州轨道APP");

    private final Integer code;
    private final String desc;

    HotLineType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static HotLineType getByCode(Integer code) {
        for (HotLineType value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String value() {
        return desc;
    }

    public Integer getId() {
        return code;
    }
}
