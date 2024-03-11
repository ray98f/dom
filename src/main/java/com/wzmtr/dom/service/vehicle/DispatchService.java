package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DispatchReqDTO;
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
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageReqDTO 分页参数
     * @return 调度命令记录列表
     */
    Page<DispatchRecordResDTO> pageRecord(String startTime, String endTime, PageReqDTO pageReqDTO);

    /**
     * 获取调度命令详情列表
     * @param recordId 记录id
     * @return 调度命令详情列表
     */
    List<DispatchOrderResDTO> listOrder(String recordId);

    /**
     * 提取施工调度调度命令数据
     * @param time 日期
     * @return 调度命令数据
     */
    List<DispatchOrderResDTO> getCsmDispatch(String time);

    /**
     * 新增调度命令
     * @param dispatchReqDTO 调度命令参数
     */
    void add(DispatchReqDTO dispatchReqDTO);

    /**
     * 编辑调度命令
     * @param dispatchReqDTO 调度命令参数
     */
    void modify(DispatchReqDTO dispatchReqDTO);

    /**
     * 删除调度命令
     * @param ids ids
     */
    void delete(List<String> ids);
}
