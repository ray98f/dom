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
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DrivingRecordReqDTO extends BaseEntity {

    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    @ApiModelProperty(value = "下塘停车场发车列数")
    private Integer trainCount1;

    @ApiModelProperty(value = "汀田车辆段发车列数")
    private Integer trainCount2;

    @ApiModelProperty(value = "司机总人数")
    private Integer driverCount;

    @ApiModelProperty(value = "驾驶总公里数")
    private Double mileageTotal;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "版本号")
    private String version;

}
