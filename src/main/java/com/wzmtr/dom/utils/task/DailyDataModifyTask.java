package com.wzmtr.dom.utils.task;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.*;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.vehicle.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.service.operate.*;
import com.wzmtr.dom.service.traffic.*;
import com.wzmtr.dom.service.vehicle.*;
import lombok.AllArgsConstructor;
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
    @Autowired
    private SecurityService securityService;
    @Autowired
    private CrewAttentionService crewAttentionService;

    @Autowired
    private PassengerService trafficService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private TicketUseService ticketUseService;
    @Autowired
    private OnewaySaleService onewaySaleService;
    @Autowired
    private HotLineSummaryService hotLineSummaryService;
    @Autowired
    private HotLineHandoverService hotLineHandoverService;
    @Autowired
    private ProductionSummaryService productionSummaryService;
    @Autowired
    private ImportantWorkService importantWorkService;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private HotLineService hotLineService;
    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private OperatePassengerFlowService operatePassengerFlowService;
    @Autowired
    private OperateIndicatorService operateIndicatorService;
    @Autowired
    private OperateEventService operateEventService;
    @Autowired
    private OperateConstructService operateConstructService;
    @Autowired
    private DebugService debugService;
    @Autowired
    private SpeedLimitService speedLimitService;
    @Autowired
    private OperateFaultStatisticsService faultStatisticsService;
    @Autowired
    private SecurityCleaningService securityCleaningService;

    // 定时任务逻辑
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
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(today));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(today));
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder().personId(CommonConstants.ADMIN).personNo(CommonConstants.ADMIN).build();

        //创建本日数据
        trafficDailyAuto(currentLoginUser,today);

        //创建本周数据
        if(today.equals(DateUtil.formatDate(sunday))){
            trafficWeeklyAuto(currentLoginUser,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
        }

        //创建本月数据
        if(today.equals(DateUtil.formatDate(monthEnd))){
            trafficMonthlyAuto(currentLoginUser,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
        }

    }

    /**
     * 运营日报每日数据新增
     * */
    @Scheduled(cron = "0 20 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void operateAutoCreateTask() {
        String today = DateUtil.today();
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(today));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(today));
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder().personId(CommonConstants.ADMIN).personNo(CommonConstants.ADMIN).build();

        //创建本日数据
        operateDailyAuto(currentLoginUser,today);

        //创建本周数据
        if(today.equals(DateUtil.formatDate(sunday))){
            operateWeeklyAuto(currentLoginUser,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
        }

        //创建本月数据
        if(today.equals(DateUtil.formatDate(monthEnd))){
            operateMonthlyAuto(currentLoginUser,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
        }
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
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder().personId(CommonConstants.ADMIN).personNo(CommonConstants.ADMIN).build();

        //更新昨日数据
        vehicleDailyAutoModify(currentLoginUser,today);

        //更新上周数据
        if(today.equals(DateUtil.formatDate(monday))){
            vehicleWeeklyAutoModify(currentLoginUser,today);
        }

        //更新上月数据
        if(today.equals(DateUtil.formatDate(monthStart))){
            vehicleMonthlyAutoModify(currentLoginUser,today);
        }
    }

    /**
     * 客运部每日数据同步更新
     * */
    @Scheduled(cron = "0 10 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void trafficSyncData() {
        String today = DateUtil.today();
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(today));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(today));
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder().personId(CommonConstants.ADMIN).personNo(CommonConstants.ADMIN).build();

        //更新昨日数据
        trafficDailyAutoModify(currentLoginUser,today);

        //更新上周数据
        if(today.equals(DateUtil.formatDate(monday))){
            trafficWeeklyAutoModify(currentLoginUser,today);
        }

        //更新上月数据
        if(today.equals(DateUtil.formatDate(monthStart))){
            trafficMonthlyAutoModify(currentLoginUser,today);
        }
    }

    /**
     * 运营日报每日数据同步更新
     * */
    @Scheduled(cron = "0 20 5 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void operateSyncData() {
        String today = DateUtil.today();
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(today));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(today));
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder().personId(CommonConstants.ADMIN).personNo(CommonConstants.ADMIN).build();

        //更新昨日数据
        operateDailyAutoModify(currentLoginUser,today);

        //更新上周数据
        if(today.equals(DateUtil.formatDate(monday))){
            operateWeeklyAutoModify(currentLoginUser,today);
        }

        //更新上月数据
        if(today.equals(DateUtil.formatDate(monthStart))){
            operateMonthlyAutoModify(currentLoginUser,today);
        }
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
        DispatchRecordReqDTO dispatchRecordReqDTO = DispatchRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(DateUtil.parseDate(monday)).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        dispatchService.addRecord(currentLoginUser,dispatchRecordReqDTO);

        //周其他情况-新增
        OtherRecordReqDTO otherRecordReqDTO = OtherRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(DateUtil.parseDate(monday)).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        otherRecordService.add(currentLoginUser,otherRecordReqDTO);

    }

    /**
     * 车辆部每月数据新增
     * */
    private void vehicleMonthlyAuto(CurrentLoginUser currentLoginUser,String monthStart,String monthEnd){

        //安全运营概述
        SecurityReqDTO securityReqDTO = SecurityReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        securityService.add(currentLoginUser,securityReqDTO);

        //重要指标-新增
        IndicatorReqDTO indicatorReqDTO = IndicatorReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        indicatorService.add(currentLoginUser,indicatorReqDTO);

        //行车情况-新增
        DrivingRecordReqDTO drivingRecordReqDTO = DrivingRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        drivingService.add(currentLoginUser,drivingRecordReqDTO);

        //正线及车场情况-新增
        LineEventRecordReqDTO lineEventRecordReqDTO = LineEventRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        lineEventService.add(currentLoginUser,lineEventRecordReqDTO);

        //乘务中心行车事件总结-新增
        CrewEventSummaryReqDTO crewEventSummaryReqDTO = CrewEventSummaryReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        crewEventService.add(currentLoginUser,crewEventSummaryReqDTO);

        //乘务中心注意事项-新增
        CrewAttentionReqDTO crewAttentionReqDTO = CrewAttentionReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        crewAttentionService.add(currentLoginUser,crewAttentionReqDTO);

        //乘务中心班组培训情况-新增
        TrainRecordReqDTO trainRecordReqDTO = TrainRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        trainRecordService.add(currentLoginUser,trainRecordReqDTO);

        //乘务中心班组演练情况-新增
        CrewDrillReqDTO crewDrillReqDTO = CrewDrillReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        crewDrillService.add(currentLoginUser,crewDrillReqDTO);

        //乘务中心班组业务情况-新增
        CrewBusinessReqDTO crewBusinessReqDTO = CrewBusinessReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        crewBusinessService.add(currentLoginUser,crewBusinessReqDTO);

        //车场及施工情况记录-新增
        DepotConstructRecordReqDTO depot280ConstructRecordReqDTO = DepotConstructRecordReqDTO.builder().depotCode(CommonConstants.STATION_280).dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        depotConstructService.add(currentLoginUser,depot280ConstructRecordReqDTO);
        DepotConstructRecordReqDTO depot281ConstructRecordReqDTO = DepotConstructRecordReqDTO.builder().depotCode(CommonConstants.STATION_281).dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        depotConstructService.add(currentLoginUser,depot281ConstructRecordReqDTO);

        //乘务中心情况总结-新增
        CrewSummaryReqDTO crewSummaryReqDTO = CrewSummaryReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        crewSummaryService.add(currentLoginUser,crewSummaryReqDTO);

        //调度命令-新增
        DispatchRecordReqDTO dispatchRecordReqDTO = DispatchRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).dataDate(DateUtil.parseDate(monthStart)).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        dispatchService.addRecord(currentLoginUser,dispatchRecordReqDTO);

        //其他情况-新增
        OtherRecordReqDTO otherRecordReqDTO = OtherRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).dataDate(DateUtil.parseDate(monthStart)).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        otherRecordService.add(currentLoginUser,otherRecordReqDTO);
    }

    /**
     * 车辆部每日数据更新
     * */
    private void vehicleDailyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);

        //重要指标 由行车及施工自动更新

        //行车情况-更新
        drivingService.syncData(currentLoginUser,null,CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //正线及车场情况
        lineEventService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,today,today);

        //车场施工情况记录
        depotConstructService.syncData(currentLoginUser,null,CommonConstants.STATION_280,CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
        depotConstructService.syncData(currentLoginUser,null,CommonConstants.STATION_281,CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //调度命令记录
        dispatchService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
    }

    /**
     * 车辆部每周数据更新
     * */
    private void vehicleWeeklyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);
        Date monday = DateUtil.beginOfWeek(previousDay);
        Date sunday = DateUtil.endOfWeek(previousDay);

        //基本由日报自动更新,有特殊遗漏项在下方补充更新
        //乘务中心行车事件总结
        crewEventService.autoModify(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
    }

    /**
     * 车辆部每月数据更新
     * */
    private void vehicleMonthlyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);
        Date monthStart = DateUtil.beginOfMonth(previousDay);
        Date monthEnd = DateUtil.endOfMonth(previousDay);

        //基本由日报自动更新,有特殊遗漏项在下方补充更新

        //乘务中心行车事件总结
        crewEventService.autoModify(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }

    /**
     * 客运部每日数据新增
     * */
    private void trafficDailyAuto(CurrentLoginUser currentLoginUser,String today){

        // 客流总体情况-新增
        PassengerRecordReqDTO passengerRecordReqDTO = PassengerRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).startDate(today).endDate(today).build();
        trafficService.add(currentLoginUser,passengerRecordReqDTO);

        //收益总体情况-新增
        IncomeAddReqDTO incomeAddReqDTO = IncomeAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
        incomeService.add(currentLoginUser,incomeAddReqDTO);

        //线网车票过闸使用情况-新增
        TicketUseReqDTO ticketUseReqDTO = TicketUseReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        ticketUseService.add(currentLoginUser,ticketUseReqDTO);

        //线网单程票发售情况-新增
        OnewaySaleAddReqDTO onewaySaleAddReqDTO = OnewaySaleAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        onewaySaleService.add(currentLoginUser,onewaySaleAddReqDTO);

        //服务热线汇总(热线重要内容记录)-新增
        HotLineSummaryAddReqDTO hotLineSummaryAddReqDTO = HotLineSummaryAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        hotLineSummaryService.add(currentLoginUser,hotLineSummaryAddReqDTO);

        //需要转交其他部门-新增
        HotLineHandoverAddReqDTO handoverAddReqDTO = HotLineHandoverAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        hotLineHandoverService.addRecord(currentLoginUser,handoverAddReqDTO);

        //安全生产情况汇总-新增
        //安全生产情况-新增
        for(String s:CommonConstants.S2_STATION_ARRAY){
            currentLoginUser.setStationCode(s);
            ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO = ProductionSummaryRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
            productionSummaryService.add(currentLoginUser, productionSummaryRecordReqDTO);
            ProductionRecordReqDTO productionRecordReqDTO = ProductionRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
            productionService.add(currentLoginUser,productionRecordReqDTO);
        }

    }

    /**
     * 客运部每周数据新增
     * */
    private void trafficWeeklyAuto(CurrentLoginUser currentLoginUser,String monday,String sunday){

        // 本周重点工作落实-新增
        ImportantWorkReqDTO importantWorkReqDTO = ImportantWorkReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        importantWorkService.add(currentLoginUser,importantWorkReqDTO);

        // 客流总体情况-新增
        PassengerRecordReqDTO passengerRecordReqDTO = PassengerRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        trafficService.add(currentLoginUser,passengerRecordReqDTO);

        //收益总体情况-新增
        IncomeAddReqDTO incomeAddReqDTO = IncomeAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(monday).startDate(monday).endDate(sunday).build();
        incomeService.add(currentLoginUser,incomeAddReqDTO);

        //线网车票过闸使用情况-新增
        TicketUseReqDTO ticketUseReqDTO = TicketUseReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(DateUtil.parseDate(monday)).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        ticketUseService.add(currentLoginUser,ticketUseReqDTO);

        //线网单程票发售情况-新增
        OnewaySaleAddReqDTO onewaySaleAddReqDTO = OnewaySaleAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        onewaySaleService.add(currentLoginUser,onewaySaleAddReqDTO);

        //服务热线汇总(热线重要内容记录)-新增
        HotLineSummaryAddReqDTO hotLineSummaryAddReqDTO = HotLineSummaryAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        hotLineSummaryService.add(currentLoginUser,hotLineSummaryAddReqDTO);

        //需要转交其他部门-新增
        HotLineHandoverAddReqDTO handoverAddReqDTO = HotLineHandoverAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        hotLineHandoverService.addRecord(currentLoginUser,handoverAddReqDTO);

        //安全生产汇总-新增
        ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO = ProductionSummaryRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        productionSummaryService.add(currentLoginUser, productionSummaryRecordReqDTO);

        //安全生产情况-新增
        ProductionRecordReqDTO productionRecordReqDTO = ProductionRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        productionService.add(currentLoginUser,productionRecordReqDTO);

        //设备维保施工情况-新增
        MaintenanceRecordReqDTO maintenanceRecordReqDTO = MaintenanceRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        maintenanceService.add(currentLoginUser,maintenanceRecordReqDTO);

    }

    /**
     * 客运部每月数据新增
     * */
    private void trafficMonthlyAuto(CurrentLoginUser currentLoginUser,String monthStart,String monthEnd){

        // 本周重点工作落实-新增
        ImportantWorkReqDTO importantWorkReqDTO = ImportantWorkReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        importantWorkService.add(currentLoginUser,importantWorkReqDTO);

        // 客流总体情况-新增
        PassengerRecordReqDTO passengerRecordReqDTO = PassengerRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        trafficService.add(currentLoginUser,passengerRecordReqDTO);

        //收益总体情况-新增
        IncomeAddReqDTO incomeAddReqDTO = IncomeAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        incomeService.add(currentLoginUser,incomeAddReqDTO);

        //线网车票过闸使用情况-新增
        TicketUseReqDTO ticketUseReqDTO = TicketUseReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        ticketUseService.add(currentLoginUser,ticketUseReqDTO);

        //线网单程票发售情况-新增
        OnewaySaleAddReqDTO onewaySaleAddReqDTO = OnewaySaleAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        onewaySaleService.add(currentLoginUser,onewaySaleAddReqDTO);

        //服务热线汇总(热线重要内容记录)-新增
        HotLineSummaryAddReqDTO hotLineSummaryAddReqDTO = HotLineSummaryAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        hotLineSummaryService.add(currentLoginUser,hotLineSummaryAddReqDTO);

        //安全生产汇总-新增
        ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO = ProductionSummaryRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        productionSummaryService.add(currentLoginUser, productionSummaryRecordReqDTO);

        //安全生产情况-新增
        ProductionRecordReqDTO productionRecordReqDTO = ProductionRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        productionService.add(currentLoginUser,productionRecordReqDTO);

        //设备维保施工情况-新增
        MaintenanceRecordReqDTO maintenanceRecordReqDTO = MaintenanceRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        maintenanceService.add(currentLoginUser,maintenanceRecordReqDTO);
    }

    /**
     * 客运部每日数据更新
     * */
    private void trafficDailyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);

        // 客流总体情况
        trafficService.autoModify(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
        trafficService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //收益总体情况
        incomeService.autoModify(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
        incomeService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //线网车票过闸使用情况
        ticketUseService.autoModify(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
        ticketUseService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //线网单程票发售情况
        onewaySaleService.autoModify(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
        onewaySaleService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //服务热线汇总(热线重要内容记录)
        hotLineSummaryService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //需要转交其他部门
        hotLineHandoverService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //安全生产情况汇总
        for(String s:CommonConstants.S2_STATION_ARRAY){
            productionSummaryService.autoModify(s,CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));
        }

        // 故障统计
        maintenanceService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

    }

    /**
     * 客运部每周数据更新
     * */
    private void trafficWeeklyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);
        Date monday = DateUtil.beginOfWeek(previousDay);
        Date sunday = DateUtil.endOfWeek(previousDay);

        //安全生产情况汇总
        productionSummaryService.autoModify(null,CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
    }

    /**
     * 客运部每月数据更新
     * */
    private void trafficMonthlyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);
        Date monthStart = DateUtil.beginOfMonth(previousDay);
        Date monthEnd = DateUtil.endOfMonth(previousDay);

        //安全生产情况汇总
        productionSummaryService.autoModify(null,CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }

    /**
     * 运营日报每日数据新增
     * */
    private void operateDailyAuto(CurrentLoginUser currentLoginUser,String today){

        //客流情况
        PassengerFlowAddReqDTO passengerFlowAddReqDTO = PassengerFlowAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
        operatePassengerFlowService.add(currentLoginUser, passengerFlowAddReqDTO);

        //初期运营指标
        IndicatorRecordReqDTO indicatorRecordReqDTO = IndicatorRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
        operateIndicatorService.add(currentLoginUser, indicatorRecordReqDTO);

        //运营事件
        OperateEventReqDTO operateEventReqDTO = OperateEventReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
        operateEventService.add(currentLoginUser,operateEventReqDTO);

        //施工情况
        ConstructRecordReqDTO constructRecordReqDTO = ConstructRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(today).startDate(today).endDate(today).build();
        operateConstructService.add(currentLoginUser, constructRecordReqDTO);

        //调试情况
        DebugRecordReqDTO debugRecordReqDTO = DebugRecordReqDTO.builder().dataDate(DateUtil.parseDate(today)).build();
        debugService.addRecord(currentLoginUser,debugRecordReqDTO);

        //线路限速情况
        SpeedLimitRecordReqDTO speedLimitRecordReqDTO = SpeedLimitRecordReqDTO.builder().dataDate(DateUtil.parseDate(today)).build();
        speedLimitService.addRecord(currentLoginUser,speedLimitRecordReqDTO);

        //故障统计
        OperateFaultStatisticsReqDTO operateFaultStatisticsReqDTO = OperateFaultStatisticsReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).dataDate(DateUtil.parseDate(today)).startDate(DateUtil.parseDate(today)).endDate(DateUtil.parseDate(today)).build();
        faultStatisticsService.add(currentLoginUser,operateFaultStatisticsReqDTO);
    }

    /**
     * 运营日报每周数据新增
     * */
    private void operateWeeklyAuto(CurrentLoginUser currentLoginUser,String monday,String sunday){

        //客流情况
        PassengerFlowAddReqDTO passengerFlowAddReqDTO = PassengerFlowAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(monday).endDate(sunday).build();
        operatePassengerFlowService.add(currentLoginUser, passengerFlowAddReqDTO);

        //能耗统计(列车运行数据及运营指标)
        IndicatorRecordReqDTO indicatorRecordReqDTO = IndicatorRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(monday).startDate(monday).endDate(sunday).build();
        operateIndicatorService.add(currentLoginUser, indicatorRecordReqDTO);

        //事件及故障统计
        OperateFaultStatisticsReqDTO operateFaultStatisticsReqDTO = OperateFaultStatisticsReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(DateUtil.parseDate(monday)).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        faultStatisticsService.add(currentLoginUser,operateFaultStatisticsReqDTO);

        //施工情况
        ConstructRecordReqDTO constructRecordReqDTO = ConstructRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).dataDate(monday).startDate(monday).endDate(sunday).build();
        operateConstructService.add(currentLoginUser, constructRecordReqDTO);

        //服务热线
        HotLineReqDTO hotLineReqDTO = HotLineReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        hotLineService.add(currentLoginUser,hotLineReqDTO);

        //安检及保洁情况
        SecurityCleaningReqDTO securityCleaningReqDTO = SecurityCleaningReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.parseDate(monday)).endDate(DateUtil.parseDate(sunday)).build();
        securityCleaningService.add(currentLoginUser,securityCleaningReqDTO);
    }

    /**
     * 运营日报每月数据新增
     * */
    private void operateMonthlyAuto(CurrentLoginUser currentLoginUser,String monthStart,String monthEnd){
        //客流情况
        PassengerFlowAddReqDTO passengerFlowAddReqDTO = PassengerFlowAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(monthStart).endDate(monthEnd).build();
        operatePassengerFlowService.add(currentLoginUser, passengerFlowAddReqDTO);

        //能耗统计(列车运行数据及运营指标)
        IndicatorRecordReqDTO indicatorRecordReqDTO = IndicatorRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).dataDate(monthStart).startDate(monthStart).endDate(monthEnd).build();
        operateIndicatorService.add(currentLoginUser, indicatorRecordReqDTO);

        //事件及故障统计
        OperateFaultStatisticsReqDTO operateFaultStatisticsReqDTO = OperateFaultStatisticsReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).dataDate(DateUtil.parseDate(monthStart)).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        faultStatisticsService.add(currentLoginUser,operateFaultStatisticsReqDTO);

        //施工情况
        ConstructRecordReqDTO constructRecordReqDTO = ConstructRecordReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).dataDate(monthStart).startDate(monthStart).endDate(monthEnd).build();
        operateConstructService.add(currentLoginUser, constructRecordReqDTO);

        //服务热线
        HotLineReqDTO hotLineReqDTO = HotLineReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        hotLineService.add(currentLoginUser,hotLineReqDTO);

        //安检及保洁情况
        SecurityCleaningReqDTO securityCleaningReqDTO = SecurityCleaningReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.parseDate(monthStart)).endDate(DateUtil.parseDate(monthEnd)).build();
        securityCleaningService.add(currentLoginUser,securityCleaningReqDTO);
    }

    /**
     * 运营日报每日数据更新
     * */
    private void operateDailyAutoModify(CurrentLoginUser currentLoginUser,String today){
        //获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);

        //客流情况
        PassengerFlowAddReqDTO passengerFlowAddReqDTO = PassengerFlowAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_DAILY).startDate(DateUtil.formatDate(previousDay)).endDate(DateUtil.formatDate(previousDay)).build();
        operatePassengerFlowService.syncACCdata(passengerFlowAddReqDTO);

        //能耗统计(列车运行数据及运营指标)
        operateIndicatorService.syncData(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //施工情况
        operateConstructService.syncStatistics(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));

        //事件及故障统计
        faultStatisticsService.syncData(CommonConstants.DATA_TYPE_DAILY,DateUtil.formatDate(previousDay),DateUtil.formatDate(previousDay));


    }

    /**
     * 运营日报每周数据更新
     * */
    private void operateWeeklyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);
        Date monday = DateUtil.beginOfWeek(previousDay);
        Date sunday = DateUtil.endOfWeek(previousDay);

        //客流情况
        PassengerFlowAddReqDTO passengerFlowAddReqDTO = PassengerFlowAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_WEEKLY).startDate(DateUtil.formatDate(monday)).endDate(DateUtil.formatDate(sunday)).build();
        operatePassengerFlowService.syncACCdata(passengerFlowAddReqDTO);

        //能耗统计(列车运行数据及运营指标)
        operateIndicatorService.syncData(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

    }

    /**
     * 运营日报每月数据更新
     * */
    private void operateMonthlyAutoModify(CurrentLoginUser currentLoginUser,String today){
        // 获取前一天
        Date previousDay = DateUtil.offsetDay(DateUtil.parse(today), -1);
        Date monthStart = DateUtil.beginOfMonth(previousDay);
        Date monthEnd = DateUtil.endOfMonth(previousDay);

        //客流情况
        PassengerFlowAddReqDTO passengerFlowAddReqDTO = PassengerFlowAddReqDTO.builder().dataType(CommonConstants.DATA_TYPE_MONTHLY).startDate(DateUtil.formatDate(monthStart)).endDate(DateUtil.formatDate(monthEnd)).build();
        operatePassengerFlowService.syncACCdata(passengerFlowAddReqDTO);

        //能耗统计(列车运行数据及运营指标)
        operateIndicatorService.syncData(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }
}
