package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface OnewaySaleService {

    OnewaySaleDetailResDTO detail(SidReqDTO reqDTO);

    void add(OnewaySaleAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, OnewaySaleAddReqDTO passengerRecordReqDTO);

    Page<OnewaySaleListResDTO> list(OnewaySaleListReqDTO reqDTO);
}
