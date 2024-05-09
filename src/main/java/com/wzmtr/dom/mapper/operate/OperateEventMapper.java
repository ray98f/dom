package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateEventDetailReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.EventCountResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventDetailResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-正线/车场事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 16:25
 */
@Mapper
@Repository
public interface OperateEventMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<OperateEventResDTO> list(Page<OperateEventResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    List<OperateEventResDTO> listAll(String startDate, String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return LineEventResDTO
     */
    OperateEventResDTO queryInfoById(String id,String startDate, String endDate);

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
     * @param operateEventReqDTO 入参数
     */
    void add(OperateEventReqDTO operateEventReqDTO);

    /**
     * 编辑
     * @param id 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void modifyCount(String id,String startDate, String endDate);

    /**
     * 事件信息-日期范围
     * @param ids 查询参数
     * @return 事件信息
     */
    OperateEventInfoResDTO queryDateRange(List<String> ids);

    /**
     * 事件信息-列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<OperateEventInfoResDTO> eventList(Page<OperateEventInfoResDTO> page,String startDate,String endDate);

    /**
     * 事件信息-时间明细列表
     * @param eventId 事件ID
     * @return 列表
     */
    List<OperateEventDetailResDTO>  eventDetailById(String eventId);

    /**
     * 事件信息-统计
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 统计
     */
    List<EventCountResDTO> eventCount(String startDate, String endDate);

    /**
     * 事件信息-新增
     * @param operateEventInfoReqDTO 入参数
     */
    void createEvent(OperateEventInfoReqDTO operateEventInfoReqDTO);

    void deleteEventDetail(String eventId);
    void deleteEventDetailBatch(List<String> ids);

    void createEventDetail(String createBy,String parentId,List<OperateEventDetailReqDTO> list);

    /**
     * 事件信息-编辑
     * @param operateEventInfoReqDTO 入参数
     * @return 更新结果
     */
    int modifyEvent(OperateEventInfoReqDTO operateEventInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     * @param userId 用户id
     */
    void deleteEvent(List<String> ids, String userId);

}
