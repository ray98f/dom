package com.wzmtr.dom.dto.req.traffic;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  客运部月报请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthlyReportReqDTO extends BaseEntity {

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
     * 月报类型:0总,1客流收益,2服务热线,3安全生产
     */
    @ApiModelProperty(value = "月报类型:0总,1客流收益,2服务热线,3安全生产")
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
     * 周报起始日期
     */
    @ApiModelProperty(value = "月报起始日期")
    private String startDate;

    /**
     * 周报终止
     */
    @ApiModelProperty(value = "月报终止")
    private String endDate;

    /**
     * 周报状态
     */
    @ApiModelProperty(value = "月报状态")
    private String status;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
