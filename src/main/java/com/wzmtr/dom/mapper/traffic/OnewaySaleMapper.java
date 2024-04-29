package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OneWaySaleDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:32
 */
@Mapper
@Repository
public interface OnewaySaleMapper extends BaseMapper<TrafficOnewaySaleDO> {
    Page<OnewaySaleListResDTO> list(Page<Object> of, @Param("req") OnewaySaleListReqDTO reqDTO);

    TrafficOnewaySaleDO detail(OneWaySaleDetailReqDTO reqDTO);
}
