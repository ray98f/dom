package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 运营日报请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DailyReportReqDTO extends BaseEntity {

    /**
     * 选用客流数据 0否1是
     */
    @ApiModelProperty(value = "选用客流数据 0否1是")
    private String ifPassenger;

    /**
     * 选用初期指标 0否1是
     */
    @ApiModelProperty(value = "选用初期指标 0否1是")
    private String ifIndicator;

    /**
     * 选用运营事件 0否1是
     */
    @ApiModelProperty(value = "选用运营事件 0否1是")
    private String ifEvent;

    /**
     * 选用施工 0否1是
     */
    @ApiModelProperty(value = "选用施工 0否1是")
    private String ifConstruct;

    /**
     * 选用调试 0否1是
     */
    @ApiModelProperty(value = "选用调试 0否1是")
    private String ifDebug;

    /**
     * 选用限速 0否1是
     */
    @ApiModelProperty(value = "选用限速 0否1是")
    private String ifSpeedLimit;

    /**
     * 选用故障统计情况 0否1是
     */
    @ApiModelProperty(value = "选用故障统计情况 0否1是")
    private String ifFaultStatistics;

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
     * 日报状态
     */
    @ApiModelProperty(value = "日报状态")
    private String status;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
