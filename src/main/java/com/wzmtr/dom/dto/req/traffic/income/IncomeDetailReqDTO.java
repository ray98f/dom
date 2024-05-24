package com.wzmtr.dom.dto.req.traffic.income;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2024/4/24 15:05
 */
@Data
public class IncomeDetailReqDTO {
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;

    @ApiModelProperty("ID")
    private String id;
}
