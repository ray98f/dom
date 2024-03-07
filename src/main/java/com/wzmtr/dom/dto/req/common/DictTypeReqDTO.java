package com.wzmtr.dom.dto.req.common;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictTypeReqDTO extends BaseEntity {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 启用状态 0 启用 1 禁用
     */
    @ApiModelProperty(value = "启用状态 0 启用 1 禁用")
    private String status;
}
