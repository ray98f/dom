package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface OperatePassengerFlowDetailMapper extends BaseMapper<OperatePassengerFlowDetailDO> {

    Page<PassengerFlowListResDTO> list(Page<Object> of, String dataType, String startDate, String endDate);

    OperatePassengerFlowDetailDO info(String id, String dataType, String startDate, String endDate);

    /**
     * 校验数据是否存在
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     * @return IndicatorResDTO
     */
    int checkExist(String dataType,String startDate,String endDate);

    void add(PassengerFlowAddReqDTO addReqDTO);

    int modify(PassengerFlowAddReqDTO req);

    void modifyCount(String id, String startDate, String endDate);
}
