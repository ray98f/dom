package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowInfoDO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowTopThreeListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface OperatePassengerFlowInfoMapper extends BaseMapper<OperatePassengerFlowInfoDO> {

    List<PassengerFlowTopThreeListResDTO> topThree(String date,String dataType);
}
