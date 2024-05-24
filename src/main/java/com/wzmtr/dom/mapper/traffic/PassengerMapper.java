package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerResDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客运部-客流总体情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface PassengerMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<PassengerResDTO> list(Page<PassengerResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return IndicatorResDTO
     */
    int checkExist(String dataType,String startDate,String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return PassengerDetailResDTO
     */
    PassengerDetailResDTO queryInfoById(String id,String dataType,String startDate,String endDate);

    /**
     * 新增
     * @param passengerRecordReqDTO 入参数
     */
    void add(PassengerRecordReqDTO passengerRecordReqDTO);

    /**
     * 编辑
     * @param passengerRecordReqDTO 入参数
     */
    int modify(PassengerRecordReqDTO passengerRecordReqDTO);

    /**
     * 客流数据更新
     * @param id 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void modifyCount(String id,String startDate,String endDate);

    /**
     * 车站客流量
     * @param startDate 入参数
     * @param endDate 入参数
     * @return 车站客流
     */
    List<PassengerInfoResDTO> stationPassenger(String startDate, String endDate);

    /**
     * 新增车站客流
     * @param passengerRecordReqDTO 入参数
     */
    void createStationPassenger(PassengerInfoReqDTO passengerRecordReqDTO);

    /**
     * 车站客流数据删除
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void deleteStationPassenger(String dataType,String startDate,String endDate);

    /**
     * 编辑车站客流
     * @param passengerInfoReqDTO 入参数
     * @return 更新数
     */
    int modifyStationPassenger(PassengerInfoReqDTO passengerInfoReqDTO);

    /**
     * 客流数据更新
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModify(String dataType,String startDate,String endDate);

    /**
     * 客流数据更新
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModifyByDaily(String dataType,String startDate,String endDate);
}
