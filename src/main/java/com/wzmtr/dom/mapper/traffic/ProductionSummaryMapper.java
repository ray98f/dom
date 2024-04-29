package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerRecordReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionSummaryRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionSummaryResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客运部-安全生产情况汇总
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface ProductionSummaryMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param stationCode 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<ProductionSummaryResDTO> list(Page<ProductionSummaryResDTO> page, String dataType, String stationCode,String startDate, String endDate);

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
     * @return ProductionSummaryResDTO
     */
    ProductionSummaryResDTO queryInfoById(String id,String startDate,String endDate);

    /**
     * 详情
     * @param stationCode 入参数
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return ProductionSummaryResDTO
     */
    ProductionSummaryResDTO queryInfoByDate(String stationCode,String dataType,String startDate,String endDate);

    /**
     * 详情
     * @param stationCode 入参数
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return ProductionSummaryResDTO
     */
    ProductionSummaryResDTO queryPreInfoByDate(String stationCode,String dataType,String startDate,String endDate);

    /**
     * 新增
     * @param productionSummaryRecordReqDTO 入参数
     */
    void add(ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO);

    /**
     * 编辑
     * @param productionSummaryRecordReqDTO 入参数
     */
    int modify(ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO);

}
