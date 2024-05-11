package com.wzmtr.dom.dto.req.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/10 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenDispatchOrderReqDTO {
    @ApiModelProperty("线路")
    private String lineCode;
    @ApiModelProperty("业务类型")
    private String dispatchorderType;
    @ApiModelProperty("业务状态")
    private String dispatchorderStatus;
    @ApiModelProperty("命令标题")
    private String dispatchorderTitle;
    @ApiModelProperty("调度代码")
    private String dispatchCode;
    @ApiModelProperty("命令代码")
    private String orderCode;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    private Integer page=1;
    private Integer limit=10;
}
