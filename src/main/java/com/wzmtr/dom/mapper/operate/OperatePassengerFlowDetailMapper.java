package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Mapper
@Repository
public interface OperatePassengerFlowDetailMapper extends BaseMapper<OperatePassengerFlowDetailDO> {

    Page<PassengerFlowListResDTO> list(Page<Object> of, String dataType, String startDate, String endDate);

    void add(PassengerFlowAddReqDTO addReqDTO);

    int modify(PassengerFlowAddReqDTO req);

    void modifyCount(String id, String startDate, String endDate);
}
