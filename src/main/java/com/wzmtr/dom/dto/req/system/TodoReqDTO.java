package com.wzmtr.dom.dto.req.system;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务督办审批请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TodoReqDTO extends BaseEntity {

    /**
     * 流程名
     */
    @ApiModelProperty(value = "流程名")
    private String processKey;

    /**
     * 所属报表ID
     */
    @ApiModelProperty(value = "所属报表ID")
    private String reportId;

    /**
     * 审批结果说明
     */
    @ApiModelProperty(value = "审批结果说明")
    private String approvalResult;

    /**
     * 审批结果状态:1通过2驳回
     */
    @ApiModelProperty(value = "审批结果状态:1通过2驳回")
    private String approvalStatus;

}
