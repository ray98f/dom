package com.wzmtr.dom.mapper.common;

import com.wzmtr.dom.shiro.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonMapper {

    /**
     * 获取登录用户信息
     * @param no 用户登录账号
     * @return 用户信息
     */
    Person searchPersonByNo(@Param("no") String no);

}
