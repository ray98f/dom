package com.wzmtr.dom.dto.res.operate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 施工计划信息
 *
 * @author zhangxin
 * @version 1.0o
 * @date 2024/3/13 13:38
 */
@Data
public class UnsaturationConstructResDTO {

    @ApiModelProperty("施工情况")
    private String planStausName;
    @ApiModelProperty("作业代码")
    private String workCode;
    @ApiModelProperty("施工单位")
    private String workdeptname;
    @ApiModelProperty("CONSTRUCTPLAN_ID")
    private String constructplanId;
    @ApiModelProperty("作业内容及说明")
    private String workDetail;
    private String workdate;
    private String workTime;
    private String planbeginTimeStr;
    private String planStatus;
    private String beginapproveTime;
    private String endapproveTime;
    private String planBeginTime;
    private String planEndTime;
    @ApiModelProperty("计划类型")
    private String planType;
    @ApiModelProperty("实际时间")
    private String finishTime;
    @ApiModelProperty("饱和度")
    private String unsaturation;
    @ApiModelProperty("计划时间")
    private String planTime;

}
