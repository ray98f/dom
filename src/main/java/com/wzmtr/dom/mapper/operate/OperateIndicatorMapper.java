package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.IndicatorInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorPowerReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorDetailResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorInfoResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorPowerResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorRecordResDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 运营日报-初期运营指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 16:25
 */
@Mapper
@Repository
public interface OperateIndicatorMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<IndicatorRecordResDTO> list(Page<IndicatorRecordResDTO> page, String dataType, String startDate, String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return LineEventResDTO
     */
    IndicatorDetailResDTO queryInfoById(String id);

    /**
     * 详情
     * @param recordId 入参数
      @return LineEventResDTO
     */
    List<IndicatorInfoResDTO> infoList(String recordId);

    /**
     * 详情
     * @param recordId 入参数
     @return LineEventResDTO
     */
    IndicatorPowerResDTO queryPower(String recordId);
    IndicatorPowerResDTO queryPowerByDate(String startDate, String endDate, String dataType);

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
     * @param indicatorRecordReqDTO 入参数
     */
    void add(IndicatorRecordReqDTO indicatorRecordReqDTO);

    /**
     * 新增
     * @param indicatorInfoReqDTO 入参数
     */
    void addInfo(IndicatorInfoReqDTO indicatorInfoReqDTO);

    /**
     * 新增
     * @param indicatorPowerReqDTO 入参数
     */
    void addPower(IndicatorPowerReqDTO indicatorPowerReqDTO);

    /**
     * 编辑
     * @param indicatorRecordReqDTO 入参数
     */
    int modify(IndicatorRecordReqDTO indicatorRecordReqDTO);

    /**
     * 编辑
     * @param indicatorInfoReqDTO 入参数
     */
    int modifyInfo(IndicatorInfoReqDTO indicatorInfoReqDTO);

    /**
     * 编辑
     * @param indicatorPowerReqDTO 入参数
     */
    int modifyPower(IndicatorPowerReqDTO indicatorPowerReqDTO);


    IndicatorInfoResDTO queryInfoByDate(String startDate, String endDate, String dataType);
}
