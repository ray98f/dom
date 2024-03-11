package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.common.UserAccountResDTO;
import com.wzmtr.dom.dto.res.common.UserCenterInfoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 公共分类-用户管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface UserAccountService {

    /**
     * 获取用户信息列表
     * @param searchKey 关键字
     * @param pageReqDTO 分页参数
     * @return 用户信息列表
     */
    Page<UserAccountResDTO> listUserAccount(String searchKey, PageReqDTO pageReqDTO);

    /**
     * 根据ids获取用户信息列表
     * @param ids ids
     * @return 用户信息列表
     */
    List<UserAccountResDTO> selectUserAccountById(List<String> ids);

    /**
     * 获取登录用户token
     * @param no 用户登录账号
     * @return token
     */
    String getToken(String no);

    /**
     * 获取登录用户详情
     * @return 用户详情
     */
    UserCenterInfoResDTO getUserDetail();

}
