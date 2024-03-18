package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchRecordReqDTO;
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
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 调度命令记录列表
     */
    Page<DispatchRecordResDTO> pageRecord(Page<DispatchRecordResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取调度命令详情列表
     * @param page 分页参数
     * @param id 记录id
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 调度命令详情列表
     */
    Page<DispatchOrderResDTO> pageOrder(Page<DispatchOrderResDTO> page, String id, String startDate, String endDate);

    /**
     * 根据id获取调度命令记录
     * @param id id
     * @return 调度命令记录
     */
    DispatchRecordResDTO getRecordDetail(String id);

    /**
     * 根据id获取调度命令详情
     * @param id id
     * @return 调度命令详情
     */
    DispatchOrderResDTO getOrderDetail(String id);

    /**
     * 查询当天调度命令记录是否已存在
     * @param dispatchRecordReqDTO 调度命令记录参数
     * @return 是否已存在
     */
    Integer selectRecordIsExist(DispatchRecordReqDTO dispatchRecordReqDTO);

    /**
     * 查询调度命令详情是否已修改
     * @param dispatchOrderReqDTO 调度命令详情参数
     * @return 是否已存在
     */
    Integer selectOrderIsExist(DispatchOrderReqDTO dispatchOrderReqDTO);

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
