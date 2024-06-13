package com.wzmtr.dom.impl.operate;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.IndicatorInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorPowerReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorRecordReqDTO;
import com.wzmtr.dom.dto.res.common.OpenDepotStatisticsRes;
import com.wzmtr.dom.dto.res.common.OpenTrainMileRes;
import com.wzmtr.dom.dto.res.operate.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.DataType;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateIndicatorMapper;
import com.wzmtr.dom.service.common.ThirdService;
import com.wzmtr.dom.service.operate.OperateEventService;
import com.wzmtr.dom.service.operate.OperateIndicatorService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

/**
 * 运营日报-初期运营指标
 * @author zhangxin
 * @version 1.0
 * @date 2024/4/2 13:43
 */
@Service
public class OperateIndicatorServiceImpl implements OperateIndicatorService {

    @Autowired
    private OperateEventService operateEventService;
    @Autowired
    private ThirdService thirdService;
    @Autowired
    private OperateIndicatorMapper operateIndicatorMapper;

    @Override
    public Page<IndicatorRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<IndicatorRecordResDTO> list = operateIndicatorMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
        List<IndicatorRecordResDTO> records = list.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new Page<>();
        }
        if (!DataType.DAY.getCode().equals(dataType)) {
            // 周报月报多返回一个正点率和总能耗
            records.forEach(a -> {
                IndicatorInfoResDTO infoRes = operateIndicatorMapper.queryInfoByDate(DateUtil.formatDate(a.getStartDate()), DateUtil.formatDate(a.getEndDate()), dataType, "2");
                IndicatorPowerResDTO indicatorPowerResDTO = operateIndicatorMapper.queryPowerByDate(DateUtil.formatDate(a.getStartDate()), DateUtil.formatDate(a.getEndDate()), dataType);
                if (null != indicatorPowerResDTO) {
                    a.setEnergyConsumption(indicatorPowerResDTO.getEnergyConsumption());
                }
                if (null != infoRes) {
                    a.setPunctualityRate(infoRes.getPunctualityRate());
                }
            });
        }
        return list;
    }

    @Override
    public IndicatorDetailResDTO detail(String id, String dataType, String startDate, String endDate) {
        IndicatorDetailResDTO detail = operateIndicatorMapper.queryInfoById(id, dataType, startDate, endDate);

        if (StringUtils.isNotNull(detail)) {
            buildIndicatorDetail(detail);
            IndicatorPowerResDTO indicatorPower = operateIndicatorMapper.queryPower(id, dataType, startDate, endDate);
            detail.setIndicatorPower(indicatorPower);
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        int existFlag = operateIndicatorMapper.checkExist(indicatorRecordReqDTO.getDataType(),
                indicatorRecordReqDTO.getStartDate(), indicatorRecordReqDTO.getEndDate());
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        // 日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(indicatorRecordReqDTO.getDataType())) {
            if (!indicatorRecordReqDTO.getStartDate().equals(indicatorRecordReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            indicatorRecordReqDTO.setDataDate(indicatorRecordReqDTO.getStartDate());
        }
        indicatorRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        indicatorRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        indicatorRecordReqDTO.setId(TokenUtils.getUuId());
        try {
            operateIndicatorMapper.add(indicatorRecordReqDTO);

            IndicatorPowerReqDTO powerReqDTO = buildIndicatorPower(currentLoginUser, indicatorRecordReqDTO);
            operateIndicatorMapper.addPower(powerReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        indicatorRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modify(indicatorRecordReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void modifyInfo(CurrentLoginUser currentLoginUser, IndicatorInfoReqDTO indicatorInfoReqDTO) {
        //TODO 注释掉 废弃
/*        indicatorInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modifyInfo(indicatorInfoReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }*/
    }

    @Override
    public void modifyPower(CurrentLoginUser currentLoginUser, IndicatorPowerReqDTO indicatorPowerReqDTO) {
        indicatorPowerReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modifyPower(indicatorPowerReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncData(String dataType, String startDate, String endDate) {

        try{
            //日报数据 同步
            if(CommonConstants.DATA_TYPE_DAILY.equals(dataType)){

                IndicatorRecordReqDTO indicatorRecordReqDTO = new IndicatorRecordReqDTO();
                indicatorRecordReqDTO.setDataType(dataType);
                indicatorRecordReqDTO.setStartDate(startDate);
                indicatorRecordReqDTO.setEndDate(endDate);

                //获取运营事件统计
                IndicatorDetailResDTO detail = operateIndicatorMapper.queryInfoById(null, dataType, startDate, endDate);
                if(Objects.nonNull(detail)){
                    NewEventCountResDTO newEventCountResDTO = operateEventService.eventCountByType(startDate,endDate);

                    //停运列次
                    indicatorRecordReqDTO.setStopCount(newEventCountResDTO.getStopCount());

                    //计划兑现列次 计划开行列次-停运列次
                    indicatorRecordReqDTO.setPlanPromiseCount(detail.getPlanRunCount() - newEventCountResDTO.getStopCount());

                    //加开列次
                    indicatorRecordReqDTO.setAddCount(newEventCountResDTO.getAddCount());

                    //实际开行列次 计划兑现列次+加开列次
                    indicatorRecordReqDTO.setRealRunCount(detail.getPlanRunCount() - newEventCountResDTO.getStopCount() + newEventCountResDTO.getAddCount());

                    //清客列次
                    indicatorRecordReqDTO.setRutineGuestCount(newEventCountResDTO.getRutineGuestCount());

                    //救援列次
                    indicatorRecordReqDTO.setRescueCount(newEventCountResDTO.getRescueCount());

                    //掉线次数
                    indicatorRecordReqDTO.setOffLineCount(newEventCountResDTO.getOffLineCount());

                    //晚点5分钟以上
                    indicatorRecordReqDTO.setDelayCount(newEventCountResDTO.getDelay2Count());

                    //晚点5分钟以内 3-5分钟
                    indicatorRecordReqDTO.setDelay2Count(newEventCountResDTO.getDelay1Count());

                    //延误 15分钟以内 5-15分钟延误
                    indicatorRecordReqDTO.setDelay3Count(newEventCountResDTO.getDelay1VehicleCount());

                    //延误 15分钟以上
                    indicatorRecordReqDTO.setDelay4Count(newEventCountResDTO.getDelay2VehicleCount());

                    //行调数据接口
                    OpenDepotStatisticsRes openDepotStatisticsRes = thirdService.getOdmDepotStatistics(startDate);
                    indicatorRecordReqDTO.setRunCode(openDepotStatisticsRes.getSchedule());
                    indicatorRecordReqDTO.setSendTimeInterval(openDepotStatisticsRes.getDepartureInterval());
                    indicatorRecordReqDTO.setPlanRunCount(openDepotStatisticsRes.getScheduleCount());

                    //运营车公里（万列公里）
                    OpenTrainMileRes openTrainMileRes =  thirdService.getEamTrainMile(startDate);
                    indicatorRecordReqDTO.setOperate1Kilometer(Double.valueOf(openTrainMileRes.getSumDailyWorkMile()));
                    //运营车公里（万车公里）：运营车公里（万列公里）*4
                    indicatorRecordReqDTO.setOperate2Kilometer(Double.valueOf(openTrainMileRes.getSumDailyWorkMile()) * CommonConstants.FOUR);

                    //走行车公里（万列公里）EAM系统-设备维护，走行公里数维护，当日所有车组的增加公里数之和/10000
                    if(openTrainMileRes.getSumDailyMile() > 0){
                        DecimalFormat df = new DecimalFormat(CommonConstants.DECIMAL_FMT_STRING);
                        indicatorRecordReqDTO.setRun1Kilometer(Double.parseDouble(df.format(openTrainMileRes.getSumDailyMile()/CommonConstants.TEN_THOUSAND_DOUBLE)));

                    }else{
                        indicatorRecordReqDTO.setRun1Kilometer(CommonConstants.ZERO_DOUBLE);
                    }
                    //走行车公里（万车公里） 走行车公里（万列公里）*4
                    indicatorRecordReqDTO.setRun2Kilometer(indicatorRecordReqDTO.getRun1Kilometer() * 4);

                    operateIndicatorMapper.autoModify(indicatorRecordReqDTO);
                }

            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

    }

    private void buildIndicatorDetail(IndicatorDetailResDTO detail){

        DecimalFormat decimalFormat = new DecimalFormat(CommonConstants.DECIMAL_FMT_STRING);
        // 正点率 (实际开行列此-晚点次数) / 实际开行列次
        if(detail.getRealRunCount() > 0){
            detail.setPunctualityRate(decimalFormat.format(((detail.getRealRunCount() - detail.getDelayCount()) / detail.getRealRunCount()) * CommonConstants.ONE_HUNDRED_LONG) + CommonConstants.PERCENTAGE_STRING);
        }else{
            detail.setPunctualityRate(CommonConstants.ZERO_PERCENTAGE_STRING);
        }
        // 兑现率 (计划兑现列次) / 计划开行列次
        if(detail.getPlanRunCount() > 0){
            detail.setFulfillmentRate(decimalFormat.format(( detail.getPlanPromiseCount() / detail.getPlanRunCount()) * CommonConstants.ONE_HUNDRED_LONG) + CommonConstants.PERCENTAGE_STRING);
        }else{
            detail.setFulfillmentRate(CommonConstants.ZERO_PERCENTAGE_STRING);
        }
        // 列出服务可靠度 运营车公里数(万列公里) / 延误次数, 延误次数 15分钟以内延误次数+15分钟以上延误次数
        if((detail.getDelay3Count() + detail.getDelay4Count()) > 0){
            detail.setServiceReliability(decimalFormat.format( detail.getOperate1Kilometer() / (detail.getDelay3Count() + detail.getDelay4Count())));
        }else{
            detail.setServiceReliability(CommonConstants.INFINITY_STRING);
        }

        // 列出退出正线运营故障率  退出正线数 / 运营车公里(万列公里)
        // 车辆系统故障率  车辆系统故障数 / 运营车公里(万列公里)
        // 信号系统故障率  信号系统故障数 / 运营车公里(万列公里)
        // 供电系统故障率  供电系统故障数 / 运营车公里(万列公里)
        if(detail.getOperate1Kilometer() > 0){
            detail.setEvent1Rate(decimalFormat.format( detail.getOperateEvent1() / detail.getOperate1Kilometer()));
            detail.setEvent2Rate(decimalFormat.format( detail.getOperateEvent2() / detail.getOperate1Kilometer()));
            detail.setEvent3Rate(decimalFormat.format( detail.getOperateEvent3() / detail.getOperate1Kilometer()));
            detail.setEvent4Rate(decimalFormat.format( detail.getOperateEvent4() / detail.getOperate1Kilometer()));
        }else{
            detail.setEvent1Rate(CommonConstants.ZERO_STRING);
            detail.setEvent2Rate(CommonConstants.ZERO_STRING);
            detail.setEvent3Rate(CommonConstants.ZERO_STRING);
            detail.setEvent4Rate(CommonConstants.ZERO_STRING);
        }

    }
    @NotNull
    private static IndicatorInfoReqDTO buildIndicatorInfo(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO, String sType) {
        IndicatorInfoReqDTO infoReqDTO = new IndicatorInfoReqDTO();
        infoReqDTO.setId(TokenUtils.getUuId());
        infoReqDTO.setRecordId(indicatorRecordReqDTO.getId());
        infoReqDTO.setIndicatorType(sType);
        infoReqDTO.setDataType(indicatorRecordReqDTO.getDataType());
        infoReqDTO.setDataDate(indicatorRecordReqDTO.getDataDate());
        infoReqDTO.setStartDate(indicatorRecordReqDTO.getStartDate());
        infoReqDTO.setEndDate(indicatorRecordReqDTO.getEndDate());
        infoReqDTO.setCreateBy(currentLoginUser.getPersonId());
        infoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        return infoReqDTO;
    }

    @NotNull
    private static IndicatorPowerReqDTO buildIndicatorPower(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        IndicatorPowerReqDTO powerReqDTO = new IndicatorPowerReqDTO();
        powerReqDTO.setId(TokenUtils.getUuId());
        powerReqDTO.setRecordId(indicatorRecordReqDTO.getId());
        powerReqDTO.setDataType(indicatorRecordReqDTO.getDataType());
        powerReqDTO.setDataDate(indicatorRecordReqDTO.getDataDate());
        powerReqDTO.setStartDate(indicatorRecordReqDTO.getStartDate());
        powerReqDTO.setEndDate(indicatorRecordReqDTO.getEndDate());
        powerReqDTO.setCreateBy(currentLoginUser.getPersonId());
        powerReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        return powerReqDTO;
    }
}
