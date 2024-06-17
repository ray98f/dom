package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 客运部日报结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@Data
public class DailyReportResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * parentId
     */
    @ApiModelProperty(value = "parentId")
    private String parentId;

    /**
     * 日报类型:0总,1客流收益,2服务热线,3安全生产
     */
    @ApiModelProperty(value = "日报类型:0总,1客流收益,2服务热线,3安全生产")
    private String reportType;

    /**
     * 选用客流总体情况 0否1是
     */
    @ApiModelProperty(value = "选用客流总体情况 0否1是")
    private String ifPassenger;

    /**
     * 选用收益总体情况 0否1是
     */
    @ApiModelProperty(value = "选用收益总体情况 0否1是")
    private String ifIncome;

    /**
     * 选用车票使用情况 0否1是
     */
    @ApiModelProperty(value = "选用车票使用情况 0否1是")
    private String ifTicketUse;

    /**
     * 选用单程票销售情况 0否1是
     */
    @ApiModelProperty(value = "选用单程票销售情况 0否1是")
    private String ifOnewaySale;

    /**
     * 选用热线汇总情况 0否1是
     */
    @ApiModelProperty(value = "选用热线汇总情况 0否1是")
    private String ifHotlineSummary;

    /**
     * 选用热线重要情况 0否1是
     */
    @ApiModelProperty(value = "选用热线重要情况 0否1是")
    private String ifHotlineImportant;

    /**
     * 选用转交处理事项 0否1是
     */
    @ApiModelProperty(value = "选用转交处理事项 0否1是")
    private String ifTransmitInfo;

    /**
     * 选用安全生产情况汇总 0否1是
     */
    @ApiModelProperty(value = "选用安全生产情况汇总 0否1是")
    private String ifProductionSummary;

    /**
     * 选用安全生产具体内容 0否1是
     */
    @ApiModelProperty(value = "选用安全生产具体内容 0否1是")
    private String ifProductionInfo;

    /**
     * 日报日期
     */
    @ApiModelProperty(value = "日报日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date dailyDate;

    /**
     * 日报日期
     */
    @ApiModelProperty(value = "日报起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 日报日期
     */
    @ApiModelProperty(value = "日报终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 日报状态
     */
    @ApiModelProperty(value = "日报状态")
    private String status;

    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private String auditor;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;

    /**
     * 可见角色
     */
    @ApiModelProperty(value = "可见角色")
    private String visibleRoles;

    /**
     * 是否可提交审核:0否1是
     */
    @ApiModelProperty(value = "是否可提交审核 0否1是")
    private String submitFlag;

    /**
     * 子报表:客流收益/服务热线/安全生产
     */
    @ApiModelProperty(value = "子报表:客流收益/服务热线/安全生产")
    private List<DailyReportResDTO> subDailyReport;
}
