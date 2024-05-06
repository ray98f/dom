package com.wzmtr.dom.service.traffic;

import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface HotLineImportantService {

    List<HotLineImportantDetailResDTO> detail(String date, String startDate, String endDate, String dataType);

    void add(HotLineImportantAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, HotLineImportantAddReqDTO passengerRecordReqDTO);

    List<HotLineImportantDetailResDTO> acc(SidReqDTO reqDTO);
}
