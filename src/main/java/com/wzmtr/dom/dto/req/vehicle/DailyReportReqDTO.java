package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 车辆部日报请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DailyReportReqDTO extends BaseEntity {

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
     * 选用班组培训 0否1是
     */
    @ApiModelProperty(value = "选用班组培训 0否1是")
    private String ifTrainRecord;

    /**
     * 选用人员情况 0否1是
     */
    @ApiModelProperty(value = "选用人员情况 0否1是")
    private String ifPersonRecord;

    /**
     * 选用调度命令 0否1是
     */
    @ApiModelProperty(value = "选用调度命令 0否1是")
    private String ifDispatch;

    /**
     * 选用车场交接班 0否1是
     */
    @ApiModelProperty(value = "选用车场交接班 0否1是")
    private String ifDispatchHandover;

    /**
     * 选用行车注意 0否1是
     */
    @ApiModelProperty(value = "选用行车注意 0否1是")
    private String ifDrivingAttention;

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
     * 交班场调
     */
    @ApiModelProperty(value = "交班场调")
    private String currentDispatcher;

    /**
     * 接班场调
     */
    @ApiModelProperty(value = "接班场调")
    private String nextDispatcher;

    /**
     * 交班DCC值班员
     */
    @ApiModelProperty(value = "交班DCC值班员")
    private String currentDcc;

    /**
     * 接班DCC值班员
     */
    @ApiModelProperty(value = "接班DCC值班员")
    private String nextDcc;

    /**
     * 交班指导司机
     */
    @ApiModelProperty(value = "交班指导司机")
    private String currentDriver;

    /**
     * 接班指导司机
     */
    @ApiModelProperty(value = "接班指导司机")
    private String nextDriver;

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
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
