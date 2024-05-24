package com.wzmtr.dom.dto.res.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 任务督办结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Data
public class FlowNodeResDTO {

    /**
     * 流程ID
     */
    @ApiModelProperty(value = "流程ID")
    private String flowId;

    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称")
    private String flowName;

    /**
     * 节点ID
     */
    @ApiModelProperty(value = "节点ID")
    private String nodeId;

    /**
     * 节点名称
     */
    @ApiModelProperty(value = "节点名称")
    private String nodeName;

    /**
     * 节点审批角色
     */
    @ApiModelProperty(value = "节点审批角色")
    private String roleId;

    /**
     * 上一节点
     */
    @ApiModelProperty(value = "上一节点")
    private String parentNode;

    /**
     * 下一节点
     */
    @ApiModelProperty(value = "下一节点")
    private String nextNode;

    /**
     * 节点序号
     */
    @ApiModelProperty(value = "节点序号")
    private Integer nodeOrder;

    /**
     * 节点类型:1待办 2待阅
     */
    @ApiModelProperty(value = "节点类型:1待办 2待阅")
    private String nodeType;

    /**
     * 是否在待办列表显示: 0否,1是
     */
    @ApiModelProperty(value = "是否在待办列表显示: 0否,1是")
    private String todoShowFlag;
}
