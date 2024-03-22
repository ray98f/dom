package com.wzmtr.dom.impl.system;

import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.res.system.AllStationResDTO;
import com.wzmtr.dom.dto.res.system.StationResDTO;
import com.wzmtr.dom.mapper.system.StationMapper;
import com.wzmtr.dom.service.system.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 车站管理
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/21 11:01
 */
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public AllStationResDTO allList(String lineCode) {
        AllStationResDTO res = new AllStationResDTO();
        List<StationResDTO> allList = stationMapper.allList(lineCode);
        res.setStationList(allList);
        res.setStationCodes(allList.stream().map(StationResDTO::getStationCode).collect(Collectors.joining(CommonConstants.COMMA)));
        return res;
    }
}
