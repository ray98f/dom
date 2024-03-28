package com.wzmtr.dom.dataobject.operate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 运营日报-客流情况记录表
 *
 * @TableName OPERATE_PASSENGER_FLOW_DETAIL
 */
@TableName("OPERATE_PASSENGER_FLOW_DETAIL")
@Data
public class OperatePassengerFlowDetailDO implements Serializable {

    /**
     * ID
     */
    @TableId("ID")
    @ApiModelProperty("ID")
    private String id;
    /**
     * 数据所属日期
     */
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
    @ApiModelProperty("数据起始日期")
    private Date startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    private Date endDate;
    /**
     * 删除标识
     */
    @NotBlank(message = "[删除标识]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("删除标识")
    @Length(max = 255, message = "编码长度不能超过255")
    private String delFlag;
    /**
     * 版本号(乐观锁)
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("版本号(乐观锁)")
    @Length(max = 255, message = "编码长度不能超过255")
    private String version;
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private Date createDate;
    /**
     * 创建者
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("创建者")
    @Length(max = 255, message = "编码长度不能超过255")
    private String createBy;
    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    private Date updateDate;
    /**
     * 更新者
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("更新者")
    @Length(max = 255, message = "编码长度不能超过255")
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
