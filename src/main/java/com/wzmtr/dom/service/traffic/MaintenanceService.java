package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.MaintenanceInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.MaintenanceRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 13:47
 */
public interface MaintenanceService {

    /**
     * 主要工作情况-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 主要工作情况列表
     */
    Page<MaintenanceRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 主要工作情况-详情
     * @param recordId 入参数
     * @return 主要工作情况-详情
     */
    MaintenanceRecordResDTO detail(String recordId, String startDate, String endDate);

    /**
     * 主要工作情况-新增
     * @param currentLoginUser 入参数
     * @param maintenanceRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, MaintenanceRecordReqDTO maintenanceRecordReqDTO);

    /**
     * 维修事件信息-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 维修事件信息列表
     */
    Page<MaintenanceInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 维修事件信息-新增
     * @param currentLoginUser 入参数
     * @param maintenanceInfoReqDTO 入参数
     */
    void createEvent(CurrentLoginUser currentLoginUser, MaintenanceInfoReqDTO maintenanceInfoReqDTO);

    /**
     * 维修事件信息-编辑
     * @param currentLoginUser 入参数
     * @param maintenanceInfoReqDTO 入参数
     */
    void modifyEvent(CurrentLoginUser currentLoginUser, MaintenanceInfoReqDTO maintenanceInfoReqDTO);

    /**
     * 维修事件信息-删除
     * @param ids ids
     */
    void deleteEvent(List<String> ids);
}
