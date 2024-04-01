package com.wzmtr.dom.dto.res.operate.passengerflow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.dto.res.traffic.PassengerInfoResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/26 17:37
 */
@Data
public class PassengerFlowDetailResDTO {

    /**
     * 车站客流列表
     * */
    @ApiModelProperty(value = "车站客流列表")
    private List<PassengerInfoResDTO> stationPassengerList;


    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 数据所属日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("数据所属日期")
    private Date dataDate;
    /**
     * 数据类型 1日报 2周报 3月报
     */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;
    /**
     * 数据起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("数据起始日期")
    private Date startDate;
    /**
     * 数据结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("数据结束日期")
    private Date endDate;
    /**
     * 删除标识
     */
    @ApiModelProperty("删除标识")
    private String delFlag;
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("创建日期")
    private Date createDate;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String createBy;
    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("更新日期")
    private Date updateDate;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private String updateBy;
    /**
     * 正常进站客流
     */
    @ApiModelProperty("正常进站客流")
    private Integer normEntrance;
    /**
     * 工作卡进站客流
     */
    @ApiModelProperty("工作卡进站客流")
    private Integer workEntrance;
    /**
     * 正常出站客流
     */
    @ApiModelProperty("正常出站客流")
    private Integer normExit;
    /**
     * 工作卡出站客流
     */
    @ApiModelProperty("工作卡出站客流")
    private Integer workExit;
    /**
     * S1线换入S2线客流
     */
    @ApiModelProperty("S1线换入S2线客流")
    private Integer s1S2Transfer;
    /**
     * 本日客运量
     */
    @ApiModelProperty("本日客运量")
    private Integer dailyPass;
    /**
     * 月累计客运量
     */
    @ApiModelProperty("月累计客运量")
    private Integer monthPass;
    /**
     * 年累计客运量
     */
    @ApiModelProperty("年累计客运量")
    private Integer yearPass;
    /**
     * 开通至今累计客运量
     */
    @ApiModelProperty("开通至今累计客运量")
    private Integer totalPass;
    /**
     * 本月日均客运量
     */
    @ApiModelProperty("本月日均客运量")
    private Integer dayAvgThisMonth;
    /**
     * 年累计日均客运量
     */
    @ApiModelProperty("年累计日均客运量")
    private Integer dayAvgYear;
    /**
     * 开通至今累计日均客运量
     */
    @ApiModelProperty("开通至今累计日均客运量")
    private Integer dayAvgTotal;
    /**
     * 本日进站
     */
    private Integer todayInCount;
    /**
     * 本日出站
     */
    private Integer todayOutCount;

    /**
     * 小时单向最大断面位置
     */
    private String hourMaxPosition;
    /**
     * 小时单向最大断面客流量
     */
    private Integer hourMaxPass;
}
