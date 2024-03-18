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
@Data
public class SyncOdmDepotResDTO {

    @ApiModelProperty(value = "车辆段编码")
    private String depotCode;

    @ApiModelProperty(value = "计划发车数")
    private Integer planDeparture;

    @ApiModelProperty(value = "计划收车数")
    private Integer planReceive;

    @ApiModelProperty(value = "实际发车数")
    private Integer realDeparture;

    @ApiModelProperty(value = "实际收车数")
    private Integer realReceive;

}
