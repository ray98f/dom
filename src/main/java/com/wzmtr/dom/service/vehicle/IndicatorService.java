package com.wzmtr.dom.service.vehicle;

import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:22
 */
public interface IndicatorService {

    /**
     * 重要指标-新增
     * @param indicatorReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO);
}
