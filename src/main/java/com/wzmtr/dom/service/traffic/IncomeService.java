package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeListReqDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * 客运部-收益总体情况
 *
 * @author zhangxin
 * @version 1.0O
 * @date 2024/3/8 16:22
 */
public interface IncomeService {

    Page<IncomeListResDTO> list(IncomeListReqDTO reqDTO);


    IncomeDetailResDTO detail(IncomeDetailReqDTO reqDTO);

    void add(IncomeAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, IncomeAddReqDTO passengerRecordReqDTO);


}
