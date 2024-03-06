package com.wzmtr.dom.shiro.service;

import com.wzmtr.dom.mapper.common.PersonMapper;
import com.wzmtr.dom.shiro.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonMapper personDao;

    @Override
    public Person searchPersonByNo(String no) {
        return personDao.searchPersonByNo(no);
    }
}
