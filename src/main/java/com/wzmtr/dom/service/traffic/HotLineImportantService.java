package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantListReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface HotLineImportantService {

    HotLineImportantDetailResDTO detail(SidReqDTO reqDTO);

    void add(HotLineImportantAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, HotLineImportantAddReqDTO passengerRecordReqDTO);

    Page<HotLineSummaryListResDTO> list(HotLineSummaryListReqDTO reqDTO);

    HotLineImportantDetailResDTO acc(SidReqDTO reqDTO);
}
