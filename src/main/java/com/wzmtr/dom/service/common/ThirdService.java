package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.common.OpenDispatchOrderReqDTO;
import com.wzmtr.dom.dto.res.common.OpenDispatchOrderRes;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.PlanStatisticsResDTO;
import com.wzmtr.dom.dto.res.operate.UnsaturationConstructResDTO;

import java.util.List;

/**
 * 公共分类-第三方调用
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface ThirdService {

    /**
     * 获取不饱和施工作业
     * @return 施工作业列表
     */
    List<UnsaturationConstructResDTO> getUnsaturationConstruct(String startTime,String endTime);

    List<PlanStatisticsResDTO> getPlanStatistics(String startTime,String endTime);

    /**
     * 选择施工计划-列表
     * @param constructPlanReqDTO 查询参数
     * @return 施工计划列表
     */
    Page<ConstructPlanResDTO> getCsmConstructPlan(OpenConstructPlanReqDTO constructPlanReqDTO);

    /**
     * 选择重点施工计划-列表
     * @param constructPlanReqDTO 查询参数
     * @return 重点施工计划列表
     */
    Page<ConstructPlanResDTO> getCsmImportantConstructPlan(OpenConstructPlanReqDTO constructPlanReqDTO);

    /**
     * 选择调度命令-列表
     * @param dispatchOrderReqDTO 查询参数
     * @return 选择调度命令列表
     */
    List<OpenDispatchOrderRes> getCsmDispatchOrder(OpenDispatchOrderReqDTO dispatchOrderReqDTO);

}
