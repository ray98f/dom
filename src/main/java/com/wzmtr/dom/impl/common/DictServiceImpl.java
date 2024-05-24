package com.wzmtr.dom.impl.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.common.DictReqDTO;
import com.wzmtr.dom.dto.res.common.DictResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.common.DictMapper;
import com.wzmtr.dom.service.common.DictService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共分类-字典值管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Page<DictResDTO> page(String name, String code, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dictMapper.page(pageReqDTO.of(), name, code);
    }

    @Override
    public List<DictResDTO> list(String typeCode, String code, String status) {
        return dictMapper.list(typeCode, code, status);
    }

    @Override
    public DictResDTO detail(String id) {
        return dictMapper.detail(id);
    }

    @Override
    public void add(DictReqDTO dictReqDTO) {
        dictReqDTO.setId(TokenUtils.getUuId());
        dictReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        dictMapper.add(dictReqDTO);
    }

    @Override
    public void modify(DictReqDTO dictReqDTO) {
        dictReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        dictMapper.modify(dictReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dictMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }


}
