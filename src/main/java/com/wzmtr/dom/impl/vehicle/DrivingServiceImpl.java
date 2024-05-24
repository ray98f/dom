package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.DrivingCountReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingDepotReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingRecordReqDTO;
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
            detail.setDriveInfo(drivingMapper.driveInfo(detail.getId()));
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

            //TODO 行车调度 乘务系统 数据接口

            //日报场景
            if(CommonConstants.DATA_TYPE_DAILY.equals(detail.getDataType())){

                //同步每日行车调度数据
                OpenDepotStatisticsRes res = thirdService.getOdmDepotStatistics(DateUtil.formatDate(detail.getStartDate()));

                //更新车场数据
                for(String i: CommonConstants.STATION_280_281){
                    DrivingDepotReqDTO depotReqDTO = new DrivingDepotReqDTO();
                    depotReqDTO.setDataType(detail.getDataType());
                    depotReqDTO.setStartDate(DateUtil.formatDate(detail.getStartDate()));
                    depotReqDTO.setEndDate(DateUtil.formatDate(detail.getEndDate()));
                    depotReqDTO.setDepotCode(i);
                    if(CommonConstants.STATION_280.equals(i)){

                    }else{

                    }
                }

            // 从日报获取统计数据更新
            }else{

            }



            // modify depot data
            //modify driver info data

            //更新记录中的统计数据 更新总里程  TODO async
            // 驾驶总公里数 = 前一日驾驶总公里数+当日所有司机【公里数统计报表】中公里数之和
            // TODO 当日所有司机【公里数统计报表】中公里数 由乘务系统提供接口
            //前一日的recordId
            // String preRecordId = DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parseDate(recordId), -1));
            //获取前一日的总里程和累计人均公里数
            DrivingCountReqDTO countReqDTO = new DrivingCountReqDTO();
            countReqDTO.setId(recordId);
            countReqDTO.setTrainCount1(0);
            countReqDTO.setTrainCount2(0);
            //TODO

            // 更新重要指标统计
            //indicatorService.autoModifyByDaily(detail.getDataType(),DateUtil.formatDate(detail.getStartDate()),DateUtil.formatDate(detail.getEndDate()));
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
