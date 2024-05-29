package com.wzmtr.dom.utils.task;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.service.vehicle.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *  日报数据更新
 * @author  Ray
 * @version 1.0
 * @date 2023/11/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DailyDataModifyTask {

    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private DrivingService drivingService;
    @Autowired
    private LineEventService lineEventService;
    @Autowired
    private DepotConstructService depotConstructService;
    @Autowired
    private TrainRecordService trainRecordService;
    @Autowired
    private PersonRecordService personRecordService;
    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private DispatchHandoverService dispatchHandoverService;
    @Autowired
    private DrivingAttentionService drivingAttentionService;
    @Autowired
    private OtherRecordService otherRecordService;
    @Autowired
    private BadWeatherService badWeatherService;
    @Autowired
    private CrewEventService crewEventService;
    @Autowired
    private CrewDrillService crewDrillService;
    @Autowired
    private CrewBusinessService crewBusinessService;
    @Autowired
    private CrewSummaryService crewSummaryService;

    // TODO
    // 每日凌晨5点 更新一些需要同步其他系统数据的日报数据
    // 更新前一天的日报数据
    // 若是周一，更新上周一到周日的周报数据
    // 若是月初第一天，更新上一个月的月初到月末的月报数据

    /**
     * 车辆部每日数据新增
     * */
    @Scheduled(cron = "0 1 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void vehicleAutoCreateTask() {
        String today = DateUtil.today();
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(today));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(today));
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder().personId(CommonConstants.ADMIN).personNo(CommonConstants.ADMIN).build();

                //创建本日数据
        vehicleDailyAuto(currentLoginUser,today);

        //创建本周数据
        if(today.equals(DateUtil.formatDate(sunday))){
            vehicleWeeklyAuto(currentLoginUser,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
        }

        //创建本月数据
        if(today.equals(DateUtil.formatDate(monthEnd))){
            vehicleMonthlyAuto(currentLoginUser,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
        }

    }

    /**
     * 客运部每日数据新增
     * */
    @Scheduled(cron = "0 10 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void trafficAutoCreateTask() {
        String today = DateUtil.today();

    }

    /**
     * 运营日报每日数据新增
     * */
    @Scheduled(cron = "0 20 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void operateAutoCreateTask() {
        String today = DateUtil.today();

    }

    /**
     * 车辆部每日数据同步更新
     * */
    @Scheduled(cron = "0 1 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void vehicleSyncData() {
        String today = DateUtil.today();
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(today));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(today));
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));
    }

    /**
     * 客运部每日数据同步更新
     * */
    @Scheduled(cron = "0 10 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void trafficSyncData() {

    }

    /**
     * 运营日报每日数据同步更新
     * */
    @Scheduled(cron = "0 20 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void operateSyncData() {

    }


    /**
     * 车辆部每日数据新增
     * */
    private void vehicleDailyAuto(CurrentLoginUser currentLoginUser,String today){
        //重要指标-新增
        IndicatorReqDTO indicatorReqDTO = IndicatorReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).startDate(today).endDate(today).build();
        indicatorService.add(currentLoginUser,indicatorReqDTO);

        //行车情况-新增
        DrivingRecordReqDTO drivingRecordReqDTO = DrivingRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).startDate(today).endDate(today).build();
        drivingService.add(currentLoginUser,drivingRecordReqDTO);

        //正线及车场情况-新增
        LineEventRecordReqDTO lineEventRecordReqDTO = LineEventRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).startDate(today).endDate(today).build();
        lineEventService.add(currentLoginUser,lineEventRecordReqDTO);

        //车场施工情况记录-新增
        DepotConstructRecordReqDTO depot280ConstructRecordReqDTO = DepotConstructRecordReqDTO.builder().depotCode(CommonConstants.STATION_280).dataType(CommonConstants.DATA_TYPE_DAILY).startDate(today).endDate(today).build();
        depotConstructService.add(currentLoginUser,depot280ConstructRecordReqDTO);
        DepotConstructRecordReqDTO depot281ConstructRecordReqDTO = DepotConstructRecordReqDTO.builder().depotCode(CommonConstants.STATION_281).dataType(CommonConstants.DATA_TYPE_DAILY).startDate(today).endDate(today).build();
        depotConstructService.add(currentLoginUser,depot281ConstructRecordReqDTO);

        //班组培训情况-新增
        TrainRecordReqDTO trainRecordReqDTO = TrainRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        trainRecordService.add(currentLoginUser,trainRecordReqDTO);

        //当日人员情况-新增
        PersonRecordReqDTO personRecordReqDTO = PersonRecordReqDTO.builder().dataDate(DateUtil.parseDate(today)).build();
        personRecordService.add(currentLoginUser,personRecordReqDTO);

        //调度命令记录-新增
        DispatchRecordReqDTO dispatchRecordReqDTO = DispatchRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        dispatchService.addRecord(currentLoginUser,dispatchRecordReqDTO);

        //车场调度员交接班情况-新增
        DispatchHandoverReqDTO dispatchHandoverReqDTO = DispatchHandoverReqDTO.builder().dataDate(DateUtil.parseDate(today)).build();
        dispatchHandoverService.add(currentLoginUser,dispatchHandoverReqDTO);

        //行车注意事项-新增
        DrivingAttentionReqDTO drivingAttentionReqDTO = DrivingAttentionReqDTO.builder().dataDate(DateUtil.parseDate(today)).build();
        drivingAttentionService.add(currentLoginUser,drivingAttentionReqDTO);

        //其他情况-新增
        OtherRecordReqDTO otherRecordReqDTO = OtherRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).build();
        otherRecordService.add(currentLoginUser,otherRecordReqDTO);

        //恶劣天气组织-新增
        BadWeatherReqDTO badWeatherReqDTO = BadWeatherReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).build();
        badWeatherService.add(currentLoginUser,badWeatherReqDTO);

    }

    /**
     * 车辆部每周数据新增
     * */
    private void vehicleWeeklyAuto(CurrentLoginUser currentLoginUser,String monday,String sunday){
        //重要指标-新增
        IndicatorReqDTO indicatorReqDTO = IndicatorReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        indicatorService.add(currentLoginUser,indicatorReqDTO);

        //行车情况-新增
        DrivingRecordReqDTO drivingRecordReqDTO = DrivingRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        drivingService.add(currentLoginUser,drivingRecordReqDTO);

        //正线及车场情况-新增
        LineEventRecordReqDTO lineEventRecordReqDTO = LineEventRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        lineEventService.add(currentLoginUser,lineEventRecordReqDTO);

        //周乘务中心行车事件总结-新增
        CrewEventSummaryReqDTO crewEventSummaryReqDTO = CrewEventSummaryReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        crewEventService.add(currentLoginUser,crewEventSummaryReqDTO);

        //周乘务中心班组培训情况-新增
        TrainRecordReqDTO trainRecordReqDTO = TrainRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        trainRecordService.add(currentLoginUser,trainRecordReqDTO);

        //周乘务中心班组演练情况-新增
        CrewDrillReqDTO crewDrillReqDTO = CrewDrillReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        crewDrillService.add(currentLoginUser,crewDrillReqDTO);

        //周乘务中心班组业务情况-新增
        CrewBusinessReqDTO crewBusinessReqDTO = CrewBusinessReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        crewBusinessService.add(currentLoginUser,crewBusinessReqDTO);

        //周车场及施工情况记录-新增
        DepotConstructRecordReqDTO depot280ConstructRecordReqDTO = DepotConstructRecordReqDTO.builder().depotCode(CommonConstants.STATION_280).dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        depotConstructService.add(currentLoginUser,depot280ConstructRecordReqDTO);
        DepotConstructRecordReqDTO depot281ConstructRecordReqDTO = DepotConstructRecordReqDTO.builder().depotCode(CommonConstants.STATION_281).dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        depotConstructService.add(currentLoginUser,depot281ConstructRecordReqDTO);

        //周乘务中心情况总结-新增
        CrewSummaryReqDTO crewSummaryReqDTO = CrewSummaryReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        crewSummaryService.add(currentLoginUser,crewSummaryReqDTO);

        //周调度命令-新增
        DispatchRecordReqDTO dispatchRecordReqDTO = DispatchRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(monday)).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        dispatchService.addRecord(currentLoginUser,dispatchRecordReqDTO);

        //周其他情况-新增
        //OtherRecordReqDTO otherRecordReqDTO = OtherRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).build();
        //otherRecordService.add(currentLoginUser,otherRecordReqDTO);

    }

    /**
     * 车辆部每月数据新增
     * */
    private void vehicleMonthlyAuto(CurrentLoginUser currentLoginUser,String monthStart,String monthEnd){

    }
}
