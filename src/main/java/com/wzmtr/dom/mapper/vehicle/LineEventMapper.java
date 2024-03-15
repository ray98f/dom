package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.*;
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
public interface LineEventMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<LineEventResDTO> list(Page<LineEventResDTO> page,String dataType,String startDate,String endDate);

    /**
     * 列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    List<LineEventResDTO> listAll(String startDate, String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return LineEventResDTO
     */
    LineEventDetailResDTO queryInfoById(String id);

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
     * @param lineEventRecordReqDTO 入参数
     */
    void add(LineEventRecordReqDTO lineEventRecordReqDTO);

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
    LineEventInfoResDTO queryDateRange(List<String> ids);

    /**
     * 事件信息-列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<LineEventInfoResDTO> eventList(Page<LineEventResDTO> page,String startDate,String endDate);

    /**
     * 事件信息-新增
     * @param lineEventInfoReqDTO 入参数
     */
    void createEvent(LineEventInfoReqDTO lineEventInfoReqDTO);

    /**
     * 事件信息-编辑
     * @param lineEventInfoReqDTO 入参数
     * @return 更新结果
     */
    int modifyEvent(LineEventInfoReqDTO lineEventInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     * @param userId 用户id
     */
    void deleteEvent(List<String> ids, String userId);

}
