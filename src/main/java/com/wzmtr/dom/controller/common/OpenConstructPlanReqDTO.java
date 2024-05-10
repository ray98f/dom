package com.wzmtr.dom.controller.common;

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
public class OpenConstructPlanReqDTO {
    @ApiModelProperty("作业编码")
    private String workCode;
    @ApiModelProperty("作业类型")
    private String workType;
    @ApiModelProperty("计划类型")
    private String planType;
    @ApiModelProperty("计划开始时间")
    private String planbeginTime;
    @ApiModelProperty("计划结束时间")
    private String planendTime;
    private String createdBy;
    private Integer page=1;
    private Integer limit=10;
}
