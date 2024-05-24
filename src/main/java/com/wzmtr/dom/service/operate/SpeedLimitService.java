package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.SpeedLimitInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.SpeedLimitRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitInfoResDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 运营-线路限速情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/01
 */
public interface SpeedLimitService {

    /**
     * 查询线路限速情况记录列表(分页)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 线路限速情况列表
     */
    Page<SpeedLimitRecordResDTO> recordPage(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 查询线路限速情况详情列表(分页)
     * @param id 记录id
     * @param pageReqDTO 分页参数
     * @return 线路限速情况详情列表
     */
    Page<SpeedLimitInfoResDTO> infoPage(String id, String startDate, String endDate, PageReqDTO pageReqDTO);

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
     */
    void deleteRecord(List<String> ids);

    /**
     * 删除线路限速情况详情
     * @param ids ids
     */
    void deleteInfo(List<String> ids);
}
