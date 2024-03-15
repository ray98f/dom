package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部- 车场调车/施工情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 9:36
 */
@Mapper
@Repository
public interface DepotConstructMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param depotCode 查询参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<DepotConstructRecordResDTO> list(Page<DepotConstructRecordResDTO> page, String depotCode,String dataType, String startDate, String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return 详情
     */
    DepotConstructDetailResDTO queryInfoById(String id);

    /**
     * 校验数据是否存在
     * @param depotCode 入参数
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return IndicatorResDTO
     */
    int checkExist(String depotCode,String dataType,String startDate,String endDate);

    /**
     * 新增
     * @param depotConstructRecordReqDTO 入参数
     */
    void add(DepotConstructRecordReqDTO depotConstructRecordReqDTO);

    /**
     * 编辑
     * @param depotConstructRecordReqDTO 入参数
     */
    int modify(DepotConstructRecordReqDTO depotConstructRecordReqDTO);

    /**
     * 周报/月报车场统计数据更新
     * @param id 入参数
     * @param depotCode 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void modifyDepotCount(String id,String depotCode,String startDate,String endDate);

    /**
     * 施工计划列表
     * @param page 分页参数
     * @param depotCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<DepotConstructPlanResDTO> planList(Page<DepotConstructPlanResDTO> page, String depotCode, String startDate, String endDate);

    /**
     * 施工计划-新增
     * @param depotConstructPlanReqDTO 入参数
     */
    void createPlan(DepotConstructPlanReqDTO depotConstructPlanReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deletePlan(List<String> ids);
}
