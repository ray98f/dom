package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 车辆部月报结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthlyReportReqDTO extends BaseEntity {

    /**
     * 选用安全运营概述 0否1是
     */
    @ApiModelProperty(value = "选用安全运营概述 0否1是")
    private String ifSecurityRecord;

    /**
     * 选用重要指标 0否1是
     */
    @ApiModelProperty(value = "选用重要指标 0否1是")
    private String ifImportantIndicator;

    /**
     * 选用行车情况 0否1是
     */
    @ApiModelProperty(value = "选用行车情况 0否1是")
    private String ifDrivingRecord;

    /**
     * 选用正线/车场事件 0否1是
     */
    @ApiModelProperty(value = "选用正线/车场事件 0否1是")
    private String ifLineEvent;

    /**
     * 选用下塘调车/施工 0否1是
     */
    @ApiModelProperty(value = "选用下塘调车/施工 0否1是")
    private String ifXtConstruct;

    /**
     * 选用汀田调车/施工 0否1是
     */
    @ApiModelProperty(value = "选用汀田调车/施工 0否1是")
    private String ifTtConstruct;

    /**
     * 选用乘务中心行车事件总结 0否1是
     */
    @ApiModelProperty(value = "选用乘务中心行车事件总结 0否1是")
    private String ifCrewEventSummary;

    /**
     * 选用乘务中心班组培训情况 0否1是
     */
    @ApiModelProperty(value = "选用乘务中心班组培训情况 0否1是")
    private String ifCrewTrain;

    /**
     * 选用调度命令 0否1是
     */
    @ApiModelProperty(value = "选用调度命令 0否1是")
    private String ifDispatch;

    /**
     * 选用乘务中心演练情况 0否1是
     */
    @ApiModelProperty(value = "选用乘务中心演练情况 0否1是")
    private String ifCrewDrill;

    /**
     * 选用乘务中心业务情况 0否1是
     */
    @ApiModelProperty(value = "选用乘务中心业务情况 0否1是")
    private String ifCrewBusiness;

    /**
     * 选用其他情况 0否1是
     */
    @ApiModelProperty(value = "选用其他情况 0否1是")
    private String ifOtherRecord;

    /**
     * 选用恶劣天气 0否1是
     */
    @ApiModelProperty(value = "选用恶劣天气 0否1是")
    private String ifBadWeather;

    /**
     * 选用乘务中心注意事项情况 0否1是
     */
    @ApiModelProperty(value = "选用乘务中心注意事项情况 0否1是")
    private String ifCrewAttention;

    /**
     * 选用乘务中心情况总结 0否1是
     */
    @ApiModelProperty(value = "选用乘务中心情况总结 0否1是")
    private String ifCrewSummary;

    /**
     * 周报起始日期
     */
    @ApiModelProperty(value = "周报起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 周报终止日期
     */
    @ApiModelProperty(value = "周报终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 周报状态
     */
    @ApiModelProperty(value = "周报状态")
    private String status;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
