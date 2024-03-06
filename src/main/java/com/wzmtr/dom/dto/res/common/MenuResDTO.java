package com.wzmtr.dom.dto.res.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 菜单结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data

public class MenuResDTO {

    /**
     * 主键id
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单是否为根目录 1:是,2:否
     */
    private Integer type;

    /**
     * 目录图标
     */
    private String icon;

    /**
     * 目录排序
     */
    private Integer sort;

    /**
     * 路由地址
     */
    private String url;

    /**
     * 权限编码,如:article:create、article:edit等
     */
    private String permCode;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 状态 0:正常,1:禁用
     */
    private Integer status;

    /**
     * 是否显示 0:隐藏,1显示
     */
    private Integer isShow;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date updateDate;

    /**
     * 子集
     */
    private List<MenuResDTO> children;
}
