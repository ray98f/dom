package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.CrewEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.CrewEventSummaryReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewEventSummaryResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.CrewEventMapper;
import com.wzmtr.dom.service.vehicle.CrewEventService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆部-行车事件总结
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/14 8:52
 */
@Service
@Slf4j
public class CrewEventServiceImpl implements CrewEventService {

    @Autowired
    private CrewEventMapper crewEventMapper;

    @Override
    public Page<CrewEventSummaryResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewEventMapper.list(pageReqDTO.of(),dataType,startDate,endDate);
    }

    @Override
    public CrewEventSummaryResDTO detail(String id) {
        return crewEventMapper.queryInfoById(id);
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, CrewEventSummaryReqDTO crewEventSummaryReqDTO) {

        //检测查重
        int existFlag = crewEventMapper.checkExist(crewEventSummaryReqDTO.getDataType(),
                crewEventSummaryReqDTO.getStartDate(),crewEventSummaryReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        crewEventSummaryReqDTO.setCreateBy(currentLoginUser.getPersonId());
        crewEventSummaryReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        crewEventSummaryReqDTO.setId(TokenUtils.getUuId());
        try{
            crewEventMapper.add(crewEventSummaryReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新月统计数 TODO
        if(CommonConstants.DATA_TYPE_MONTHLY.equals(crewEventSummaryReqDTO.getDataType())){
            crewEventMapper.modifyCount(crewEventSummaryReqDTO.getId(),
                    crewEventSummaryReqDTO.getStartDate(),crewEventSummaryReqDTO.getEndDate());
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, CrewEventSummaryReqDTO crewEventSummaryReqDTO) {
        crewEventSummaryReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = crewEventMapper.modify(crewEventSummaryReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public Page<CrewEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewEventMapper.eventList(pageReqDTO.of(),startDate,endDate);
    }

    @Override
    public void createEvent(CurrentLoginUser currentLoginUser, CrewEventInfoReqDTO crewEventInfoReqDTO) {
        try{
            crewEventInfoReqDTO.setId(TokenUtils.getUuId());
            crewEventInfoReqDTO.setCreateBy(currentLoginUser.getPersonId());
            crewEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            crewEventMapper.createEvent(crewEventInfoReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新事件统计
        updateSummaryCount(crewEventInfoReqDTO.getStartDate(),crewEventInfoReqDTO.getEndDate());
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, CrewEventInfoReqDTO crewEventInfoReqDTO) {
        crewEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = crewEventMapper.modifyEvent(crewEventInfoReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //更新事件统计
        List<String> ids = new ArrayList<>();
        ids.add(crewEventInfoReqDTO.getId());
        CrewEventInfoResDTO eventInfo = crewEventMapper.queryDateRange(ids);
        updateSummaryCount(DateUtil.formatDate(eventInfo.getStartDate()),DateUtil.formatDate(eventInfo.getEndDate()));
    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            CrewEventInfoResDTO eventInfo = crewEventMapper.queryDateRange(ids);

            try{
                crewEventMapper.deleteEvent(ids);
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
         List<CrewEventSummaryResDTO> res =  crewEventMapper.listAll(startDate,endDate);
         if(StringUtils.isNotEmpty(res)){
             for(CrewEventSummaryResDTO item:res){
                 crewEventMapper.modifyCount(item.getId(),DateUtil.formatDate(item.getStartDate()),DateUtil.formatDate(item.getEndDate()));
             }
         }
    }
}
