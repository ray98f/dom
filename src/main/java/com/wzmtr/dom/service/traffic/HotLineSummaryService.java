package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface HotLineSummaryService {

    HotLineSummaryDetailResDTO detail(HotLineSummaryDetailReqDTO reqDTO);

    void add(HotLineSummaryAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, HotLineSummaryAddReqDTO passengerRecordReqDTO);

    Page<HotLineSummaryListResDTO> list(HotLineSummaryListReqDTO reqDTO);

    HotLineSummaryDetailResDTO acc(SidReqDTO reqDTO);

    /**
     * 自动更新(周报/月报)报表统计
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     */
    void autoModifyByDaily(String dataType,String startDate,String endDate);
}
