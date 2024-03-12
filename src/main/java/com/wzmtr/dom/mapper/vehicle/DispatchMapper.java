package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchRecordReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchOrderResDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchRecordResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-调度命令管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Mapper
@Repository
public interface DispatchMapper {

    /**
     * 分页查询调度命令记录列表
     * @param page 分页参数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 调度命令记录列表
     */
    Page<DispatchRecordResDTO> pageRecord(Page<DispatchRecordResDTO> page, String startTime, String endTime);

    /**
     * 获取调度命令详情列表
     * @param recordId 记录id
     * @return 调度命令详情列表
     */
    List<DispatchOrderResDTO> listOrder(String recordId);

    /**
     * 查询当天调度命令是否已存在
     * @param dispatchReqDTO 调度命令参数
     * @return 是否已存在
     */
    Integer selectRecordIsExist(DispatchReqDTO dispatchReqDTO);

    /**
     * 新增调度命令记录
     * @param dispatchRecordReqDTO 调度命令记录参数
     */
    void addRecord(DispatchRecordReqDTO dispatchRecordReqDTO);

    /**
     * 新增调度命令详情
     * @param dispatchOrderReqDTO 调度命令详情参数
     */
    void addOrder(DispatchOrderReqDTO dispatchOrderReqDTO);

    /**
     * 编辑调度命令
     * @param dispatchRecordReqDTO 调度命令参数
     */
    void modifyRecord(DispatchRecordReqDTO dispatchRecordReqDTO);

    /**
     * 编辑调度命令详情
     * @param dispatchOrderReqDTO 调度命令详情参数
     */
    void modifyOrder(DispatchOrderReqDTO dispatchOrderReqDTO);

    /**
     * 删除调度命令记录
     * @param ids ids
     * @param userId 用户id
     */
    void deleteRecord(List<String> ids, String userId);

    /**
     * 删除调度命令详情
     * @param ids ids
     * @param userId 用户id
     */
    void deleteOrder(List<String> ids, String userId);
}
