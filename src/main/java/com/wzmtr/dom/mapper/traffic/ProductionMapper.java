package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.util.V;
import com.wzmtr.dom.dto.req.operate.OperateEventDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProEventDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionApprovalReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客运部-安全生产情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface ProductionMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param stationCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ProductionRecordResDTO> list(Page<ProductionRecordResDTO> page, String dataType, String stationCode, String startDate, String endDate);

    /**
     * 全列表
     * @param stationCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    List<ProductionRecordResDTO> listAll(String stationCode,String dataType, String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param stationCode 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return IndicatorResDTO
     */
    int checkExist(String dataType,String stationCode,String startDate,String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return ProductionDetailResDTO
     */
    ProductionDetailResDTO queryInfoById(String id,String startDate,String endDate);

    /**
     * 详情
     * @return ProductionDetailResDTO
     */
    ProductionDetailResDTO queryInfoByStation(String dataType,String stationCode, String startDate,
                                              String endDate);
    /**
     * 详情
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return ProductionSummaryResDTO
     */
    ProductionRecordResDTO queryInfoByDate(String dataType,String startDate,String endDate);

    /**
     * 新增
     * @param productionRecordReqDTO 入参数
     */
    void add(ProductionRecordReqDTO productionRecordReqDTO);

    /**
     * 编辑
     * @param productionRecordReqDTO 入参数
     */
    int modify(ProductionRecordReqDTO productionRecordReqDTO);

    /**
     * 编辑
     * @param id 入参数
     * @param stationCode 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void modifyRecordCount(String id,String stationCode,String startDate, String endDate);

    /**
     * 编辑
     * @param recordId 入参数
     * @param dataType 入参数
     * @param stationCode 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void modifySummaryCount(String recordId,String dataType,String stationCode,String startDate, String endDate);

    /**
     * 待审列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ProductionApprovalResDTO> queryApproval(Page<ProductionRecordResDTO> page, String startDate, String endDate);

    /**
     * 详情
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return ProductionApprovalResDTO
     */
    ProductionApprovalResDTO queryApprovalByDate(String dataType,String startDate, String endDate);

    /**
     * 详情
     * @param approvalId 入参数
     * @param recordId 入参数
     * @return ProductionApprovalResDTO
     */
    ProductionApprovalRelationResDTO queryApprovalRelationById(String approvalId, String recordId);

    /**
     *  创建安全生产情况审核记录
     * @param productionApprovalReqDTO 入参数
     */
    void createProductionApproval(ProductionApprovalReqDTO productionApprovalReqDTO);

    /**
     *  更新安全生产情况审核记录
     * @param productionApprovalReqDTO 入参数
     */
    void modifyProductionApproval(ProductionApprovalReqDTO productionApprovalReqDTO);

    /**
     *  创建安全生产情况审核记录
     * @param id 入参数
     * @param approvalId 入参数
     * @param recordId 入参数
     * @param createBy 入参数
     */
    void createProductionApprovalRelation(String id,String recordId,String approvalId,String createBy);

    /**
     *  更新安全生产情况审核记录s
     * @param approvalId 入参数
     * @param recordId 入参数
     * @param status 入参数
     * @param updateBy 入参数
     */
    void modifyProductionApprovalRelation(String approvalId,String recordId,String status,String updateBy);

    /**
     * 事件信息-列表
     * @param page 分页参数
     * @param productionType 查询参数
     * @param dataType 数据类型 1日报 2周报 3月报
     * @param stationCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ProductionInfoResDTO> eventPage(Page<ProductionInfoResDTO> page, String stationCode, String productionType,
                                         String dataType, String startDate, String endDate);

    List<ProEventDetailResDTO> eventDetailById(String infoId);

    /**
     * 周报获取安全生产具体情况记录
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ProductionRecordResDTO> listWeeklyRecord(Page<ProductionRecordResDTO> page, String startDate, String endDate);

    /**
     * 获取安全生产具体情况施工异常详情
     * @param stationCode 车站编号
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 安全生产具体情况施工异常详情
     */
    List<ProductionInfoWeeklyResDTO.ProductionTwo> listProductionTwo(String stationCode, String startDate, String endDate);

    /**
     * 事件信息-日期范围
     * @param ids 查询参数
     * @return 事件信息
     */
    ProductionInfoResDTO queryDateRange(List<String> ids);

    /**
     * 事件信息-新增
     * @param productionInfoReqDTO 入参数
     */
    void createEvent(ProductionInfoReqDTO productionInfoReqDTO);

    void deleteEventDetail(String eventId);
    void deleteEventDetailBatch(List<String> ids);

    void createEventDetail(String createBy,String parentId,List<ProEventDetailReqDTO> list);

    /**
     * 事件信息-编辑
     * @param productionInfoReqDTO 入参数
     * @return 更新结果
     */
    int modifyEvent(ProductionInfoReqDTO productionInfoReqDTO);

    /**
     * 事件信息-删除
     * @param ids ids
     * @param userId 用户id
     */
    void deleteEvent(List<String> ids, String userId);

    void autoModifyByDaily(String dataType, String startDate, String endDate);

}
