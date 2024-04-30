package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineSummaryDO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:32
 */
@Mapper
@Repository
public interface HotLineSummaryMapper extends BaseMapper<TrafficHotlineSummaryDO> {
    Page<HotLineSummaryListResDTO> list(Page<Object> of, @Param("req") HotLineSummaryListReqDTO reqDTO);

    /**
     * 判断服务热线汇总是否已存在
     * @param summary 服务热线汇总参数
     * @return 是否已存在
     */
    Integer selectIsExist(TrafficHotlineSummaryDO summary);

    TrafficHotlineSummaryDO detail(HotLineSummaryDetailReqDTO reqDTO);
}
