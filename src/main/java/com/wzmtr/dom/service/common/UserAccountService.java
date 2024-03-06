package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.common.UserAccountListResDTO;
import com.wzmtr.dom.dto.res.common.UserCenterInfoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.SysUserAccount;

import java.util.List;

/**
 * @author frp
 */
public interface UserAccountService {

    Page<UserAccountListResDTO> listUserAccount(String searchKey, PageReqDTO pageReqDTO);

    List<UserAccountListResDTO> selectUserAccountById(List<String> ids);

    Page<SysUserAccount> listOutUserAccount(PageReqDTO pageReqDTO);

    String getToken(String userId);

    UserCenterInfoResDTO getUserDetail();

}
