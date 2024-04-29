package com.wzmtr.dom.dto.res.operate;

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
public class ConstructEventResDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 事件标题
     * */
    @ApiModelProperty(value = "事件标题")
    private String title;

    /**
     * 事件时间
     * */
    @ApiModelProperty(value = "事件时间")
    private String eventTime;

    /**
     * 事件内容
     * */
    @ApiModelProperty(value = "事件内容")
    private String eventContent;

    /**
     * 项目数量
     * */
    @ApiModelProperty(value = "项目数量")
    private Integer projectCount;

    /**
     * 周施工计划数
     * */
    @ApiModelProperty(value = "周施工计划数")
    private Integer plan1Count;

    /**
     * 日补充施工计划数
     * */
    @ApiModelProperty(value = "日补充施工计划数")
    private Integer plan2Count;

    /**
     * 临时施工计划数
     * */
    @ApiModelProperty(value = "临时施工计划数")
    private Integer plan3Count;

    /**
     * 施工计划总数
     * */
    @ApiModelProperty(value = "施工计划总数")
    private Integer totalCount;

    /**
     * 开行工程车
     * */
    @ApiModelProperty(value = "开行工程车")
    private Integer engineeringVehicle;

    /**
     * 开行调试车
     * */
    @ApiModelProperty(value = "开行调试车")
    private Integer debuggingVehicle;

    /**
     * 擅自取消数
     * */
    @ApiModelProperty(value = "擅自取消数")
    private Integer cancelCount;

    /**
     * 延迟请点
     * */
    @ApiModelProperty(value = "延迟请点")
    private Integer mask1Delay;

    /**
     * 延迟销点
     * */
    @ApiModelProperty(value = "延迟销点")
    private Integer mask2Delay;

    /**
     * 是否闭环
     * */
    @ApiModelProperty(value = "是否闭环")
    private String isClose;


    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

}
