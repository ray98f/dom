package com.wzmtr.dom.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.StationRoleReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 系统参数-审核站权限管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
public interface StationRoleService {

    /**
     * 分页查询审核站权限列表
     * @param name 名称
     * @param pageReqDTO 分页参数
     * @return 审核站权限列表
     */
    Page<StationRoleResDTO> page(String name, PageReqDTO pageReqDTO);

    /**
     * 获取审核站权限详情
     * @param id id
     * @return 审核站权限详情
     */
    StationRoleResDTO detail(Integer id);

    /**
     * 新增审核站权限
     * @param stationRoleReqDTO 审核站权限参数
     */
    void add(StationRoleReqDTO stationRoleReqDTO);

    /**
     * 编辑审核站权限
     * @param stationRoleReqDTO 审核站权限参数
     */
    void modify(StationRoleReqDTO stationRoleReqDTO);

    /**
     * 删除审核站权限
     * @param ids ids
     */
    void delete(List<String> ids);
}
