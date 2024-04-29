package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.DrivingCountReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingDepotReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DrivingMapper;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
import com.wzmtr.dom.service.vehicle.DrivingService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Page<DrivingRecordResDTO> list(String dataType,String startDate,String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return drivingMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public DrivingRecordDetailResDTO detail(String recordId) {
        // 获取详情
        DrivingRecordDetailResDTO detail = drivingMapper.queryInfoById(recordId);
        if (StringUtils.isNotNull(detail)) {
            // 车场情况
            detail.setDepotList(drivingMapper.depot(recordId));
            // 司机驾驶情况
            detail.setDriveInfo(drivingMapper.driveInfo(recordId));
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
                        updateDepotCount(reqDTO.getId(),reqDTO.getDepotCode(),reqDTO.getStartDate(),reqDTO.getEndDate());
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
                    updateDriverCount(infoReqDTO.getId(),infoReqDTO.getStartDate(),infoReqDTO.getEndDate());
                }
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //周报、月报 更新记录统计数据
        if(CommonConstants.DATA_TYPE_WEEKLY.equals(drivingRecordReqDTO.getDataType())
                || CommonConstants.DATA_TYPE_MONTHLY.equals(drivingRecordReqDTO.getDataType())){
            updateRecordCount(drivingRecordReqDTO.getId());
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
    public void syncData(CurrentLoginUser currentLoginUser,String recordId) {
        DrivingRecordDetailResDTO  detail = drivingMapper.queryInfoById(recordId);

        //TODO 行车调度 乘务系统 数据接口
        SyncOdmDepotResDTO syncOdmDepotResDTO = syncOdmDepot(recordId);
        // modify depot data
        //modify driver info data

        //更新记录中的统计数据 更新总里程  TODO async
        //前一日的recordId
        String preRecordId = DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parseDate(recordId), -1));
        //获取前一日的总里程和累计人均公里数
        DrivingCountReqDTO countReqDTO = new DrivingCountReqDTO();
        countReqDTO.setId(recordId);
        countReqDTO.setTrainCount1(0);
        countReqDTO.setTrainCount2(0);
        //TODO


        //更新重要指标统计
        indicatorMapper.modifyDayCount(DateUtil.formatDate(detail.getStartDate()),DateUtil.formatDate(detail.getEndDate()));
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
            updateRecordCount(drivingDepotReqDTO.getRecordId());
        }
    }

    @Override
    public void driverModify(CurrentLoginUser currentLoginUser, DrivingInfoReqDTO drivingInfoReqDTO) {
        drivingInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());

        //总里程计算
        Double mileageTotal = 0.0;
        if(Objects.nonNull(drivingInfoReqDTO.getMileage())){

            //获取前一天数据
            DrivingInfoResDTO preInfo = drivingMapper.driveInfo(DateUtil.formatDate(
                    DateUtil.offsetDay(DateUtil.parseDate(drivingInfoReqDTO.getDataDate()), -1)));
            if(Objects.nonNull(preInfo)){
                mileageTotal = preInfo.getMileageTotal() + drivingInfoReqDTO.getMileage();
                drivingInfoReqDTO.setMileageTotal(mileageTotal);
            }
        }
        try{
            int res = drivingMapper.modifyInfoData(drivingInfoReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }else{

                //更新记录中的统计数据 更新总里程  TODO async
                DrivingCountReqDTO countReqDTO = new DrivingCountReqDTO();
                countReqDTO.setId(drivingInfoReqDTO.getRecordId());
                if(Objects.nonNull(drivingInfoReqDTO.getMileage())){
                    countReqDTO.setMileageTotal(mileageTotal);
                }
                drivingMapper.modifyDayCount(countReqDTO);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //更新统计
        if(StringUtils.isNotEmpty(drivingInfoReqDTO.getRecordId())){
            updateRecordCount(drivingInfoReqDTO.getRecordId());
        }

    }

    // TODO TEST
    private SyncOdmDepotResDTO syncOdmDepot(String recordId){
        /*String startDate = recordId + CommonConstants.DEFAULT_TIME;
        String endDate = DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parseDate(recordId), 1))
                + CommonConstants.DEFAULT_TIME;*/
        SyncOdmDepotResDTO odmDepotResDTO = new SyncOdmDepotResDTO();
        odmDepotResDTO.setPlanDeparture(9);
        odmDepotResDTO.setPlanReceive(9);
        odmDepotResDTO.setRealDeparture(9);
        odmDepotResDTO.setRealReceive(9);
        return odmDepotResDTO;
    }

    /**
     * 周报、日报更新车场数据
     */
    private void updateDepotCount(String id,String depotCode,String startDate,String endDate){
        drivingMapper.modifyDepotCount(id,depotCode,startDate,endDate);
    }

    /**
     * 周报、日报更新司机数据
     */
    private void updateDriverCount(String id,String startDate,String endDate){
        drivingMapper.modifyInfoCount(id,startDate,endDate);
    }

    /**
     * 更新行车事件统计
     */
    private void updateRecordCount(String id){
        drivingMapper.modifyRecordCount(id);
    }
}
