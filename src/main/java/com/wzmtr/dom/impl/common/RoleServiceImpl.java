package com.wzmtr.dom.impl.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.common.RoleReqDTO;
import com.wzmtr.dom.dto.req.common.UserRoleReqDTO;
import com.wzmtr.dom.dto.res.common.PersonListResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.Role;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.common.RoleMapper;
import com.wzmtr.dom.service.common.RoleService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 公共分类-角色管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getLoginRole() {
        return roleMapper.getLoginRole(TokenUtils.getCurrentPersonId());
    }

    @Override
    public Page<Role> listRole(String roleName, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return roleMapper.listRole(pageReqDTO.of(), roleName);
    }

    @Override
    public void deleteRole(String id) {
        if (Objects.isNull(id)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        roleMapper.deleteRole(id);
        roleMapper.deleteRoleMenu(id);
    }

    @Override
    public void addRole(RoleReqDTO role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        role.setCreatedBy(TokenUtils.getCurrentPersonId());
        role.setId(TokenUtils.getUuId());
        roleMapper.insertRole(role);
    }

    @Override
    public void updateRole(RoleReqDTO role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        role.setCreatedBy(TokenUtils.getCurrentPersonId());
        roleMapper.updateRole(role);
    }

    @Override
    public List<String> getRolePerms(String id) {
        if (Objects.isNull(id)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        return roleMapper.getRolePerms(id);
    }

    @Override
    public void authorizeRole(RoleReqDTO role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        role.setCreatedBy(TokenUtils.getCurrentPersonId());
        roleMapper.deleteRoleMenu(role.getId());
        if (StringUtils.isNotEmpty(role.getMenuIds())) {
            roleMapper.insertRoleMenu(role);
        }
    }

    @Override
    public void bindUser(UserRoleReqDTO userRole) {
        roleMapper.deleteUserRole(userRole);
        roleMapper.addUserRole(userRole);
    }

    @Override
    public List<PersonListResDTO> listRoleUsers(String roleId, String roleCode) {
        return roleMapper.listRoleUsers(roleId, roleCode);
    }

}
