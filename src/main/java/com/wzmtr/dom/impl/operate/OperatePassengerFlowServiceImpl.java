package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowDetailResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowTopThreeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.operate.OperatePassengerFlowDetailMapper;
import com.wzmtr.dom.service.operate.OperatePassengerFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 17:02
 */
@Service
public class OperatePassengerFlowServiceImpl implements OperatePassengerFlowService {
    @Autowired
    private OperatePassengerFlowDetailMapper passengerFlowDetailMapper;


    @Override
    public Page<PassengerFlowListResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        return null;
    }

    @Override
    public PassengerFlowDetailResDTO detail(String id) {
        return null;
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, PassengerFlowAddReqDTO operateEventReqDTO) {

    }

    @Override
    public void delete(List<String> ids) {

    }

    @Override
    public List<PassengerFlowTopThreeListResDTO> topThree(String startDate, String endDate) {
        return null;
    }

    @Override
    public List<PassengerFlowTopThreeListResDTO> eachStation(String startDate, String endDate) {
        return null;
    }
}
