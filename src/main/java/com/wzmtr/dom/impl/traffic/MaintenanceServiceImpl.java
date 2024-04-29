package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.MaintenanceInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.MaintenanceRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.MaintenanceMapper;
import com.wzmtr.dom.service.traffic.MaintenanceService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 15:28
 */
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    public Page<MaintenanceRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return maintenanceMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public MaintenanceRecordResDTO detail(String recordId, String startDate, String endDate) {
        return maintenanceMapper.queryInfoById(recordId,startDate,endDate);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, MaintenanceRecordReqDTO maintenanceRecordReqDTO) {
        int existFlag = maintenanceMapper.checkExist(maintenanceRecordReqDTO.getDataType(),
                maintenanceRecordReqDTO.getStartDate(),maintenanceRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        // 日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(maintenanceRecordReqDTO.getDataType())){
            if(!maintenanceRecordReqDTO.getStartDate().equals(maintenanceRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            maintenanceRecordReqDTO.setDataDate(maintenanceRecordReqDTO.getStartDate());
        }

        maintenanceRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        maintenanceRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        maintenanceRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            maintenanceMapper.add(maintenanceRecordReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public Page<MaintenanceInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return maintenanceMapper.eventList(pageReqDTO.of(),startDate,endDate);
    }

    @Override
    public void createEvent(CurrentLoginUser currentLoginUser, MaintenanceInfoReqDTO maintenanceInfoReqDTO) {
        try{
            if(CommonConstants.DATA_TYPE_DAILY.equals(maintenanceInfoReqDTO.getDataType())){
                maintenanceInfoReqDTO.setDataDate(maintenanceInfoReqDTO.getStartDate());
            }
            maintenanceInfoReqDTO.setId(TokenUtils.getUuId());
            maintenanceInfoReqDTO.setCreateBy(currentLoginUser.getPersonId());
            maintenanceInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            maintenanceMapper.createEvent(maintenanceInfoReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新事件统计
        updateSummaryCount(maintenanceInfoReqDTO.getStartDate(),maintenanceInfoReqDTO.getEndDate());
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, MaintenanceInfoReqDTO maintenanceInfoReqDTO) {
        maintenanceInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = maintenanceMapper.modifyEvent(maintenanceInfoReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            MaintenanceInfoResDTO eventInfo = maintenanceMapper.queryDateRange(ids);
            try{
                maintenanceMapper.deleteEvent(ids, TokenUtils.getCurrentPersonId());
            }catch (Exception e){
                throw new CommonException(ErrorCode.DELETE_ERROR);
            }

            //更新事件统计
            updateSummaryCount(DateUtil.formatDate(eventInfo.getStartDate()),DateUtil.formatDate(eventInfo.getEndDate()));
        }
    }

    /**
     * 事件统计更新
     * */
    private void updateSummaryCount(String startDate,String endDate){
        List<MaintenanceRecordResDTO> res =  maintenanceMapper.listAll(startDate,endDate);
        if(StringUtils.isNotEmpty(res)){
            for(MaintenanceRecordResDTO item:res){
                maintenanceMapper.modifyCount(item.getId(),DateUtil.formatDate(item.getStartDate()),DateUtil.formatDate(item.getEndDate()));
            }
        }
    }
}
