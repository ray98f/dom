package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepotConstructPlanReqDTO extends BaseEntity {

    /**
     * id
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.modify.class)
    private String id;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String dataType;

    /**
     * 数据所属日期
     * */
    private String dataDate;

    /**
     * 数据起始日期
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String startDate;

    /**
     * 数据终止日期
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String endDate;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 车场编码
     */
    @ApiModelProperty(value = "车场编码")
    private String depotCode;

    /**
     * 施工计划ID
     */
    @ApiModelProperty(value = "施工计划ID")
    private String constructPlanId;

    /**
     * 施工作业类型
     */
    @ApiModelProperty(value = "施工作业类型")
    private String workType;

    /**
     * 施工作业ID
     */
    @ApiModelProperty(value = "施工作业ID")
    private String workconcentId;

    /**
     * 施工代码
     */
    @ApiModelProperty(value = "施工代码")
    private String workCode;

    /**
     * 施工事件
     */
    @ApiModelProperty(value = "施工事件")
    private String workName;

    /**
     * 施工单位
     */
    @ApiModelProperty(value = "施工单位")
    private String workDept;

    /**
     * 施工地点
     */
    @ApiModelProperty(value = "施工地点")
    private String workArea;

    /**
     * 施工内容
     */
    @ApiModelProperty(value = "施工内容")
    private String workDetail;

    /**
     * 供电要求
     */
    @ApiModelProperty(value = "供电要求")
    private String powerReq;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    @NotNull(message = "32000006",groups = ValidationGroup.modify.class)
    private String version;
}
