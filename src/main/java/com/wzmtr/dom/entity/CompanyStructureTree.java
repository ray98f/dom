package com.wzmtr.dom.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 组织机构层级结构
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
public class CompanyStructureTree {

    /**
     * 组织编码
     */
    @ApiModelProperty(value = "组织编码")
    private String id;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称")
    private String name;

    /**
     * 组织路径
     */
    @ApiModelProperty(value = "组织路径")
    private String names;

    /**
     * 上级机构编码
     */
    @ApiModelProperty(value = "上级机构编码")
    private String parentId;

    /**
     * 上级机构编码列
     */
    @ApiModelProperty(value = "上级机构编码列")
    private String parentIds;

    /**
     * 外部公司编码
     */
    @ApiModelProperty(value = "外部公司编码")
    private String areaId;

    /**
     * 子集
     */
    @ApiModelProperty(value = "子集")
    private List<CompanyStructureTree> children;
}
