package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    /**
     * 所属日期
     */
    @ApiModelProperty(value = "所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date dataDate;

    /**
     * 数据类型 1:日报 2:周报 3:月报
     */
    @ApiModelProperty(value = "数据类型 1:日报 2:周报 3:月报")
    private String dataType;

    /**
     * 数据起始日期
     */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据结束日期
     */
    @ApiModelProperty(value = "数据结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;
    /**
     * 变配电
     */
    @ApiModelProperty("变配电")
    private Integer changeDistributionNum;
    /**
     * 接触网
     */
    @ApiModelProperty("接触网")
    private Integer contactNetworkNum;
    /**
     * 通信
     */
    @ApiModelProperty("通信")
    private Integer communicationNum;
    /**
     * 信号
     */
    @ApiModelProperty("信号")
    private Integer signalNum;
    /**
     * 站台门
     */
    @ApiModelProperty("站台门")
    private Integer platformDoorsNum;
    /**
     * 风水电
     */
    @ApiModelProperty("风水电")
    private Integer hydropowerNum;
    /**
     * 房建
     */
    @ApiModelProperty("房建")
    private Integer buildingConstructionNum;
    /**
     * 综合监控
     */
    @ApiModelProperty("综合监控")
    private Integer monitorNum;
    /**
     * AFC
     */
    @ApiModelProperty("AFC")
    private Integer afcNum;
    /**
     * FAS
     */
    @ApiModelProperty("FAS")
    private Integer fasNum;
    /**
     * 电扶梯
     */
    @ApiModelProperty("电扶梯")
    private Integer escalatorNum;
    /**
     * 公务
     */
    @ApiModelProperty("公务")
    private Integer officialDutiesNum;
    /**
     * 桥隧
     */
    @ApiModelProperty("桥隧")
    private Integer bridgeTunnelNum;
    /**
     * 工程车
     */
    @ApiModelProperty("工程车")
    private Integer engineeringVehicleNum;
    /**
     * 车辆
     */
    @ApiModelProperty("车辆")
    private Integer vehicleNum;
}
