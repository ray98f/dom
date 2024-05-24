package com.wzmtr.dom.impl.common;

import com.wzmtr.dom.dto.res.common.LastReportResDTO;
import com.wzmtr.dom.mapper.operate.OperateReportMapper;
import com.wzmtr.dom.mapper.traffic.TrafficReportMapper;
import com.wzmtr.dom.mapper.vehicle.VehicleReportMapper;
import com.wzmtr.dom.service.common.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/15 8:42
 */
@Service
@Slf4j
public class HomeServiceImpl implements HomeService {


    @Autowired
    private VehicleReportMapper vehicleReportMapper;

    @Autowired
    private TrafficReportMapper trafficReportMapper;

    @Autowired
    private OperateReportMapper operateReportMapper;

    @Override
    public LastReportResDTO getLastReport() {

        LastReportResDTO res = new LastReportResDTO();
        //vehicle
        String vehicleDaily = vehicleReportMapper.getLastDaily();
        String vehicleWeekly = vehicleReportMapper.getLastWeekly();
        String vehicleMonthly = vehicleReportMapper.getLastMonthly();
        res.setVehicleDaily(vehicleDaily);
        res.setVehicleWeekly(vehicleWeekly);
        res.setVehicleMonthly(vehicleMonthly);

        //traffic
        String trafficDaily = trafficReportMapper.getLastDaily();
        String trafficWeekly = trafficReportMapper.getLastWeekly();
        String trafficMonthly = trafficReportMapper.getLastMonthly();
        res.setTrafficDaily(trafficDaily);
        res.setTrafficWeekly(trafficWeekly);
        res.setTrafficMonthly(trafficMonthly);

        //operate
        String operateDaily = operateReportMapper.getLastDaily();
        String operateWeekly = operateReportMapper.getLastWeekly();
        String operateMonthly = operateReportMapper.getLastMonthly();
        res.setOperateDaily(operateDaily);
        res.setOperateWeekly(operateWeekly);
        res.setOperateMonthly(operateMonthly);
        return res;
    }
}
