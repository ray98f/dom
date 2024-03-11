package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.DictTypeReqDTO;
import com.wzmtr.dom.dto.res.common.DictTypeResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 公共分类-字典类型管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface DictTypeService {

    /**
     * 分页查询字典类型列表
     * @param name 名称
     * @param pageReqDTO 分页参数
     * @return 字典类型列表
     */
    Page<DictTypeResDTO> page(String name, PageReqDTO pageReqDTO);

    /**
     * 获取字典类型详情
     * @param id id
     * @return 字典类型详情
     */
    DictTypeResDTO detail(Integer id);

    /**
     * 新增字典类型
     * @param dictTypeReqDTO 字典类型参数
     */
    void add(DictTypeReqDTO dictTypeReqDTO);

    /**
     * 编辑字典类型
     * @param dictTypeReqDTO 字典类型参数
     */
    void modify(DictTypeReqDTO dictTypeReqDTO);

    /**
     * 删除字典类型
     * @param ids ids
     */
    void delete(List<String> ids);
}
