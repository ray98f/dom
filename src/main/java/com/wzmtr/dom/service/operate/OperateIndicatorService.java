package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.IndicatorInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorPowerReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorDetailResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

/**
 * 运营日报-初期运营指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface OperateIndicatorService {

    /**
     * 初期运营指标-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 运营事件记录列表
     */
    Page<IndicatorRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 初期运营指标-详情
     * @param id 入参数
     * @re IndicatorResDTO
     */
    IndicatorDetailResDTO detail(String id, String dataType, String startDate, String endDate);

    /**
     * 初期运营指标-新增
     * @param currentLoginUser 入参数
     * @param indicatorRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO);

    /**
     * 初期运营指标-编辑
     * @param currentLoginUser 入参数
     * @param indicatorRecordReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO);

    /**
     * 初期运营指标-编辑八项指标
     * @param currentLoginUser 入参数
     * @param indicatorInfoReqDTO 入参数
     */
    void modifyInfo(CurrentLoginUser currentLoginUser, IndicatorInfoReqDTO indicatorInfoReqDTO);

    /**
     * 初期运营指标-新增
     * @param currentLoginUser 入参数
     * @param indicatorPowerReqDTO 入参数
     */
    void modifyPower(CurrentLoginUser currentLoginUser, IndicatorPowerReqDTO indicatorPowerReqDTO);

    /**
     * 同步数据
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void syncData(String dataType, String startDate,String endDate);
}
