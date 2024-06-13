package com.wzmtr.dom.dto.res.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 审核站权限结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class StationResDTO {

    /**
     * 车站编号
     */
    @ApiModelProperty(value = "车站编号")
    private String stationCode;

    /**
     * 车站名称
     */
    @ApiModelProperty(value = "车站名称")
    private String stationName;

    /**
     * 所属线路
     */
    @ApiModelProperty(value = "所属线路")
    private String lineCode;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer stationOrder;

    private String id;
}
