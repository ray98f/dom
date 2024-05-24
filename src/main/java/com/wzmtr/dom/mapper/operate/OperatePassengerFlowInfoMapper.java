package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowInfoDO;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerInfoResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface OperatePassengerFlowInfoMapper extends BaseMapper<OperatePassengerFlowInfoDO> {


    int modifyStationPassenger(PassengerInfoReqDTO req);

    List<PassengerInfoResDTO> eachStation(String startDate, String endDate);

    void createStationPassenger(PassengerInfoReqDTO item);

    List<String> topThree(String startDate,String endDate,String dataType);
    List<String> lastThree(String startDate,String endDate,String dataType);
}
