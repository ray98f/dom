package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/10 14:51
 */
@Data
public class OpenConstructPlanResDTO {
    @ApiModelProperty("作业区域")
    private String workareaDesc;
    @ApiModelProperty("作业名称")
    private String workName;
    @ApiModelProperty("作业编码")
    private String workCode;
    @ApiModelProperty("供电要求")
    private String powerRequest;
    @ApiModelProperty("作业类型")
    private String workType;
    @ApiModelProperty("计划开始时间")
    private String planbeginTime;
    @ApiModelProperty("计划结束时间")
    private String planendTime;
    @ApiModelProperty("作业内容id")
    private String workconcentId;
    @ApiModelProperty("计划状态")
    private String planStatus;
    @ApiModelProperty("计划id")
    private String constructplanId;
    @ApiModelProperty("计划类型")
    private String planType;
    @ApiModelProperty("部门id")
    private String workdeptId;
    @ApiModelProperty("施工负责人")
    private String constleaderName;
    @ApiModelProperty("施工负责人手机号")
    private String constLeaderPhone;
    @ApiModelProperty("施工部门名称")
    private String workdeptName;
    @ApiModelProperty("站点名称")
    private String stationName;
}
