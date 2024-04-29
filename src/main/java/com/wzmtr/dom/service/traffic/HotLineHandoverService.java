package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface HotLineHandoverService {

    Page<HotLineHandoverListResDTO> list(HotLineHandoverListReqDTO reqDTO);

    List<HotLineHandoverDetailResDTO> detail(String id, String date, String dataType, String startDate, String endDate);

    HotLineHandoverDetailResDTO acc(SidReqDTO reqDTO);

    void add(HotLineHandoverAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, HotLineHandoverAddReqDTO req);
}
