package com.wzmtr.dom.dto.res.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 施工计划信息
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 13:38
 */
@Data
public class ConstructPlanResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

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
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 数据类型:1:日报,2周报,3月报
     * */
    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    /**
     * 不饱和施工列表
     * */
    private List<UnsaturationConstructResDTO> unsaturationConstruct;
}
