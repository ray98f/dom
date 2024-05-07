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

    /**
     * 判断单程票发售情况是否已存在
     * @param reqDTO 单程票发售情况参数
     * @return 是否已存在
     */
    Integer checkExist(TrafficOnewaySaleDO reqDTO);

    Page<OnewaySaleListResDTO> list(Page<Object> of, @Param("req") OnewaySaleListReqDTO req);

    TrafficOnewaySaleDO detail(OneWaySaleDetailReqDTO reqDTO);
}
