package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.req.operate.SecurityCleaningReqDTO;
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

    void add(OperateFaultStatisticsReqDTO req);

    void modify(OperateFaultStatisticsReqDTO req);

    Integer selectIsExist(OperateFaultStatisticsReqDTO req);

    void delete(List<String> ids, String currentPersonId);
}
