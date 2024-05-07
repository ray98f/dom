package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.res.operate.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运营日报-施工情况
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 16:25
 */
@Mapper
@Repository
public interface OperateConstructMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ConstructRecordResDTO> list(Page<ConstructRecordResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return LineEventResDTO
     */
    ConstructRecordResDTO queryInfoById(String id,String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return IndicatorResDTO
     */
    int checkExist(String dataType,String startDate,String endDate);

    /**
     * 新增
     * @param constructRecordReqDTO 入参数
     */
    void add(ConstructRecordReqDTO constructRecordReqDTO);

    /**
     * 编辑
     * @param constructRecordReqDTO 入参数
     */
    int modify(ConstructRecordReqDTO constructRecordReqDTO);

    /**
     * 施工计划列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ConstructPlanResDTO> planList(Page<ConstructPlanResDTO> page,String startDate, String endDate);

    /**
     * 施工计划-新增
     * @param constructPlanReqDTO 入参数
     */
    void createPlan(ConstructPlanReqDTO constructPlanReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deletePlan(List<String> ids);

    /**
     * 事件信息-列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ConstructEventResDTO> eventList(Page<ConstructEventResDTO> page,String startDate,String endDate);

    /**
     * 事件信息-新增
     * @param constructEventReqDTO 入参数
     */
    void createEvent(ConstructEventReqDTO constructEventReqDTO);

    /**
     * 事件信息-编辑
     * @param constructEventReqDTO 入参数
     * @return 更新结果
     */
    int modifyEvent(ConstructEventReqDTO constructEventReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     * @param userId 用户id
     */
    void deleteEvent(List<String> ids, String userId);

}
