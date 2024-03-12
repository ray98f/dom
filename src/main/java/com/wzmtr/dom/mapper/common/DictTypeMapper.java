package com.wzmtr.dom.mapper.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.DictTypeReqDTO;
import com.wzmtr.dom.dto.res.common.DictTypeResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共分类-字典类型管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Mapper
@Repository
public interface DictTypeMapper {

    /**
     * 分页查询字典类型列表
     * @param page 分页参数
     * @param name 名称
     * @return 字典类型列表
     */
    Page<DictTypeResDTO> page(Page<DictTypeResDTO> page, String name);

    /**
     * 获取字典类型详情
     * @param id id
     * @return 字典类型详情
     */
    DictTypeResDTO detail(String id);

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
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
