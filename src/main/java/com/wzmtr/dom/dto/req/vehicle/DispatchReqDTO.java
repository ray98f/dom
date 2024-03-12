package com.wzmtr.dom.dto.req.vehicle;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 调度命令请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Data
public class DispatchReqDTO {
    /**
     * 调度命令记录
     */
    @ApiModelProperty(value = "调度命令记录")
    private DispatchRecordReqDTO record;

    /**
     * 调度命令详情列表
     */

    @ApiModelProperty(value = "调度命令详情列表")
    private List<DispatchOrderReqDTO> orderList;
}
