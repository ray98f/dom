package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:32
 */
@Mapper
@Repository
public interface HotLineImportantMapper extends BaseMapper<TrafficHotlineImportantDO> {
}
