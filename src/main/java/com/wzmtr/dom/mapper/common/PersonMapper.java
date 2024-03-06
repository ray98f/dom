package com.wzmtr.dom.mapper.common;

import com.wzmtr.dom.shiro.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonMapper {

    Person searchPersonByNo(@Param("no") String no);

}
