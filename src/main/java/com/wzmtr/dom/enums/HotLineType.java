package com.wzmtr.dom.enums;

import lombok.Getter;

/**
 * 热线类别
 * @Author: Li.Wang
 * Date: 2024/3/21 14:37
 */
@Getter
public enum HotLineType {
    COMPLAIN(0, "投诉"),
    PRAISE(0, "表扬"),
    SUGGEST(0, "建议"),
    CONSULT(0, "咨询"),
    HELP(0, "求助"),
    TRANSFER(0, "转接S1"),
    ELSE(0, "其他"),
    APP(0, "温州轨道APP");

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
