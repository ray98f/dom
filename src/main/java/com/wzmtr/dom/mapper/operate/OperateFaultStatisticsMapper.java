package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.res.operate.fault.FaultStatisticsResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 16:25
 */
@Mapper
@Repository
public interface OperateFaultStatisticsMapper {

    Page<FaultStatisticsResDTO> list(Page<Object> of, String dataType, String startDate, String endDate);

    /**
     * 获取报表当日数据
     * @param date 日期
     * @return 当日数据
     */
    FaultStatisticsResDTO getDayDetail(String date);

    /**
     * 获取报表时间范围内数据
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 当月数据
     */
    FaultStatisticsResDTO getCurrentDetail(String startDate, String endDate);

    Integer selectIsExist(OperateFaultStatisticsReqDTO req);

    void add(OperateFaultStatisticsReqDTO req);

    void modify(OperateFaultStatisticsReqDTO req);

    void delete(List<String> ids, String currentPersonId);
}
