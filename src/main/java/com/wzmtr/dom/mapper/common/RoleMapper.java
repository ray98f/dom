package com.wzmtr.dom.mapper.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.RoleReqDTO;
import com.wzmtr.dom.dto.req.common.UserRoleReqDTO;
import com.wzmtr.dom.dto.res.common.PersonListResDTO;
import com.wzmtr.dom.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共分类-角色管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 获取登录用户的角色列表
     * @param userId 用户id
     * @return 登录用户的角色列表
     */
    List<Role> getLoginRole(@Param("userId") String userId);

    /**
     * 分页获取角色信息
     * @param page 分页参数
     * @param roleName 角色名称
     * @return 角色信息
     */
    Page<Role> listRole(Page<Role> page, @Param("roleName") String roleName);

    /**
     * 删除角色
     * @param id 角色id
     */
    void deleteRole(@Param("id") String id);

    /**
     * 删除角色绑定菜单
     * @param id 角色id
     */
    void deleteRoleMenu(@Param("id") String id);

    /**
     * 新增角色
     * @param role 新增参数
     */
    void insertRole(RoleReqDTO role);

    /**
     * 角色绑定菜单
     * @param role 角色菜单绑定参数
     */
    void insertRoleMenu(RoleReqDTO role);

    /**
     * 编辑橘色
     * @param role 编辑参数
     */
    void updateRole(RoleReqDTO role);

    /**
     * 获取角色权限列表
     * @param id 角色id
     * @return 角色权限列表
     */
    List<String> getRolePerms(@Param("id") String id);

    /**
     * 清除角色绑定人员
     * @param userRole 角色绑定人员参数
     */
    void deleteUserRole(UserRoleReqDTO userRole);

    /**
     * 新增角色绑定人员
     * @param userRole 角色绑定人员参数
     */
    void addUserRole(UserRoleReqDTO userRole);

    /**
     * 获取角色已绑定人员列表
     * @param roleId 角色id
     * @param roleCode 角色编号
     * @return 角色已绑定人员列表
     */
    List<PersonListResDTO> listRoleUsers(@Param("roleId") String roleId, @Param("roleCode") String roleCode);
}
