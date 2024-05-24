package com.wzmtr.dom.dto.res.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 审核站权限结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class AllStationResDTO {

    /**
     * 所有车站
     */
    @ApiModelProperty(value = "所有车站")
    private List<StationResDTO> stationList;

    /**
     * 车站编码
     */
    @ApiModelProperty(value = "车站编码")
    private String stationCodes;


}
