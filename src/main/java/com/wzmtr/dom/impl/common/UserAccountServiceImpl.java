package com.wzmtr.dom.impl.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.res.common.UserAccountListResDTO;
import com.wzmtr.dom.dto.res.common.UserCenterInfoResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.SysUserAccount;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.common.UserAccountMapper;
import com.wzmtr.dom.service.common.UserAccountService;
import com.wzmtr.dom.shiro.model.Person;
import com.wzmtr.dom.shiro.service.IPersonService;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frp
 */
@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private IPersonService personService;

    @Override
    public Page<UserAccountListResDTO> listUserAccount(String searchKey, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return userAccountMapper.listUserAccount(pageReqDTO.of(), searchKey);
    }

    @Override
    public List<UserAccountListResDTO> selectUserAccountById(List<String> ids) {
        return userAccountMapper.selectUserAccountById(ids);
    }

    @Override
    public Page<SysUserAccount> listOutUserAccount(PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return userAccountMapper.listOutUserAccount(pageReqDTO.of());
    }

    @Override
    public String getToken(String userId) {
        CurrentLoginUser person = new CurrentLoginUser();
            Person p = personService.searchPersonByNo(userId);
            if (p != null) {
                person.setPersonId(p.getId());
                person.setPersonNo(p.getNo());
                person.setPersonName(p.getName());
                person.setMobile(p.getMobile());
                person.setPhone(p.getPhone());
                person.setEmail(p.getEmail());
                person.setCompanyId(p.getCompanyId());
                person.setCompanyName(p.getCompanyName());
                person.setCompanyAreaId(p.getCompanyAreaId());
                person.setOfficeId(p.getOfficeId());
                person.setOfficeName(p.getOfficeName());
                person.setOfficeAreaId(p.getOfficeAreaId());
                person.setNames(p.getNames());
            } else {
                throw new CommonException(ErrorCode.USER_NOT_EXIST);
            }
        return TokenUtils.createSimpleToken(person);
    }

    @Override
    public UserCenterInfoResDTO getUserDetail() {
        UserCenterInfoResDTO res = userAccountMapper.userCenterInfo(TokenUtils.getCurrentPersonId());
        // 获取登录用户角色权限
        res.setUserRoles(userAccountMapper.getUserRoles(res.getId()));
        return res;
    }
}
