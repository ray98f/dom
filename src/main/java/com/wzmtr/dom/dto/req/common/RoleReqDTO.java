package com.wzmtr.dom.dto.req.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class RoleReqDTO {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 权限字符
     */
    @ApiModelProperty(value = "权限字符")
    private String roleCode;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 新建人
     */
    @ApiModelProperty(value = "新建人")
    private String createdBy;

    /**
     * 菜单ids
     */
    @ApiModelProperty(value = "菜单ids")
    private List<String> menuIds;
}
