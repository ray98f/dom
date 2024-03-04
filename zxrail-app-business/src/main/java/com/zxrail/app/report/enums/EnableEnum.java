package com.zxrail.app.report.enums;

import com.mybatisflex.annotation.EnumValue;
import com.zxrail.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnableEnum implements BaseEnum {
    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用"),
    ;

    @EnumValue
    private final Integer code;

    private final String desc;

}
