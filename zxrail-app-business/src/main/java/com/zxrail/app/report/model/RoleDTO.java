package com.zxrail.app.report.model;

import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2023/12/25 15:51
 */
@Data
public class RoleDTO {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remarks;
}
