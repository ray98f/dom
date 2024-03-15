package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CrewEventInfoReqDTO extends BaseEntity {

    /**
     * 事件总结记录ID
     * */
    @ApiModelProperty(value = "事件总结记录ID")
    private String recordId;

    /**
     * 事件类型:1对标不准类,2误操作类,3作业标准类,4其他类
     * */
    @ApiModelProperty(value = "事件类型:1对标不准类,2误操作类,3作业标准类,4其他类")
    private String eventType;

    /**
     * 事件概况
     * */
    @ApiModelProperty(value = "事件概况")
    private String eventDesc;

    /**
     * 司机处置
     * */
    @ApiModelProperty(value = "司机处置")
    private String driverHandle;

    /**
     * 整改措施
     * */
    @ApiModelProperty(value = "整改措施")
    private String eventMeasure;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;
}
