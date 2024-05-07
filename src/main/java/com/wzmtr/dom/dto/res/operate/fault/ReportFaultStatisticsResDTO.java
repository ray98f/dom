package com.wzmtr.dom.dto.res.operate.fault;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 运营日报-日报故障统计记录
 * @author  Ray
 * @version 1.0
 * @date 2024/04/29
 */
@Data
public class ReportFaultStatisticsResDTO {

    /**
     * 本日
     */
    @ApiModelProperty("本日")
    private FaultStatisticsResDTO today;

    /**
     * 本月累计
     */
    @ApiModelProperty("本月累计")
    private FaultStatisticsResDTO currentMonth;

}
