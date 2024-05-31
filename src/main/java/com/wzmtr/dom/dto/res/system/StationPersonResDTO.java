package com.wzmtr.dom.dto.res.system;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class StationPersonResDTO {
    private String userId;
    private String userNum;
    private String stationCode;
    private String stationPositionId;
    private String positionName;
}
