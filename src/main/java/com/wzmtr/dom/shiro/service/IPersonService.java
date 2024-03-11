package com.wzmtr.dom.shiro.service;

import com.wzmtr.dom.shiro.model.Person;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2021/8/3 10:55
 */
public interface IPersonService {

    /**
     * 获取登录用户信息
     * @param no 用户登录账号
     * @return 用户信息
     */
    Person searchPersonByNo(String no);

}
