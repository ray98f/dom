package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.SpeedLimitInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.SpeedLimitRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitInfoResDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitRecordResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运营-线路限速情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/01
 */
@Mapper
@Repository
public interface SpeedLimitMapper {

    /**
     * 查询线路限速情况记录列表(分页)
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 线路限速情况记录列表
     */
    Page<SpeedLimitRecordResDTO> recordPage(Page<SpeedLimitRecordResDTO> page, String startDate, String endDate);

    /**
     * 查询线路限速情况详情列表(分页)
     * @param page 分页参数
     * @param id 记录id
     * @return 线路限速情况详情列表
     */
    Page<SpeedLimitInfoResDTO> infoPage(Page<SpeedLimitRecordResDTO> page, String id);

    /**
     * 获取线路限速情况详情
     * @param id id
     * @return 线路限速情况详情
     */
    SpeedLimitInfoResDTO getInfoDetail(String id);

    /**
     * 查询当天线路限速情况记录是否已存在
     * @param speedLimitRecordReqDTO 线路限速情况记录参数
     * @return 是否已存在
     */
    Integer selectRecordIsExist(SpeedLimitRecordReqDTO speedLimitRecordReqDTO);

    /**
     * 新增线路限速情况记录
     * @param speedLimitRecordReqDTO 线路限速情况记录参数
     */
    void addRecord(SpeedLimitRecordReqDTO speedLimitRecordReqDTO);

    /**
     * 新增线路限速情况详情
     * @param speedLimitInfoReqDTO 线路限速情况详情参数
     */
    void addInfo(SpeedLimitInfoReqDTO speedLimitInfoReqDTO);

    /**
     * 记录数据增长
     * @param recordId 记录id
     * @param num 数量
     */
    void incrementRecord(String recordId, Integer num);

    /**
     * 编辑线路限速情况记录
     * @param speedLimitRecordReqDTO 线路限速情况记录参数
     */
    void modifyRecord(SpeedLimitRecordReqDTO speedLimitRecordReqDTO);

    /**
     * 编辑线路限速情况详情
     * @param speedLimitInfoReqDTO 线路限速情况详情参数
     */
    void modifyInfo(SpeedLimitInfoReqDTO speedLimitInfoReqDTO);

    /**
     * 删除线路限速情况记录
     * @param ids ids
     * @param userId 用户id
     */
    void deleteRecord(List<String> ids, String userId);

    /**
     * 删除线路限速情况详情
     * @param parentIds 记录ids
     * @param ids ids
     * @param userId 用户id
     */
    void deleteInfo(List<String> parentIds, List<String> ids, String userId);
}
