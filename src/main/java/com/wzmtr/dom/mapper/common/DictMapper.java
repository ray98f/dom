package com.wzmtr.dom.mapper.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.DictReqDTO;
import com.wzmtr.dom.dto.res.common.DictResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共分类-字典值管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Mapper
@Repository
public interface DictMapper {

    /**
     * 分页查询字典值列表
     * @param page 分页参数
     * @param name 名称
     * @param code 编号
     * @return 字典值列表
     */
    Page<DictResDTO> page(Page<DictResDTO> page, String name, String code);

    /**
     * 根据字典类型获取字典值
     * @param typeCode 类型编码
     * @param code 编码
     * @param status 状态
     * @return 字典值
     */
    List<DictResDTO> list(String typeCode, String code, String status);

    /**
     * 获取字典详情
     * @param id id
     * @return 字典详情
     */
    DictResDTO detail(String id);

    /**
     * 新增字典值
     * @param dictReqDTO 字典值参数
     */
    void add(DictReqDTO dictReqDTO);

    /**
     * 编辑字典值
     * @param dictReqDTO 字典值参数
     */
    void modify(DictReqDTO dictReqDTO);

    /**
     * 删除字典值
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
