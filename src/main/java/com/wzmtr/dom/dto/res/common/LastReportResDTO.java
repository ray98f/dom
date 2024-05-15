package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 最新报表结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class LastReportResDTO {

    /**
     * 车辆部日报
     */
    @ApiModelProperty(value = "车辆部日报")
    private String vehicleDaily;

    /**
     * 车辆部周报
     */
    @ApiModelProperty(value = "车辆部周报")
    private String vehicleWeekly;

    /**
     * 车辆部月报
     */
    @ApiModelProperty(value = "车辆部月报")
    private String vehicleMonthly;

    /**
     * 客运部日报
     */
    @ApiModelProperty(value = "客运部日报")
    private String trafficDaily;

    /**
     * 客运部周报
     */
    @ApiModelProperty(value = "客运部周报")
    private String trafficWeekly;

    /**
     * 客运部月报
     */
    @ApiModelProperty(value = "客运部月报")
    private String trafficMonthly;

    /**
     * 运营日报
     */
    @ApiModelProperty(value = "运营日报")
    private String operateDaily;

    /**
     * 运营周报
     */
    @ApiModelProperty(value = "运营周报")
    private String operateWeekly;

    /**
     * 运营月报
     */
    @ApiModelProperty(value = "运营月报")
    private String operateMonthly;
}
