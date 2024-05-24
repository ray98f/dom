package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerRecordReqDTO;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

/**
 * 客运部-客流总体情况
 *
 * @author zhangxin
 * @version 1.0O
 * @date 2024/3/8 16:22
 */
public interface PassengerService {

    /**
     * 客流总体情况-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 客流总体情况列表
     */
    Page<PassengerResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 客流总体情况-详情
     * @param recordId 入参数
     * @return 客流总体情况-详情
     */
    PassengerDetailResDTO detail(String recordId,String dataType,String startDate, String endDate);

    /**
     * 客流总体情况-新增
     * @param currentLoginUser 入参数
     * @param passengerRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, PassengerRecordReqDTO passengerRecordReqDTO);

    /**
     * 客流总体情况-编辑
     * @param currentLoginUser 入参数
     * @param passengerRecordReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, PassengerRecordReqDTO passengerRecordReqDTO);



    /**
     * 车站客流-编辑
     * @param currentLoginUser 入参数
     * @param passengerInfoReqDTO 入参数
     */
    void modifyStationPassenger(CurrentLoginUser currentLoginUser, PassengerInfoReqDTO passengerInfoReqDTO);

    /**
     * 车站客流同步
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
     void syncACCdata13(String id,String dataType, String startDate, String endDate);

    /**
     * 客流统计
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
     void autoModify(String dataType, String startDate, String endDate);

    /**
     * 客流统计
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
     void autoModifyByDaily(String dataType, String startDate, String endDate);
}
