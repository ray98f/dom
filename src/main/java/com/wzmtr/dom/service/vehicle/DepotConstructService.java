package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanBatchReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructPlanResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部- 车场调车/施工情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 9:34
 */
public interface DepotConstructService {

    /**
     * 正线/车场事件记录-列表
     * @param depotCode 查询参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件记录列表
     */
    Page<DepotConstructRecordResDTO> list(String depotCode,String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 正线/车场事件记录-详情
     * @param id 入参数
     * @return 详情
     */
    DepotConstructDetailResDTO detail(String id);

    /**
     * 车场调车/施工情况记录-新增
     * @param currentLoginUser 入参数
     * @param depotConstructRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, DepotConstructRecordReqDTO depotConstructRecordReqDTO);

    /**
     * 车场调车/施工情况-编辑
     * @param currentLoginUser 入参数
     * @param depotConstructRecordReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, DepotConstructRecordReqDTO depotConstructRecordReqDTO);

    /**
     * 车场调车/施工情况-数据提取
     * @param currentLoginUser 入参数
     * @param recordId 入参数
     */
    void syncData(CurrentLoginUser currentLoginUser,String recordId,String depotCode,String dataType,String startDate,String endDate);

    /**
     * 选择施工计划-列表
     * @param depotCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    Page<ConstructPlanResDTO> getCsmConstructPlan(String depotCode, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 车场施工计划-列表
     * @param depotCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 车场施工计划列表
     */
    Page<DepotConstructPlanResDTO> planList(String depotCode, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 车场施工计划-新增
     * @param currentLoginUser 入参数
     * @param depotConstructPlanBatchReqDTO 入参数
     */
    void createPlan(CurrentLoginUser currentLoginUser, DepotConstructPlanBatchReqDTO depotConstructPlanBatchReqDTO);

    /**
     * 车场施工计划-删除
     * @param ids ids
     */
    void deletePlan(List<String> ids);

    /**
     * 更新统计数据
     * @param dataType dataType
     * @param startDate startDate
     * @param endDate endDate
     */
    void autoModify(String depotCode,String dataType,String startDate,String endDate);

    /**
     * 更新周报/月报统计
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 分页参数
     */
    void autoModifyByDaily(String depotCode,String dataType,String startDate,String endDate);
}
