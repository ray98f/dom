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
     * flowId
     */
    @ApiModelProperty(value = "flowId")
    private String flowId;

    /**
     * flowName
     */
    @ApiModelProperty(value = "flowName")
    private String flowName;

    /**
     * nodeId
     */
    @ApiModelProperty(value = "nodeId")
    private String nodeId;

    /**
     * nodeName
     */
    @ApiModelProperty(value = "nodeName")
    private String nodeName;

    /**
     * roleId
     */
    @ApiModelProperty(value = "roleId")
    private String roleId;

    /**
     * parentNode
     */
    @ApiModelProperty(value = "parentNode")
    private String parentNode;

    /**
     * nextNode
     */
    @ApiModelProperty(value = "nextNode")
    private String nextNode;

    /**
     * nodeOrder
     */
    @ApiModelProperty(value = "nodeOrder")
    private Integer nodeOrder;

    /**
     * nodeType
     */
    @ApiModelProperty(value = "nodeType")
    private String nodeType;
}
