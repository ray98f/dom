package com.wzmtr.dom.dto.req.common;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典值请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictReqDTO extends BaseEntity {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 类型编号
     */
    @ApiModelProperty(value = "类型编号")
    private String typeCode;

    /**
     * 值编号
     */
    @ApiModelProperty(value = "值编号")
    private String code;

    /**
     * 值名称
     */
    @ApiModelProperty(value = "值名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

}
