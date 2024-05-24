package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.MaintenanceInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.MaintenanceRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceRecordResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventDetailResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 13:51
 */
@Mapper
@Repository
public interface MaintenanceMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<MaintenanceRecordResDTO> list(Page<MaintenanceRecordResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    List<MaintenanceRecordResDTO> listAll(String startDate, String endDate);


    /**
     * 详情
     * @param id 入参数
     * @return LineEventResDTO
     */
    MaintenanceRecordResDTO queryInfoById(String id,String startDate, String endDate);

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
     * @param maintenanceRecordReqDTO 入参数
     */
    void add(MaintenanceRecordReqDTO maintenanceRecordReqDTO);

    /**
     * 编辑
     * @param maintenanceRecordReqDTO 入参数
     */
    int modify(MaintenanceRecordReqDTO maintenanceRecordReqDTO);

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
    MaintenanceInfoResDTO queryDateRange(List<String> ids);

    /**
     * 事件信息-列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<MaintenanceInfoResDTO> eventList(Page<MaintenanceInfoResDTO> page, String startDate, String endDate);

    /**
     * 事件信息-新增
     * @param maintenanceInfoReqDTO 入参数
     */
    void createEvent(MaintenanceInfoReqDTO maintenanceInfoReqDTO);

    /**
     * 事件信息-编辑
     * @param maintenanceInfoReqDTO 入参数
     * @return 更新结果
     */
    int modifyEvent(MaintenanceInfoReqDTO maintenanceInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     * @param userId 用户id
     */
    void deleteEvent(List<String> ids, String userId);

    void autoModifyByDaily(String dataType, String startDate, String endDate);

}
