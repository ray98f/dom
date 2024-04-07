package com.wzmtr.dom.dto.req.operate;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperateFaultStatisticsReqDTO extends BaseEntity {

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;
    /**
     * 车辆故障数量
     */
    @ApiModelProperty("车辆故障数量")
    private Integer vehicleNum;
    /**
     * 供电
     */
    @ApiModelProperty("供电")
    private Integer powerNum;
    /**
     * 信号
     */
    @ApiModelProperty("信号")
    private Integer signalNum;
    /**
     * 通信
     */
    @ApiModelProperty("通信")
    private Integer communicationNum;
    /**
     * 工建
     */
    @ApiModelProperty("工建")
    private Integer industryNum;
    /**
     * 机电
     */
    @ApiModelProperty("机电")
    private Integer mechanismNum;
    /**
     * AFC
     */
    @ApiModelProperty("AFC")
    private Integer afcNum;
    /**
     * 其他
     */
    @ApiModelProperty("其他")
    private Integer elseNum;
}
