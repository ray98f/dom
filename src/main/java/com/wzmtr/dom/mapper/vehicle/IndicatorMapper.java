package com.wzmtr.dom.mapper.vehicle;

import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface IndicatorMapper {

    /**
     * 新增
     * @param commonReqDTO 入参数
     */
    void add(IndicatorReqDTO commonReqDTO);
}
