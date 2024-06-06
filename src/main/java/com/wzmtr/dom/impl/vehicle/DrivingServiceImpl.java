package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.DrivingCountReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingDepotReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingRecordReqDTO;
import com.wzmtr.dom.dto.res.OpenDriverInfoRes;
import com.wzmtr.dom.dto.res.common.OpenDepotStatisticsRes;
import com.wzmtr.dom.dto.res.vehicle.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DrivingMapper;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
import com.wzmtr.dom.service.common.ThirdService;
import com.wzmtr.dom.service.vehicle.DrivingService;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 车辆部-行车情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 11:15
 */
@Service
public class DrivingServiceImpl implements DrivingService {

    @Autowired
    private DrivingMapper drivingMapper;

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private ThirdService thirdService;

    @Override
    public Page<DrivingRecordResDTO> list(String dataType,String startDate,String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return drivingMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public DrivingRecordDetailResDTO detail(String recordId, String dataType, String startDate, String endDate) {
        // 获取详情
        DrivingRecordDetailResDTO detail = drivingMapper.queryInfoById(recordId, dataType, startDate, endDate);
        if (StringUtils.isNotNull(detail)) {
            // 车场情况
            detail.setDepotList(drivingMapper.depot(detail.getId()));
            // 司机驾驶情况
            detail.setDriveInfo(drivingMapper.driveInfo(detail.getId(),null,null,null));
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, DrivingRecordReqDTO drivingRecordReqDTO) {
        int existFlag = drivingMapper.checkExist(drivingRecordReqDTO.getDataType(),
                drivingRecordReqDTO.getStartDate(),drivingRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型数据
        if(CommonConstants.DATA_TYPE_DAILY.equals(drivingRecordReqDTO.getDataType())){
            if(!drivingRecordReqDTO.getStartDate().equals(drivingRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            drivingRecordReqDTO.setDataDate(drivingRecordReqDTO.getStartDate());
        }

        drivingRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        drivingRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        drivingRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            int res = drivingMapper.add(drivingRecordReqDTO);
            if(res > 0){
                DrivingInfoReqDTO infoReqDTO= new DrivingInfoReqDTO();
                for(String i: CommonConstants.STATION_280_281){
                    DrivingDepotReqDTO reqDTO = new DrivingDepotReqDTO();
                    reqDTO.setId(TokenUtils.getUuId());
                    reqDTO.setRecordId(drivingRecordReqDTO.getId());
                    reqDTO.setDataType(drivingRecordReqDTO.getDataType());
                    reqDTO.setStartDate(drivingRecordReqDTO.getStartDate());
                    reqDTO.setEndDate(drivingRecordReqDTO.getEndDate());
                    reqDTO.setDepotCode(i);
                    reqDTO.setCreateBy(currentLoginUser.getPersonId());
                    reqDTO.setUpdateBy(currentLoginUser.getPersonId());
                    drivingMapper.createDepotData(reqDTO);

                    //统计本周/本月车场数据
                    if (!CommonConstants.DATA_TYPE_DAILY.equals(drivingRecordReqDTO.getDataType())){
                        updateDepotCount(reqDTO.getDepotCode(),drivingRecordReqDTO.getDataType(),reqDTO.getStartDate(),reqDTO.getEndDate());
                    }
                }

                infoReqDTO.setCreateBy(currentLoginUser.getPersonId());
                infoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
                infoReqDTO.setId(TokenUtils.getUuId());
                infoReqDTO.setRecordId(drivingRecordReqDTO.getId());
                infoReqDTO.setDataType(drivingRecordReqDTO.getDataType());
                infoReqDTO.setStartDate(drivingRecordReqDTO.getStartDate());
                infoReqDTO.setEndDate(drivingRecordReqDTO.getEndDate());
                drivingMapper.createInfoData(infoReqDTO);

                //统计本周/本月司机驾驶数据
                if (!CommonConstants.DATA_TYPE_DAILY.equals(drivingRecordReqDTO.getDataType())){
                    updateDriverCount(drivingRecordReqDTO.getDataType(),infoReqDTO.getStartDate(),infoReqDTO.getEndDate());
                }
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //周报、月报 更新记录统计数据
        if(CommonConstants.DATA_TYPE_WEEKLY.equals(drivingRecordReqDTO.getDataType())
                || CommonConstants.DATA_TYPE_MONTHLY.equals(drivingRecordReqDTO.getDataType())){
            updateRecordCount(drivingRecordReqDTO.getId(),drivingRecordReqDTO.getDataType(),drivingRecordReqDTO.getStartDate(),drivingRecordReqDTO.getEndDate());
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, DrivingRecordReqDTO drivingRecordReqDTO) {
        drivingRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = drivingMapper.modify(drivingRecordReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncData(CurrentLoginUser currentLoginUser,String recordId,String dataType,String startDate,String endDate) {
        try{

            DrivingRecordDetailResDTO  detail = drivingMapper.queryInfoById(recordId, dataType, startDate, endDate);

            //日报场景
            if(Objects.nonNull(detail) && CommonConstants.DATA_TYPE_DAILY.equals(detail.getDataType())){

                //同步每日行车调度数据
                OpenDepotStatisticsRes res = thirdService.getOdmDepotStatistics(DateUtil.formatDate(detail.getStartDate()));
                DrivingCountReqDTO countReqDTO = new DrivingCountReqDTO();
                //更新车场数据
                for(String i: CommonConstants.STATION_280_281){
                    DrivingDepotReqDTO depotReqDTO = new DrivingDepotReqDTO();
                    depotReqDTO.setUpdateBy(currentLoginUser.getPersonId());
                    depotReqDTO.setDataType(detail.getDataType());
                    depotReqDTO.setStartDate(DateUtil.formatDate(detail.getStartDate()));
                    depotReqDTO.setEndDate(DateUtil.formatDate(detail.getEndDate()));
                    depotReqDTO.setDepotCode(i);
                    //下塘计划及实际收发车
                    if(CommonConstants.STATION_280.equals(i)){
                        depotReqDTO.setPlanDeparture(res.getXtPlanDepartureCount());
                        depotReqDTO.setPlanReceive(res.getXtPlanArrivalCount());
                        depotReqDTO.setRealDeparture(res.getXtActualDepartureCount());
                        depotReqDTO.setRealReceive(res.getXtActualArrivalCount());
                        countReqDTO.setTrainCount1(res.getXtActualDepartureCount());
                    //汀田计划及实际收发车
                    }else{
                        depotReqDTO.setPlanDeparture(res.getTtPlanDepartureCount());
                        depotReqDTO.setPlanReceive(res.getTtPlanArrivalCount());
                        depotReqDTO.setRealDeparture(res.getTtActualDepartureCount());
                        depotReqDTO.setRealReceive(res.getTtActualArrivalCount());
                        countReqDTO.setTrainCount1(res.getTtActualDepartureCount());
                    }
                    //更新车场数据
                    drivingMapper.modifyDepotByDate(depotReqDTO);
                }

                //前一天的记录
                Date preDate = DateUtil.offsetDay(DateUtil.parseDate(startDate),-1);
                DrivingInfoResDTO preDriveInfo = drivingMapper.driveInfo(null, dataType,
                        DateUtil.formatDate(preDate), DateUtil.formatDate(preDate));
                Double preMileageTotal = CommonConstants.DEFAULT_MILE;
                Double preAvgMileage = CommonConstants.DEFAULT_MILE;
                if(Objects.nonNull(preDriveInfo)){
                    preMileageTotal = preDriveInfo.getMileageTotal();
                    preAvgMileage = preDriveInfo.getAvgMileage();
                }

                // 同步乘务系统数据
                OpenDriverInfoRes openDriverInfoRes = thirdService.getDriverInfo(startDate);

                //更新记录中的统计数据 更新总里程
                // 驾驶总公里数 = 前一日驾驶总公里数+当日所有司机【公里数统计报表】中公里数之和
                // 累计人均公里数=当日所有司机【公里数统计报表】中公里数之和/司机总人数+前一日的数据
                DrivingInfoReqDTO drivingInfoReqDTO = DrivingInfoReqDTO.builder()
                        .driverCount(openDriverInfoRes.getDriverNumber())
                        .planAttend(openDriverInfoRes.getShouldWorkNumber())
                        .realAttend(openDriverInfoRes.getDidWorkNumber())
                        .mileageTotal(preMileageTotal + Double.parseDouble(openDriverInfoRes.getTotalDistance()))
                        .avgMileage(preAvgMileage +  Double.parseDouble(openDriverInfoRes.getAverageDistance()))
                        .dataType(dataType)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build();
                drivingMapper.modifyDriveInfo(drivingInfoReqDTO);

                //更新记录数据统计
                countReqDTO.setId(detail.getId());
                countReqDTO.setDriverCount(drivingInfoReqDTO.getDriverCount());
                countReqDTO.setMileageTotal(drivingInfoReqDTO.getMileageTotal());
                drivingMapper.modifyDayCount(countReqDTO);

                // 更新重要指标统计
                indicatorService.autoModifyByDaily(detail.getDataType(),DateUtil.formatDate(detail.getStartDate()),DateUtil.formatDate(detail.getEndDate()));

            // 从日报获取统计数据更新
            }else{
                for(String i: CommonConstants.STATION_280_281){
                    updateDepotCount(i,dataType,startDate,endDate);
                }
                updateDriverCount(dataType,startDate,endDate);
                drivingMapper.modifyRecordCount(null,dataType, startDate, endDate);
                indicatorService.autoModifyByDaily(CommonConstants.DATA_TYPE_DAILY, startDate,endDate);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public List<DrivingDepotResDTO> depot(String recordId) {
        return drivingMapper.depot(recordId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void depotModify(CurrentLoginUser currentLoginUser, DrivingDepotReqDTO drivingDepotReqDTO) {
        drivingDepotReqDTO.setUpdateBy(currentLoginUser.getPersonId());

        drivingMapper.modifyDepotData(drivingDepotReqDTO);

        // 更新列表数据
        String depotCode = drivingDepotReqDTO.getDepotCode();
        if (StringUtils.isNotEmpty(depotCode)) {
            DrivingCountReqDTO drivingCountReqDTO = new DrivingCountReqDTO();
            drivingCountReqDTO.setId(drivingDepotReqDTO.getRecordId());
            switch (depotCode) {
                case CommonConstants.STATION_280:
                    drivingCountReqDTO.setTrainCount1(drivingDepotReqDTO.getRealDeparture());
                    break;
                case CommonConstants.STATION_281:
                    drivingCountReqDTO.setTrainCount2(drivingDepotReqDTO.getRealDeparture());
                    break;
                default:
                    break;
            }
            drivingMapper.modifyDayCount(drivingCountReqDTO);
        }

        //更新统计
        if(StringUtils.isNotEmpty(drivingDepotReqDTO.getRecordId())){
            updateRecordCount(drivingDepotReqDTO.getRecordId(),null,null,null);
        }
    }

    @Override
    public void driverModify(CurrentLoginUser currentLoginUser, DrivingInfoReqDTO drivingInfoReqDTO) {

        DrivingInfoResDTO infoData = drivingMapper.queryInfoDataById(drivingInfoReqDTO.getId());
        if(Objects.nonNull(infoData)){
            drivingInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());

            try{
                int res = drivingMapper.modifyInfoData(drivingInfoReqDTO);
                if( res <= 0){
                    throw new CommonException(ErrorCode.UPDATE_ERROR);
                }else{

                    //更新记录中的统计数据 更新总里程
                    DrivingCountReqDTO countReqDTO = new DrivingCountReqDTO();
                    countReqDTO.setId(drivingInfoReqDTO.getRecordId());
                    countReqDTO.setMileageTotal(drivingInfoReqDTO.getMileageTotal());
                    drivingMapper.modifyDayCount(countReqDTO);
                }
            }catch (Exception e){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }

            //更新周/月报统计
            autoModifyByDaily(infoData.getDataType(),DateUtil.formatDate(infoData.getStartDate()),DateUtil.formatDate(infoData.getEndDate()));
        }

    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {
        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));

        updateRecordCount(null,CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        updateRecordCount(null,CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));


    }


    /**
     * 周报、日报更新车场数据
     */
    private void updateDepotCount(String depotCode,String dataType,String startDate,String endDate){
        //数量是本周/月里日报数据总和
        drivingMapper.modifyDepotCount(depotCode,dataType,startDate,endDate);
    }

    /**
     * 周报、日报更新司机数据
     */
    private void updateDriverCount(String dataType,String startDate,String endDate){

        // 司机总人数、驾驶总公里数、累计人均公里数=本周/月最后一天的日报数量
        //其它字段为本周/月 所有日报数量的总和
        drivingMapper.modifyInfoCount(dataType,startDate,endDate);
    }

    /**
     * 更新行车事件统计
     */
    private void updateRecordCount(String id,String dataType,String startDate,String endDate){
        drivingMapper.modifyRecordCount(id,dataType, startDate, endDate);

        //更新重要指标
        DrivingRecordDetailResDTO detail = drivingMapper.queryInfoById(id, dataType, startDate, endDate);
        if(Objects.nonNull(detail)
                && CommonConstants.DATA_TYPE_DAILY.equals(detail.getDataType())){
            indicatorService.autoModifyByDaily(detail.getDataType(),DateUtil.formatDate(detail.getStartDate()),DateUtil.formatDate(detail.getEndDate()));
        }
    }
}
