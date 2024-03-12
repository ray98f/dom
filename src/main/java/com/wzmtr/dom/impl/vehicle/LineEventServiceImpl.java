package com.wzmtr.dom.impl.vehicle;

import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.service.vehicle.LineEventService;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 20:02
 */
@Service
public class LineEventServiceImpl implements LineEventService {

    @Override
    public IndicatorResDTO detail(String id) {
        return null;
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, LineEventRecordReqDTO lineEventRecordReqDTO) {

    }
}
