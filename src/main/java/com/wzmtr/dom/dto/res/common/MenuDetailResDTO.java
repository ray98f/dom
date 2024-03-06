package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单详情结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@ApiModel
public class MenuDetailResDTO {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private String parentId;

    /**
     * 父菜单名称
     */
    @ApiModelProperty(value = "父菜单名称")
    private String parentName;

    /**
     * 目录名称
     */
    @ApiModelProperty(value = "目录名称")
    private String name;

    /**
     * 菜单是否为根目录 1:是,2:否
     */
    @ApiModelProperty(value = "菜单是否为根目录 1:是,2:否")
    private Integer type;

    /**
     * 目录图标
     */
    @ApiModelProperty(value = "目录图标")
    private String icon;

    /**
     * 目录排序
     */
    @ApiModelProperty(value = "目录排序")
    private Integer sort;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String url;

    /**
     * 权限编码,如:article:create、article:edit等
     */
    @ApiModelProperty(value = "权限编码,如:article:create、article:edit等")
    private String permCode;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 状态 0:正常,1:禁用
     */
    @ApiModelProperty(value = "状态 0:正常,1:禁用")
    private Integer status;

    /**
     * 是否显示 0:隐藏,1显示
     */
    @ApiModelProperty(value = "是否显示 0:隐藏,1显示")
    private Integer isShow;

}
