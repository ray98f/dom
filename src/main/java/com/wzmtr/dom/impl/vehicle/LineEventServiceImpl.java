package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
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
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
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

        switch (lineEventRecordReqDTO.getDataType()){
            case CommonConstants.DATA_TYPE_DAILY:
                if(!lineEventRecordReqDTO.getStartDate().equals(lineEventRecordReqDTO.getEndDate())){
                    throw new CommonException(ErrorCode.DATE_ERROR);
                }
                lineEventRecordReqDTO.setDataDate(lineEventRecordReqDTO.getStartDate());
                break;
            case CommonConstants.DATA_TYPE_WEEKLY:
                // TODO 统计本周数据
                break;
            case CommonConstants.DATA_TYPE_MONTHLY:
                // TODO 统计本月数据
                break;
            default:
                break;
        }
        lineEventRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        lineEventRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        lineEventRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            lineEventMapper.add(lineEventRecordReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public Page<LineEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
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
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, LineEventInfoReqDTO lineEventInfoReqDTO) {
        lineEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = lineEventMapper.modifyEvent(lineEventInfoReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            lineEventMapper.deleteEvent(ids, TokenUtils.getCurrentPersonId());
        }
    }
}
