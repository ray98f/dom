package com.wzmtr.dom.dto.req.traffic.income;

import com.wzmtr.dom.entity.PageReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IncomeListReqDTO extends PageReqDTO {
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
}
