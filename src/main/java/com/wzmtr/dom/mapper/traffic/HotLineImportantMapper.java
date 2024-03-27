package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
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
public interface HotLineImportantMapper extends BaseMapper<TrafficHotlineImportantDO> {
    void insertList(@Param("list") List<TrafficHotlineImportantDO> list);

    Integer selectIsExist(HotLineImportantAddReqDTO req);

    // void updateMany(List<TrafficHotlineImportantDO> list);

    Page<HotLineImportantListResDTO> list(Page<Object> of, HotLineImportantListReqDTO reqDTO);
}
