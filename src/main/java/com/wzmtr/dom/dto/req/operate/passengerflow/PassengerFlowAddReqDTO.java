package com.wzmtr.dom.dto.req.operate.passengerflow;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/26 18:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerFlowAddReqDTO extends BaseEntity {

    /**
     * 数据所属日期
     */
    @ApiModelProperty("数据所属日期")
    private String dataDate;
    /**
     * 数据类型 1日报 2周报 3月报
     */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;
    /**
     * 数据起始日期
     */
    @ApiModelProperty("数据起始日期")
    private String startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    private String endDate;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
     * 本日进站
     */
    @ApiModelProperty("本日进站")
    private String todayInCount;

    /**
     * 正常进站客流
     */
    @ApiModelProperty("正常进站客流")
    private String normEntrance;
    /**
     * 工作卡进站客流
     */
    @ApiModelProperty("工作卡进站客流")
    private String workEntrance;
    /**
     * 本日出站
     */
    @ApiModelProperty("本日出站")
    private String todayOutCount;
    /**
     * 正常出站客流
     */
    @ApiModelProperty("正常出站客流")
    private String normExit;
    /**
     * 工作卡出站客流
     */
    @ApiModelProperty("工作卡出站客流")
    private String workExit;
    /**
     * S1线换入S2线客流
     */
    @ApiModelProperty("S1线换入S2线客流")
    private String s1S2Transfer;
    /**
     * 本日客运量
     */
    @ApiModelProperty("本日客运量")
    private String dailyPass;
    /**
     * 月累计客运量
     */
    @ApiModelProperty("月累计客运量")
    private String monthPass;
    /**
     * 年累计客运量
     */
    @ApiModelProperty("年累计客运量")
    private String yearPass;
    /**
     * 开通至今累计客运量
     */
    @ApiModelProperty("开通至今累计客运量")
    private String totalPass;
    /**
     * 本月日均客运量
     */
    @ApiModelProperty("本月日均客运量")
    private String dayAvgThisMonth;
    /**
     * 年累计日均客运量
     */
    @ApiModelProperty("年累计日均客运量")
    private String dayAvgYear;
    /**
     * 开通至今累计日均客运量
     */
    @ApiModelProperty("开通至今累计日均客运量")
    private String dayAvgTotal;
    /**
     * 小时单向最大断面位置
     */
    private String hourMaxPosition;
    /**
     * 小时单向最大断面客流量
     */
    private String hourMaxPass;
    /**
     * 小时单向最大断面开始时间
     */
    private String hourMaxTimeStart;
    /**
     * 小时单向最大断面结束时间
     */
    private String hourMaxTimeEnd;
}
