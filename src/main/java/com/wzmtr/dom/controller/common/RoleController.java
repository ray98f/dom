package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.req.common.RoleReqDTO;
import com.wzmtr.dom.dto.req.common.UserRoleReqDTO;
import com.wzmtr.dom.dto.res.common.PersonListResDTO;
import com.wzmtr.dom.entity.BaseIdEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.Role;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.common.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 公共分类-角色管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = "公共分类-角色管理")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取登录用户的角色列表
     * @return 登录用户的角色列表
     */
    @GetMapping("/login")
    @ApiOperation(value = "获取登录用户的角色")
    public DataResponse<List<Role>> getLoginRole() {
        return DataResponse.of(roleService.getLoginRole());
    }

    /**
     * 分页获取角色信息
     * @param roleName 角色名称
     * @param pageReqDTO 分页信息
     * @return 角色信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页获取角色信息")
    public PageResponse<Role> listRole(@RequestParam(required = false) @ApiParam("名称模糊查询") String roleName,
                                       @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(roleService.listRole(roleName, pageReqDTO));
    }

    /**
     * 删除角色
     * @param baseIdEntity id
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除角色")
    public DataResponse<T> deleteRole(@RequestBody BaseIdEntity baseIdEntity) {
        roleService.deleteRole(baseIdEntity.getId());
        return DataResponse.success();
    }

    /**
     * 新增角色
     * @param role 新增参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增角色")
    public DataResponse<T> addRole(@RequestBody RoleReqDTO role) {
        roleService.addRole(role);
        return DataResponse.success();
    }

    /**
     * 修改角色
     * @param role 修改参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改角色")
    public DataResponse<T> updateRole(@RequestBody RoleReqDTO role) {
        roleService.updateRole(role);
        return DataResponse.success();
    }

    /**
     * 获取角色权限列表
     * @param id 角色id
     * @return 角色权限列表
     */
    @GetMapping("/perms")
    @ApiOperation(value = "角色权限列表")
    public DataResponse<List<String>> getRolePerms(@RequestParam @ApiParam(value = "角色id") String id) {
        return DataResponse.of(roleService.getRolePerms(id));
    }

    /**
     * 角色权限分配
     * @param role 权限
     * @return 成功
     */
    @PostMapping("/authorize")
    @ApiOperation(value = "角色权限分配")
    public DataResponse<T> authorizeRole(@RequestBody RoleReqDTO role) {
        roleService.authorizeRole(role);
        return DataResponse.success();
    }

    /**
     * 角色绑定人员
     * @param userRole 角色绑定人员参数
     * @return 成功
     */
    @PostMapping("/bindUser")
    @ApiOperation(value = "角色绑定人员")
    public DataResponse<T> bindUser(@RequestBody UserRoleReqDTO userRole) {
        roleService.bindUser(userRole);
        return DataResponse.success();
    }

    /**
     * 获取角色已绑定人员列表
     * @param roleId 角色id
     * @param roleCode 角色编码
     * @return 角色已绑定人员列表
     */
    @GetMapping("/listRoleUsers")
    @ApiOperation(value = "获取角色已绑定人员")
    public DataResponse<List<PersonListResDTO>> listRoleUsers(@RequestParam(required = false) @ApiParam(value = "角色id") String roleId,
                                                              @RequestParam(required = false) @ApiParam(value = "角色编码") String roleCode) {
        return DataResponse.of(roleService.listRoleUsers(roleId, roleCode));
    }
}