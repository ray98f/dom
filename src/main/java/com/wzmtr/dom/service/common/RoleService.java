package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.RoleReqDTO;
import com.wzmtr.dom.dto.req.common.UserRoleReqDTO;
import com.wzmtr.dom.dto.res.common.PersonListResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.Role;

import java.util.List;

/**
 * 公共分类-角色管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface RoleService {

    /**
     * 获取登录用户的角色列表
     * @return 登录用户的角色列表
     */
    List<Role> getLoginRole();

    /**
     * 分页获取角色信息
     * @param roleName 角色名称
     * @param pageReqDTO 分页信息
     * @return 角色信息
     */
    Page<Role> listRole(String roleName, PageReqDTO pageReqDTO);

    /**
     * 删除角色
     * @param id id
     */
    void deleteRole(String id);

    /**
     * 新增角色
     * @param role 新增参数
     */
    void addRole(RoleReqDTO role);

    /**
     * 编辑角色
     * @param role 修改参数
     */
    void updateRole(RoleReqDTO role);

    /**
     * 获取角色权限列表
     * @param id 角色id
     * @return 角色权限列表
     */
    List<String> getRolePerms(String id);

    /**
     * 角色权限分配
     * @param role
     */
    void authorizeRole(RoleReqDTO role);

    /**
     * 角色绑定人员
     * @param userRole 角色绑定人员参数
     */
    void bindUser(UserRoleReqDTO userRole);

    /**
     * 获取角色已绑定人员列表
     * @param roleId 角色id
     * @param roleCode 角色编码
     * @return 角色已绑定人员列表
     */
    List<PersonListResDTO> listRoleUsers(String roleId, String roleCode);
}
