package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 日数据请求类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IndicatorReqDTO extends BaseEntity {

    @ApiModelProperty(value = "日期")
    @NotNull(message = "32000006")
    private String day;

    @ApiModelProperty(value = "责任晚点统计")
    private Number delayCount;
}
