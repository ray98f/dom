package com.wzmtr.dom.dto.res.vehicle;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DrivingInfoResDTO extends BaseEntity {

    @ApiModelProperty(value = "行车情况记录ID")
    private String recordId;

    @ApiModelProperty(value = "司机总人数")
    private Integer driverCount;

    @ApiModelProperty(value = "应出勤人数")
    private Integer planAttend;

    @ApiModelProperty(value = "实际出勤数")
    private Integer realAttend;

    @ApiModelProperty(value = "支援人数")
    private Integer supportCount;

    @ApiModelProperty(value = "司机行驶总里程")
    private Double mileage;

    @ApiModelProperty(value = "调试公里数")
    private Double testMileage;

    @ApiModelProperty(value = "驾驶总公里数")
    private Double mileageTotal;

    @ApiModelProperty(value = "驾驶总公里数")
    private Double avgMileage;

    @ApiModelProperty(value = "版本号")
    private String version;

}
