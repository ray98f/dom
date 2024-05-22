package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-正线/车场事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface LineEventService {

    /**
     * 正线/车场事件记录-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件记录列表
     */
    Page<LineEventResDTO> list(String dataType,String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 正线/车场事件记录-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    LineEventDetailResDTO detail(String id);

    /**
     * 正线/车场事件记录-新增
     * @param currentLoginUser 入参数
     * @param lineEventRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, LineEventRecordReqDTO lineEventRecordReqDTO);

    /**
     * 事件信息-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 事件信息列表
     */
    Page<LineEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 事件信息-新增
     * @param currentLoginUser 入参数
     * @param lineEventInfoReqDTO 入参数
     */
    void createEvent(CurrentLoginUser currentLoginUser, LineEventInfoReqDTO lineEventInfoReqDTO);

    /**
     * 事件信息-编辑
     * @param currentLoginUser 入参数
     * @param lineEventInfoReqDTO 入参数
     */
    void modifyEvent(CurrentLoginUser currentLoginUser, LineEventInfoReqDTO lineEventInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deleteEvent(List<String> ids);

    /**
     * 更新统计数据
     * @param dataType dataType
     * @param startDate startDate
     * @param endDate endDate
     */
    void autoModify(String dataType,String startDate,String endDate);

    /**
     * 更新统计数据
     * @param dataType dataType
     * @param startDate startDate
     * @param endDate endDate
     */
    void autoModifyByDaily(String dataType,String startDate,String endDate);
}
