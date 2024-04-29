package com.wzmtr.dom.impl.operate;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.OperateEventInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.res.operate.EventCountResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateEventMapper;
import com.wzmtr.dom.service.operate.OperateEventService;
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
 * @date 2024/3/25 17:02
 */
@Service
public class OperateEventServiceImpl implements OperateEventService {

    @Autowired
    private OperateEventMapper operateEventMapper;

    @Override
    public Page<OperateEventResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateEventMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public OperateEventResDTO detail(String id,String startDate, String endDate) {
        return operateEventMapper.queryInfoById(id,startDate,endDate);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, OperateEventReqDTO operateEventReqDTO) {
        int existFlag = operateEventMapper.checkExist(operateEventReqDTO.getDataType(),
                operateEventReqDTO.getStartDate(),operateEventReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        // 日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(operateEventReqDTO.getDataType())){
            if(!operateEventReqDTO.getStartDate().equals(operateEventReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            operateEventReqDTO.setDataDate(operateEventReqDTO.getStartDate());
        }

        operateEventReqDTO.setCreateBy(currentLoginUser.getPersonId());
        operateEventReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        operateEventReqDTO.setId(TokenUtils.getUuId());
        try{
            operateEventMapper.add(operateEventReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新周、月统计数
        if(CommonConstants.DATA_TYPE_MONTHLY.equals(operateEventReqDTO.getDataType())
                || CommonConstants.DATA_TYPE_WEEKLY.equals(operateEventReqDTO.getDataType())){
            operateEventMapper.modifyCount(operateEventReqDTO.getId(),operateEventReqDTO.getStartDate(),operateEventReqDTO.getEndDate());
        }
    }

    @Override
    public Page<OperateEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateEventMapper.eventList(pageReqDTO.of(),startDate,endDate);
    }

    @Override
    public List<EventCountResDTO> eventCount(String startDate, String endDate) {
        return operateEventMapper.eventCount(startDate,endDate);
    }

    @Override
    public void createEvent(CurrentLoginUser currentLoginUser, OperateEventInfoReqDTO operateEventInfoReqDTO) {
        try{
            if(CommonConstants.DATA_TYPE_DAILY.equals(operateEventInfoReqDTO.getDataType())){
                operateEventInfoReqDTO.setDataDate(operateEventInfoReqDTO.getStartDate());
            }
            operateEventInfoReqDTO.setId(TokenUtils.getUuId());
            operateEventInfoReqDTO.setCreateBy(currentLoginUser.getPersonId());
            operateEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            operateEventMapper.createEvent(operateEventInfoReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新事件统计
        updateSummaryCount(operateEventInfoReqDTO.getStartDate(),operateEventInfoReqDTO.getEndDate());
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, OperateEventInfoReqDTO operateEventInfoReqDTO) {
        operateEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateEventMapper.modifyEvent(operateEventInfoReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            OperateEventInfoResDTO eventInfo = operateEventMapper.queryDateRange(ids);
            try{
                operateEventMapper.deleteEvent(ids, TokenUtils.getCurrentPersonId());
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
        List<OperateEventResDTO> res =  operateEventMapper.listAll(startDate,endDate);
        if(StringUtils.isNotEmpty(res)){
            for(OperateEventResDTO item:res){
                operateEventMapper.modifyCount(item.getId(), DateUtil.formatDate(item.getStartDate()),DateUtil.formatDate(item.getEndDate()));
            }
        }
    }
}
