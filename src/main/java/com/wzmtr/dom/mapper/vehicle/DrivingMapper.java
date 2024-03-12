package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.*;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.dto.res.vehicle.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-行车情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface DrivingMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<DrivingRecordResDTO> list(Page<DrivingRecordResDTO> page, String startDate, String endDate);

    /**
     * 行车情况-详情
     * @param id 入参数
     * @return 行车情况-详情
     */
    DrivingRecordDetailResDTO queryInfoById(String id);

    /**
     * 新增
     * @param drivingRecordReqDTO 入参数
     * @return int
     */
    int add(DrivingRecordReqDTO drivingRecordReqDTO);

    /**
     * 编辑
     * @param drivingRecordReqDTO 入参数
     * @return int
     */
    int modify(DrivingRecordReqDTO drivingRecordReqDTO);

    /**
     * 统计数据更新
     * @param drivingCountReqDTO 入参数
     * @return int
     */
    int modifyCount(DrivingCountReqDTO drivingCountReqDTO);

    /**
     * 删除
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);

    /**
     * 新增车场行车情况
     * @param drivingDepotReqDTO 入参数
     * @return int
     */
    int createDepotData(DrivingDepotReqDTO drivingDepotReqDTO);

    /**
     * 编辑车场行车情况
     * @param drivingDepotReqDTO 入参数
     * @return int
     */
    int modifyDepotData(DrivingDepotReqDTO drivingDepotReqDTO);

    /**
     * 车场
     * @param recordId 入参数
     * @return 车场
     */
    List<DrivingDepotResDTO> depot(String recordId);

    /**
     * 新增司机驾驶情况
     * @param drivingInfoReqDTO 入参数
     * @return int
     */
    int createInfoData(DrivingInfoReqDTO drivingInfoReqDTO);

    /**
     * 编辑司机驾驶情况
     * @param drivingInfoReqDTO 入参数
     * @return int
     */
    int modifyInfoData(DrivingInfoReqDTO drivingInfoReqDTO);


    /**
     * 司机驾驶情况
     * @param recordId 入参数
     * @return 司机驾驶情况
     */
    DrivingInfoResDTO driveInfo(String recordId);

}
