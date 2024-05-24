package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织机构父级结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class OrgParentResDTO {

    /**
     * id
     */
    private String id;

    /**
     * 父ID路径
     */
    @ApiModelProperty(value = "父ID路径")
    private String parentIds;

    /**
     * 名称路径
     */
    @ApiModelProperty(value = "名称路径")
    private String names;
}
