package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.CrewEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.CrewEventSummaryReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventSummaryResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-乘务中心行车事件总结
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/14 8:52
 */
public interface CrewEventService {

    /**
     * 乘务中心行车事件总结-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件记录列表
     */
    Page<CrewEventSummaryResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 乘务中心行车事件总结-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    CrewEventSummaryResDTO detail(String id);

    /**
     * 乘务中心行车事件总结-新增
     * @param currentLoginUser 入参数
     * @param crewEventSummaryReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, CrewEventSummaryReqDTO crewEventSummaryReqDTO);

    /**
     * 乘务中心行车事件总结-编辑
     * @param currentLoginUser 入参数
     * @param crewEventSummaryReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, CrewEventSummaryReqDTO crewEventSummaryReqDTO);

    /**
     * 事件信息-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 事件信息列表
     */
    Page<CrewEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 事件信息-新增
     * @param currentLoginUser 入参数
     * @param crewEventInfoReqDTO 入参数
     */
    void createEvent(CurrentLoginUser currentLoginUser, CrewEventInfoReqDTO crewEventInfoReqDTO);

    /**
     * 事件信息-编辑
     * @param currentLoginUser 入参数
     * @param crewEventInfoReqDTO 入参数
     */
    void modifyEvent(CurrentLoginUser currentLoginUser, CrewEventInfoReqDTO crewEventInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deleteEvent(List<String> ids);
}
