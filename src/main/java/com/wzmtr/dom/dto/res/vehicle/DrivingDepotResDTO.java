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
public class DrivingDepotResDTO extends BaseEntity {

    @ApiModelProperty(value = "行车情况记录ID")
    private String recordId;

    @ApiModelProperty(value = "车辆段编码")
    private String depotCode;

    @ApiModelProperty(value = "车辆段名称")
    private String depotName;

    @ApiModelProperty(value = "计划发车数")
    private Integer planDeparture;

    @ApiModelProperty(value = "计划收车数")
    private Integer planReceive;

    @ApiModelProperty(value = "实际发车数")
    private Integer realDeparture;

    @ApiModelProperty(value = "实际发车数")
    private Integer realReceive;

    @ApiModelProperty(value = "工程车发车数")
    private Integer railcarDeparture;

    @ApiModelProperty(value = "工程车收车数")
    private Integer railcarReceive;

    @ApiModelProperty(value = "版本号")
    private String version;

}
