package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.ProductionApprovalReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 17:15
 */
public interface ProductionService {

    /**
     * 安全生产情况汇总-列表
     * @param dataType 查询参数
     * @param stationCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 安全生产情况汇总-列表
     */
    Page<ProductionRecordResDTO> list(String dataType, String stationCode, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 安全生产情况汇总-详情
     * @param id 入参数
     * @return 客流总体情况-详情
     */
    ProductionDetailResDTO detail(String id,String startDate,String endDate);

    /**
     * 安全生产情况汇总-详情
     * @return 客流总体情况-详情
     */
    ProductionDetailResDTO queryInfo(String dataType,String stationCode, String startDate,
                                     String endDate);

    /**
     * 安全生产情况汇总-新增
     * @param currentLoginUser 入参数
     * @param productionRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, ProductionRecordReqDTO productionRecordReqDTO);

    /**
     * 安全生产情况提交审核-新增
     * @param currentLoginUser 入参数
     * @param productionRecordReqDTO 入参数
     */
    void submitApproval(CurrentLoginUser currentLoginUser, ProductionRecordReqDTO productionRecordReqDTO);

    /**
     * 安全生产情况审核
     * @param currentLoginUser 入参数
     * @param productionApprovalReqDTO 入参数
     */
    void productionApproval(CurrentLoginUser currentLoginUser, ProductionApprovalReqDTO productionApprovalReqDTO);

    /**
     * 事件信息-列表
     * @param stationCode 查询参数
     * @param productionType 查询参数
     * @param dataType 数据类型 1日报 2周报 3月报
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 事件信息列表
     */
    Page<ProductionInfoResDTO> eventList(String stationCode, String productionType, String dataType,
                                         String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 安全生产事件-施工异常-列表-周报
     * @param stationCode 车站编码
     * @param productionType 事件类型
     * @param dataType 数据类型 1日报 2周报 3月报
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 安全生产事件-施工异常-列表-周报
     */
    Page<ProductionInfoWeeklyResDTO> weeklyEventList(String stationCode, String productionType, String dataType,
                                                     String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 事件信息-新增
     * @param currentLoginUser 入参数
     * @param productionInfoReqDTO 入参数
     */
    void createEvent(CurrentLoginUser currentLoginUser, ProductionInfoReqDTO productionInfoReqDTO);

    /**
     * 事件信息-编辑
     * @param currentLoginUser 入参数
     * @param productionInfoReqDTO 入参数
     */
    void modifyEvent(CurrentLoginUser currentLoginUser, ProductionInfoReqDTO productionInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     */
    void deleteEvent(List<String> ids);

    /**
     * 安全生产情况待审列表
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 安全生产情况待审列表
     */
    Page<ProductionApprovalResDTO> queryApproval(CurrentLoginUser currentLoginUser, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 更新数据
     * @param stationCode 入参数
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModifyByDaily(String stationCode,String dataType, String startDate, String endDate);
}
