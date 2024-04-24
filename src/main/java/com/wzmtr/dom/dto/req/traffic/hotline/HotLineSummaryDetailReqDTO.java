package com.wzmtr.dom.dto.req.traffic.hotline;

import com.wzmtr.dom.entity.PageReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 17:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HotLineSummaryDetailReqDTO extends PageReqDTO {
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("数据类型")
    private String dataType;
}
