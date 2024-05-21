package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.ProductionSummaryRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.MonthSummaryResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionSummaryResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 客运部-安全生产情况汇总
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 10:26
 */
public interface ProductionSummaryService {

    /**
     * 安全生产情况汇总-列表
     * @param dataType 查询参数
     * @param stationCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 客流总体情况列表
     */
    Page<ProductionSummaryResDTO> list(String dataType,String stationCode, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 安全生产情况汇总-详情
     * @return 客流总体情况-详情
     */
    ProductionSummaryResDTO detail(String id,String startDate,String endDate);;

    /**
     * 安全生产情况汇总-新增
     * @param currentLoginUser 入参数
     * @param productionSummaryRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO);

    /**
     * 安全生产情况汇总-编辑
     * @param currentLoginUser 入参数
     * @param productionSummaryRecordReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO);

    /**
     * 周报/月报数据更新
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 周报/月报数据更新
     */
    void autoModify(String stationCode,String dataType, String startDate, String endDate);
}
