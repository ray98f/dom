package com.wzmtr.dom.mapper.system;

import com.wzmtr.dom.dto.res.system.StationResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 系统参数-车站管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Mapper
@Repository
public interface StationMapper {

    /**
     *  车站列表
     * @param lineCode 所属线路
     * @return 车站列表
     */
    List<StationResDTO> allList(String lineCode);
    List<StationResDTO> listByCodes(Set<String> stationCode);


}
