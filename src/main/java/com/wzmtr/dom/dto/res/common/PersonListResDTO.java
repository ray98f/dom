package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色绑定用户类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class PersonListResDTO {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

}
