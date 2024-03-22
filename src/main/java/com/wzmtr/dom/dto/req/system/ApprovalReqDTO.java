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
public class ApprovalReqDTO extends BaseEntity {

    /**
     * 流程名
     */
    @ApiModelProperty(value = "流程名")
    private String processKey;

    /**
     * 事项类型:1待办,2待阅
     */
    @ApiModelProperty(value = "事项类型:1待办,2待阅")
    private String todoType;

    /**
     * 待办状态0未办理,1已办理
     */
    @ApiModelProperty(value = "待办状态0未办理,1已办理")
    private String status;

    /**
     * 所属数据类型:1日报,2周报,3月报
     */
    @ApiModelProperty(value = "所属数据类型:1日报,2周报,3月报")
    private String dataType;

    /**
     * 所属报表ID
     */
    @ApiModelProperty(value = "所属报表ID")
    private String reportId;

    /**
     * 关联报表表名
     */
    @ApiModelProperty(value = "关联报表表名")
    private String reportTable;

    /**
     * 当前审批人
     */
    @ApiModelProperty(value = "当前审批人")
    private String approvalUser;

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

    /**
     * 当前审批节点名
     */
    @ApiModelProperty(value = "当前审批节点名")
    private String currentNode;

}
