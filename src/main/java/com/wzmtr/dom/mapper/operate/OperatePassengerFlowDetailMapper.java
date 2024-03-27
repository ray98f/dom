package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface OperatePassengerFlowDetailMapper extends BaseMapper<OperatePassengerFlowDetailDO> {

}
