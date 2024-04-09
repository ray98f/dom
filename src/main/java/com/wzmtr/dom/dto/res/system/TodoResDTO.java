package com.wzmtr.dom.dto.res.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 任务督办结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Data
public class TodoResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 流程名
     */
    @ApiModelProperty(value = "流程名")
    private String processKey;

    /**
     * 父流程名
     */
    @ApiModelProperty(value = "父流程名")
    private String parentProcessKey;

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

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "新增时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "新增人")
    private String createBy;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private String isHide;

    /**
     * 父待办
     */
    @ApiModelProperty(value = "父待办")
    private String parentId;
}
