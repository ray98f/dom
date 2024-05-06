package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.LineEventMapper;
import com.wzmtr.dom.service.vehicle.LineEventService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆部-正线/车场事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 20:02
 */
@Service
public class LineEventServiceImpl implements LineEventService {

    @Autowired
    private LineEventMapper lineEventMapper;

    @Override
    public Page<LineEventResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return lineEventMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public LineEventDetailResDTO detail(String id) {
        return lineEventMapper.queryInfoById(id);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, LineEventRecordReqDTO lineEventRecordReqDTO) {
        int existFlag = lineEventMapper.checkExist(lineEventRecordReqDTO.getDataType(),
                lineEventRecordReqDTO.getStartDate(),lineEventRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        // 日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(lineEventRecordReqDTO.getDataType())){
            if(!lineEventRecordReqDTO.getStartDate().equals(lineEventRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            lineEventRecordReqDTO.setDataDate(lineEventRecordReqDTO.getStartDate());
        }

        lineEventRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        lineEventRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        lineEventRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            lineEventMapper.add(lineEventRecordReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新周、月统计数
        if(CommonConstants.DATA_TYPE_MONTHLY.equals(lineEventRecordReqDTO.getDataType())
                || CommonConstants.DATA_TYPE_WEEKLY.equals(lineEventRecordReqDTO.getDataType())){
            lineEventMapper.modifyCount(lineEventRecordReqDTO.getId(),lineEventRecordReqDTO.getStartDate(),lineEventRecordReqDTO.getEndDate());
        }
    }

    @Override
    public Page<LineEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return lineEventMapper.eventList(pageReqDTO.of(),startDate,endDate);
    }

    @Override
    public void createEvent(CurrentLoginUser currentLoginUser, LineEventInfoReqDTO lineEventInfoReqDTO) {
        try{
            if(CommonConstants.DATA_TYPE_DAILY.equals(lineEventInfoReqDTO.getDataType())){
                lineEventInfoReqDTO.setDataDate(lineEventInfoReqDTO.getStartDate());
            }
            lineEventInfoReqDTO.setId(TokenUtils.getUuId());
            lineEventInfoReqDTO.setCreateBy(currentLoginUser.getPersonId());
            lineEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            lineEventMapper.createEvent(lineEventInfoReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新事件统计
        updateSummaryCount(lineEventInfoReqDTO.getStartDate(),lineEventInfoReqDTO.getEndDate());
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, LineEventInfoReqDTO lineEventInfoReqDTO) {
        lineEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = lineEventMapper.modifyEvent(lineEventInfoReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //更新事件统计
        List<String> ids = new ArrayList<>();
        ids.add(lineEventInfoReqDTO.getId());
        LineEventInfoResDTO eventInfo = lineEventMapper.queryDateRange(ids);
        updateSummaryCount(DateUtil.formatDate(eventInfo.getStartDate()),DateUtil.formatDate(eventInfo.getEndDate()));

    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            LineEventInfoResDTO eventInfo = lineEventMapper.queryDateRange(ids);
            try{
                lineEventMapper.deleteEvent(ids, TokenUtils.getCurrentPersonId());
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
        List<LineEventResDTO> res =  lineEventMapper.listAll(startDate,endDate);
        if(StringUtils.isNotEmpty(res)){
            for(LineEventResDTO item:res){
                lineEventMapper.modifyCount(item.getId(),DateUtil.formatDate(item.getStartDate()),DateUtil.formatDate(item.getEndDate()));
            }
        }
    }
}
