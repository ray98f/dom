package com.wzmtr.dom.impl.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.common.DictTypeReqDTO;
import com.wzmtr.dom.dto.res.common.DictTypeResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.common.DictTypeMapper;
import com.wzmtr.dom.service.common.DictTypeService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共分类-字典类型管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Override
    public Page<DictTypeResDTO> page(String name, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dictTypeMapper.page(pageReqDTO.of(), name);
    }

    @Override
    public DictTypeResDTO detail(String id) {
        return dictTypeMapper.detail(id);
    }

    @Override
    public void add(DictTypeReqDTO dictTypeReqDTO) {
        dictTypeReqDTO.setId(TokenUtils.getUuId());
        dictTypeReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        dictTypeMapper.add(dictTypeReqDTO);
    }

    @Override
    public void modify(DictTypeReqDTO dictTypeReqDTO) {
        dictTypeReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        dictTypeMapper.modify(dictTypeReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dictTypeMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
