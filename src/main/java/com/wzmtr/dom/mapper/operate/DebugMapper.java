package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.DebugInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.DebugRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.DebugInfoResDTO;
import com.wzmtr.dom.dto.res.operate.DebugRecordResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运营-调试情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/01
 */
@Mapper
@Repository
public interface DebugMapper {

    /**
     * 查询调试情况记录列表(分页)
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 调试情况记录列表
     */
    Page<DebugRecordResDTO> recordPage(Page<DebugRecordResDTO> page, String startDate, String endDate);

    /**
     * 查询调试情况详情列表(分页)
     * @param page 分页参数
     * @param id 记录id
     * @param dataType 数据类型 1 信号调试 2 车辆调试
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 调试情况详情列表
     */
    Page<DebugInfoResDTO> infoPage(Page<DebugRecordResDTO> page, String id, String dataType,
                                   String startDate, String endDate);

    /**
     * 获取调试情况详情
     * @param id id
     * @return 调试情况详情
     */
    DebugInfoResDTO getInfoDetail(String id);

    /**
     * 查询当天调试情况记录是否已存在
     * @param debugRecordReqDTO 调试情况记录参数
     * @return 是否已存在
     */
    Integer selectRecordIsExist(DebugRecordReqDTO debugRecordReqDTO);

    /**
     * 新增调试情况记录
     * @param debugRecordReqDTO 调试情况记录参数
     */
    void addRecord(DebugRecordReqDTO debugRecordReqDTO);

    /**
     * 新增调试情况详情
     * @param debugInfoReqDTO 调试情况详情参数
     */
    void addInfo(DebugInfoReqDTO debugInfoReqDTO);

    /**
     * 记录数据增长
     * @param recordId 记录id
     * @param num 数量
     * @param dataType 类型
     */
    void incrementRecord(String recordId, Integer num, String dataType);

    /**
     * 编辑调试情况记录
     * @param debugRecordReqDTO 调试情况记录参数
     */
    void modifyRecord(DebugRecordReqDTO debugRecordReqDTO);

    /**
     * 编辑调试情况详情
     * @param debugInfoReqDTO 调试情况详情参数
     */
    void modifyInfo(DebugInfoReqDTO debugInfoReqDTO);

    /**
     * 删除调试情况记录
     * @param ids ids
     * @param userId 用户id
     */
    void deleteRecord(List<String> ids, String userId);

    /**
     * 删除调试情况详情
     * @param parentIds 记录ids
     * @param ids ids
     * @param userId 用户id
     */
    void deleteInfo(List<String> parentIds, List<String> ids, String userId);
}
