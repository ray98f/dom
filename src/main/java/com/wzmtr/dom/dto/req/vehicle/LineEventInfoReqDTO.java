package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LineEventInfoReqDTO extends BaseEntity {

    @ApiModelProperty(value = "记录ID")
    private String recordId;

    @ApiModelProperty(value = "事件类型1车辆类,2信号类,3侵限类,4设备类,5其他类")
    private String eventType;

    @ApiModelProperty(value = "事件概况")
    private String eventDesc;

    @ApiModelProperty(value = "事件分析")
    private String eventAnalyze;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;
}
