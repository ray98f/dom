package com.wzmtr.dom.dto.req.operate;

import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstructEventReqDTO extends BaseEntity {

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

    /**
     * 是否重点施工0否1是
     * */
    @ApiModelProperty(value = "是否重点施工0否1是")
    private String isImportant;


    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    private String dataType;

    /**
     * 数据所属日期
     * */
    private String dataDate;

    /**
     * 数据起始日期
     * */
    private String startDate;

    /**
     * 数据终止日期
     * */
    private String endDate;

}
