package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@Builder
public class LineEventRecordReqDTO extends BaseEntity {

    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    @ApiModelProperty(value = "车辆类事件数")
    private Integer type1Count;

    @ApiModelProperty(value = "信号类事件数")
    private Integer type2Count;

    @ApiModelProperty(value = "侵限类事件数")
    private Integer type3Count;

    @ApiModelProperty(value = "设备类事件数")
    private Integer type4Count;

    @ApiModelProperty(value = "其他类事件数")
    private Integer type5Count;

    @ApiModelProperty(value = "版本号")
    private String version;
}
