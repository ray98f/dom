package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface HotLineSummaryService {

    OnewaySaleDetailResDTO detail(SidReqDTO reqDTO);

    void add(OnewaySaleAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, OnewaySaleAddReqDTO passengerRecordReqDTO);

    Page<HotLineSummaryListResDTO> list(OnewaySaleListReqDTO reqDTO);
}
