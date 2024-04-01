package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineHandoverDO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:32
 */
@Mapper
@Repository
public interface HotLineHandoverMapper extends BaseMapper<TrafficHotlineHandoverDO> {
    Page<HotLineHandoverListResDTO> list(Page<Object> of, @Param("req") HotLineHandoverListReqDTO reqDTO);

    Integer selectIsExist(HotLineHandoverAddReqDTO req);
    void insertList(List<TrafficHotlineHandoverDO> list);

    List<HotLineHandoverDetailResDTO> selectListByDate(String date,String dataType);
}
