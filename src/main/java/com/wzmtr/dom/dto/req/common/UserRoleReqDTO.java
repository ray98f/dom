package com.wzmtr.dom.dto.req.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色绑定人员类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class UserRoleReqDTO {

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private String roleId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private List<String> userIds;
}
