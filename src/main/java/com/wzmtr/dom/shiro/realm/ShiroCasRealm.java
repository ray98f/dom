package com.wzmtr.dom.shiro.realm;

import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.shiro.model.Person;
import com.wzmtr.dom.shiro.service.IPersonService;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2021/8/3 10:54
 */
@Slf4j
public class ShiroCasRealm extends CasRealm {


    @Autowired
    private IPersonService personService;

    /**
     * 1、CAS认证 ,验证用户身份
     * 2、将用户基本信息设置到会话中(不用了，随时可以获取的)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        AuthenticationInfo authenticationInfo = super.doGetAuthenticationInfo(token);
        if (Objects.isNull(authenticationInfo)) {
            return null;
        }
        String name = (String) authenticationInfo.getPrincipals().getPrimaryPrincipal();
        SecurityUtils.getSubject().getSession().setAttribute("name", name);

        CurrentLoginUser person = new CurrentLoginUser();
        if (CommonConstants.ADMIN.equals(name)) {
            person.setPersonId(CommonConstants.ADMIN);
            person.setPersonNo(CommonConstants.ADMIN);
            person.setPersonName("系统管理员");
            person.setCompanyId("A");
            person.setCompanyName("集团本级");
            person.setOfficeId("A02");
            person.setOfficeName("办公室");
            person.setNames("集团本级-办公室");
            person.setStationCode("241");
        } else {
            Person p = personService.searchPersonByNo(name);
            if (p != null) {
                person.setPersonId(p.getId());
                person.setPersonNo(p.getNo());
                person.setPersonName(p.getName());
                person.setCompanyId(p.getCompanyId());
                person.setCompanyName(p.getCompanyName());
                person.setCompanyAreaId(p.getCompanyAreaId());
                person.setOfficeId(p.getOfficeId());
                person.setOfficeName(p.getOfficeName());
                person.setOfficeAreaId(p.getOfficeAreaId());
                person.setNames(p.getNames());
                person.setStationCode(p.getStationCode());
            } else {
                throw new CommonException(ErrorCode.USER_NOT_EXIST);
            }
        }
        String jwtToken = TokenUtils.createSimpleToken(person);
        SecurityUtils.getSubject().getSession().setAttribute("jwtToken", jwtToken);
        return authenticationInfo;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SecurityUtils.getSubject();
        return new SimpleAuthorizationInfo();

    }
}
