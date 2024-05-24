package com.wzmtr.dom.mapper.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.common.UserAccountResDTO;
import com.wzmtr.dom.dto.res.common.UserCenterInfoResDTO;
import com.wzmtr.dom.dto.res.common.UserRoleResDTO;
import com.wzmtr.dom.entity.SysOrgUser;
import com.wzmtr.dom.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 公共分类-用户管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Mapper
@Repository
public interface UserAccountMapper {

    /**
     * 获取用户信息列表
     * @param page 分页参数
     * @param searchKey 关键字
     * @return 用户信息列表
     */
    Page<UserAccountResDTO> listUserAccount(Page<UserAccountResDTO> page, String searchKey);

    /**
     * 根据ids获取用户信息列表
     * @param ids ids
     * @return 用户信息列表
     */
    List<UserAccountResDTO> selectUserAccountById(List<String> ids);

    /**
     * 清空用户表(不包含外部用户)
     */
    void cleanTable();

    /**
     * 清空外部用户表
     */
    void cleanSuppCon();

    /**
     * 新增用户
     * @param sysUser 用户信息
     */
    void createPerson(SysUser sysUser);

    /**
     * 清空人员职位信息
     */
    void cleanEmpJob();

    /**
     * 新增用户职位
     * @param sysOrgUser 用户职位信息
     */
    void createEmpJob(SysOrgUser sysOrgUser);

    /**
     * 修改用户公司
     * @param sysUser 用户信息
     */
    void updateUserCompany(SysUser sysUser);

    /**
     * 修改用户额外信息
     * @param paramMap 用户信息
     */
    void updatePersonPlus(Map<String, Object> paramMap);

    /**
     * 获取登录用户详情
     * @param userId 登录用户id
     * @return 登录用户详情
     */
    UserCenterInfoResDTO userCenterInfo(@Param("userId") String userId);

    /**
     * 获取登录用户权限
     * @param userId 登录用户id
     * @return 登录用户权限
     */
    List<UserRoleResDTO> getUserRoles(@Param("userId") String userId);

}
