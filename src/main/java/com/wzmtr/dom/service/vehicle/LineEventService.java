package com.wzmtr.dom.service.vehicle;

import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * 车辆部-正线/车场事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface LineEventService {

    /**
     * 正线/车场事件记录-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    IndicatorResDTO detail(String id);

    /**
     * 正线/车场事件记录-新增
     * @param currentLoginUser 入参数
     * @param lineEventRecordReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, LineEventRecordReqDTO lineEventRecordReqDTO);
}
