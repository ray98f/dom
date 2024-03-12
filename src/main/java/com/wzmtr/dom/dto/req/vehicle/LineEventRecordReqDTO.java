package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LineEventRecordReqDTO extends BaseEntity {

    @ApiModelProperty(value = "日期")
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String day;

    @ApiModelProperty(value = "版本号")
    private String version;
}
