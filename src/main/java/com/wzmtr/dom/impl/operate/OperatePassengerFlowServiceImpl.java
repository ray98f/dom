package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowDetailResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowTopThreeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.operate.OperatePassengerFlowDetailMapper;
import com.wzmtr.dom.mapper.operate.OperatePassengerFlowInfoMapper;
import com.wzmtr.dom.service.operate.OperatePassengerFlowService;
import com.wzmtr.dom.utils.BeanUtils;
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

    @Autowired
    private OperatePassengerFlowInfoMapper operatePassengerFlowInfoMapper;


    @Override
    public Page<PassengerFlowListResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return passengerFlowDetailMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public PassengerFlowDetailResDTO detail(String id) {
        OperatePassengerFlowDetailDO operatePassengerFlowDetailDO = passengerFlowDetailMapper.selectById(id);
        return BeanUtils.convert(operatePassengerFlowDetailDO, PassengerFlowDetailResDTO.class);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, PassengerFlowAddReqDTO operateEventReqDTO) {
        // 各车站客流信息插入info表
        // 乱七八糟的插入detail表
        // 还有个小时单向最大断面 也插到detail表
    }

    @Override
    public void delete(List<String> ids) {

    }

    @Override
    public List<PassengerFlowTopThreeListResDTO> topThree(String date,String dataType) {
        return operatePassengerFlowInfoMapper.topThree(date,dataType);
    }

    @Override
    public List<PassengerFlowTopThreeListResDTO> eachStation(String date, String dataType) {
        return null;
    }
}
