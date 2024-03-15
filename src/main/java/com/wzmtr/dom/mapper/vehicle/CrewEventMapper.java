package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.CrewEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.CrewEventSummaryReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventSummaryCountResDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventSummaryResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-行车事件总结
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 16:25
 */
@Mapper
@Repository
public interface CrewEventMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<CrewEventSummaryResDTO> list(Page<CrewEventSummaryResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    List<CrewEventSummaryResDTO> listAll(String startDate, String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return LineEventResDTO
     */
    CrewEventSummaryResDTO queryInfoById(String id);

    /**
     * 查询各类型统计
     * @param startDate 入参数
     * @param endDate 入参数
     * @return 数量统计
     */
    List<CrewEventSummaryCountResDTO> querySumCount(String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return int
     */
    int checkExist(String dataType,String startDate,String endDate);

    /**
     * 新增
     * @param crewEventSummaryReqDTO 入参数
     */
    void add(CrewEventSummaryReqDTO crewEventSummaryReqDTO);

    /**
     * 编辑
     * @param crewEventSummaryReqDTO 入参数
     * @return 更新数
     */
    int modify(CrewEventSummaryReqDTO crewEventSummaryReqDTO);

    /**
     * 编辑
     * @param id 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void modifyCount(String id,String startDate, String endDate);

    /**
     * 事件信息-列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<CrewEventInfoResDTO> eventList(Page<LineEventResDTO> page, String startDate, String endDate);

    /**
     * 事件信息-日期范围
     * @param ids 查询参数
     * @return 事件信息
     */
    CrewEventInfoResDTO queryDateRange(List<String> ids);

    /**
     * 事件信息-新增
     * @param crewEventInfoReqDTO 入参数
     */
    void createEvent(CrewEventInfoReqDTO crewEventInfoReqDTO);

    /**
     * 事件信息-编辑
     * @param crewEventInfoReqDTO 入参数
     * @return 更新结果
     */
    int modifyEvent(CrewEventInfoReqDTO crewEventInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deleteEvent(List<String> ids);
}
