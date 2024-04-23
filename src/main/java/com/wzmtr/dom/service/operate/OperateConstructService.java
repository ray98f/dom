package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.res.operate.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 运营日报-运营事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface OperateConstructService {

    /**
     * 正运营事件记录-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 运营事件记录列表
     */
    Page<ConstructRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 正线/车场事件记录-新增
     * @param currentLoginUser 入参数
     * @param constructRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, ConstructRecordReqDTO constructRecordReqDTO);

    /**
     * 正线/车场事件记录-新增
     * @param currentLoginUser 入参数
     * @param constructRecordReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, ConstructRecordReqDTO constructRecordReqDTO);

    /**
     * 选择施工计划-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    Page<ConstructPlanResDTO> getCsmConstructPlan(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 施工计划-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    Page<ConstructPlanResDTO> planList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 施工计划-新增
     * @param currentLoginUser 入参数
     * @param constructPlanBatchReqDTO 入参数
     */
    void createPlan(CurrentLoginUser currentLoginUser, ConstructPlanBatchReqDTO constructPlanBatchReqDTO);

    /**
     * 车场施工计划-删除
     * @param ids ids
     */
    void deletePlan(List<String> ids);

    /**
     * 事件信息-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 事件信息列表
     */
    Page<ConstructEventResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 事件信息-新增
     * @param currentLoginUser 入参数
     * @param constructEventReqDTO 入参数
     */
    void createEvent(CurrentLoginUser currentLoginUser, ConstructEventReqDTO constructEventReqDTO);

    /**
     * 事件信息-编辑
     * @param currentLoginUser 入参数
     * @param constructEventReqDTO 入参数
     */
    void modifyEvent(CurrentLoginUser currentLoginUser, ConstructEventReqDTO constructEventReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deleteEvent(List<String> ids);
    /**
     * 正线/车场事件记录-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    ConstructRecordResDTO detail(String id, String startDate, String endDate);
}
