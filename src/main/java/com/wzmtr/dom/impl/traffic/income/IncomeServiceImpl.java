package com.wzmtr.dom.impl.traffic.income;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeListReqDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.service.traffic.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {
    @Override
    public Page<IncomeListResDTO> list(IncomeListReqDTO reqDTO) {
        //todo
        return null;
    }

    @Override
    public IncomeDetailResDTO detail(SidReqDTO req) {
        //todo
        return null;
    }

    @Override
    public void add(IncomeAddReqDTO reqDTO) {
        //todo
        IncomeListResDTO totalIncomeList = reqDTO.getTotalIncomeList();
    }


    @Override
    public void modify(CurrentLoginUser currentLoginUser, IncomeAddReqDTO reqDTO) {
        //todo
    }

}
