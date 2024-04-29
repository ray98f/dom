package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.DebugInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.DebugRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.DebugInfoResDTO;
import com.wzmtr.dom.dto.res.operate.DebugRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 运营-调试情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
public interface DebugService {

    /**
     * 查询调试情况记录列表(分页)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 调试情况列表
     */
    Page<DebugRecordResDTO> recordPage(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 查询调试情况详情列表(分页)
     * @param id 记录id
     * @param dataType 数据类型 1 信号调试 2 车辆调试
     * @param pageReqDTO 分页参数
     * @return 调试情况详情列表
     */
    Page<DebugInfoResDTO> infoPage(String id, String dataType, PageReqDTO pageReqDTO);

    /**
     * 新增调试情况记录
     * @param debugRecordReqDTO 调试情况记录参数
     */
    void addRecord(DebugRecordReqDTO debugRecordReqDTO);

    /**
     * 新增调试情况详情
     * @param debugInfoReqDTO 调试情况详情参数
     */
    void addInfo(DebugInfoReqDTO debugInfoReqDTO);

    /**
     * 编辑调试情况记录
     * @param debugRecordReqDTO 调试情况记录参数
     */
    void modifyRecord(DebugRecordReqDTO debugRecordReqDTO);

    /**
     * 编辑调试情况详情
     * @param debugInfoReqDTO 调试情况详情参数
     */
    void modifyInfo(DebugInfoReqDTO debugInfoReqDTO);

    /**
     * 删除调试情况记录
     * @param ids ids
     */
    void deleteRecord(List<String> ids);

    /**
     * 删除调试情况详情
     * @param ids ids
     */
    void deleteInfo(List<String> ids);

    /**
     * 选择施工计划-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    Page<ConstructPlanResDTO> getCsmConstructPlan(String startDate, String endDate, PageReqDTO pageReqDTO);
}
