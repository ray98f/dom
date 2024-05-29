package com.wzmtr.dom.mapper.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.dto.req.system.StationRoleReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统参数-审核站权限管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Mapper
@Repository
public interface StationRoleMapper {

    /**
     * 分页查询审核站权限列表
     * @param page 分页参数
     * @return 审核站权限列表
     */
    Page<StationRoleResDTO> page(Page<StationRoleResDTO> page);

    /**
     * 获取审核站权限详情
     * @param id id
     * @return 审核站权限详情
     */
    StationRoleResDTO detail(String id);

    /**
     * 获取当前有效的审核站详情
     * @return 审核站权限详情
     */
    StationRoleResDTO getCurrentStation();

    Integer checkExist(StationRoleReqDTO stationRoleReqDTO);

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
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
