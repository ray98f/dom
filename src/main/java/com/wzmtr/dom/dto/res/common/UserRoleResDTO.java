package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class UserRoleResDTO {

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
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;
}
