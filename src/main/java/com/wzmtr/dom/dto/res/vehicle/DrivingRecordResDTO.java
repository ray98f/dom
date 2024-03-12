package com.wzmtr.dom.dto.res.vehicle;

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
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DrivingRecordResDTO extends BaseEntity {

    @ApiModelProperty(value = "下塘停车场发车列数")
    private Integer trainCount1;

    @ApiModelProperty(value = " 汀田车辆段发车列数")
    private Integer trainCount2;

    @ApiModelProperty(value = "司机总人数")
    private Integer driverCount;

    @ApiModelProperty(value = "驾驶总公里数")
    private Double mileageTotal;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;
}
