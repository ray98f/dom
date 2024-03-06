package com.wzmtr.dom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
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
}
