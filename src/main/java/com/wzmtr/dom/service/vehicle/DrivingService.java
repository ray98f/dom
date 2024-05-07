package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DrivingDepotReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingDepotResDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingRecordDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-行车情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 11:13
 */
public interface DrivingService {

    /**
     * 行车情况-列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 行车情况列表
     */
    Page<DrivingRecordResDTO> list(String dataType,String startDate,String endDate, PageReqDTO pageReqDTO);

    /**
     * 行车情况-车场
     * @param recordId 入参数
     * @return 车场情况
     */
    DrivingRecordDetailResDTO detail(String recordId, String dataType, String startDate, String endDate);

    /**
     * 行车情况-新增
     * @param currentLoginUser 入参数
     * @param drivingRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, DrivingRecordReqDTO drivingRecordReqDTO);

    /**
     * 行车情况-编辑
     * @param currentLoginUser 入参数
     * @param drivingRecordReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, DrivingRecordReqDTO drivingRecordReqDTO);

    /**
     * 行车情况-数据提取
     * @param recordId 入参数
     */
    void syncData(CurrentLoginUser currentLoginUser,String recordId);

    /**
     * 行车情况-车场
     * @param recordId 入参数
     * @return 车场情况
     */
    List<DrivingDepotResDTO> depot(String recordId);

    /**
     * 行车情况-车场数据编辑
     * @param currentLoginUser 入参数
     * @param drivingDepotReqDTO 入参数
     */
    void depotModify(CurrentLoginUser currentLoginUser,DrivingDepotReqDTO drivingDepotReqDTO);

    /**
     * 行车情况-司机数据编辑
     * @param currentLoginUser 入参数
     * @param drivingInfoResDTO 入参数
     */
    void driverModify(CurrentLoginUser currentLoginUser, DrivingInfoReqDTO drivingInfoResDTO);
}
