package com.wzmtr.dom.dto.res.operate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@Data
public class NewEventCountResDTO {


    /**
     * 掉线
     * */
    @ApiModelProperty(value = "掉线")
    private Integer offLineCount;

    /**
     * 救援
     * */
    @ApiModelProperty(value = "救援")
    private Integer rescueCount;

    /**
     * 清客
     * */
    @ApiModelProperty(value = "清客")
    private Integer rutineGuestCount;

    /**
     * 加开
     * */
    @ApiModelProperty(value = "加开")
    private Integer addCount;

    /**
     * 晚点5分钟内
     * */
    @ApiModelProperty(value = "晚点5分钟内")
    private Integer delay1Count;

    /**
     * 晚点5分钟以上
     * */
    @ApiModelProperty(value = "晚点5分钟以上")
    private Integer delay2Count;

    /**
     * 停运
     * */
    @ApiModelProperty(value = "停运")
    private Integer stopCount;

    /**
     * 延误15分钟内 列次
     * */
    @ApiModelProperty(value = "延误15分钟内 列次")
    private Integer delay1VehicleCount;

    /**
     * 延误15分钟内 件数
     * */
    @ApiModelProperty(value = "延误15分钟内 件数")
    private Integer delay1EventCount;

    /**
     * 延误15分钟外 列次
     * */
    @ApiModelProperty(value = "延误15分钟外 列次")
    private Integer delay2VehicleCount;

    /**
     * 延误15分钟外 件数
     * */
    @ApiModelProperty(value = "延误15分钟外 件数")
    private Integer delay2EventCount;

    /**
     * 站台门故障
     * */
    @ApiModelProperty(value = "站台门故障")
    private Integer gateFaultCount;

    /**
     * 列车退出正线次数
     * */
    @ApiModelProperty(value = "列车退出正线次数")
    private Integer lineFaultCount;

    /**
     * 车辆系统故障数
     * */
    @ApiModelProperty(value = "车辆系统故障数")
    private Integer vehicleFaultCount;

    /**
     * 信号系统故障数
     * */
    @ApiModelProperty(value = "信号系统故障数")
    private Integer signalFaultCount;

    /**
     * 供电系统故障数
     * */
    @ApiModelProperty(value = "供电系统故障数")
    private Integer powerFaultCount;
}
