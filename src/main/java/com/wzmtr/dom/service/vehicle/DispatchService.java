package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchOrderResDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-调度命令管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
public interface DispatchService {

    /**
     * 分页查询调度命令记录列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 调度命令记录列表
     */
    Page<DispatchRecordResDTO> pageRecord(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO);

    /**
     * 获取调度命令详情列表
     * @param recordId 记录id
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 调度命令详情列表
     */
    Page<DispatchOrderResDTO> pageOrder(String recordId, String dataType, PageReqDTO pageReqDTO);

    /**
     * 提取施工调度调度命令数据
     * @param recordId 记录id
     * @param dataType dataType
     * @param startDate startDate
     * @param endDate endDate
     * @return 调度命令数据
     */
    List<DispatchOrderResDTO> getCsmDispatch(String recordId, String dataType,String startDate,String endDate);

    /**
     * 新增调度命令记录
     * @param dispatchRecordReqDTO 调度命令记录参数
     */
    void addRecord(DispatchRecordReqDTO dispatchRecordReqDTO);

    /**
     * 编辑调度命令详情
     * @param dispatchOrderReqDTO 调度命令详情参数
     */
    void modifyOrder(DispatchOrderReqDTO dispatchOrderReqDTO);

    /**
     * 删除调度命令记录
     * @param ids ids
     */
    void deleteRecord(List<String> ids);

    /**
     * 删除调度命令详情
     * @param ids ids
     */
    void deleteOrder(List<String> ids);
}
