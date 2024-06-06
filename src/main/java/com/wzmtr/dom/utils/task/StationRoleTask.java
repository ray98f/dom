package com.wzmtr.dom.utils.task;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.system.StationRoleReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.mapper.system.StationRoleMapper;
import com.wzmtr.dom.service.common.MdmSyncService;
import com.wzmtr.dom.service.system.StationRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 定时任务-每月更新审核站
 * @author  zhangxin
 * @version 1.0
 * @date 2024/06/05
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StationRoleTask {

    @Autowired
    private StationRoleService stationRoleService;

    @Autowired
    private StationRoleMapper stationRoleMapper;

    @Scheduled(cron = "0 50 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void stationRoleModify() {

        String today = DateUtil.today();
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(today));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(today));

        //判断今天是否为本月最后一天
        if(today.equals(DateUtil.formatDate(monthEnd))){

            //下个月起始
            Date nextMonthStart = DateUtil.tomorrow();
            Date nextMonthEnd = DateUtil.endOfMonth(DateUtil.tomorrow());
            StationRoleResDTO nowRes = stationRoleMapper.queryByDate(DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));

            if(Objects.nonNull(nowRes)){
                int len = CommonConstants.S2_STATION_ARRAY.length;
                int next=0;
                for(int i =0 ;i<len;i++){
                    if(nowRes.getStationCode().equals( CommonConstants.S2_STATION_ARRAY[i])){
                        next = i+1;
                        if(next >=len){
                            next = 0;
                        }
                    }
                }
                //删除当前审核站
                stationRoleMapper.delete(Collections.singletonList(nowRes.getId()),CommonConstants.ADMIN);
                //新增下一个月审核站
                StationRoleReqDTO stationRoleReqDTO = StationRoleReqDTO.builder()
                        .stationCode(CommonConstants.S2_STATION_ARRAY[next])
                        .stationName(CommonConstants.S2_STATION_NAME[next])
                        .startDate(nextMonthStart).endDate(nextMonthEnd).build();
                stationRoleReqDTO.setCreateBy(CommonConstants.ADMIN);
                stationRoleMapper.add(stationRoleReqDTO);
            }else{
                //不存在审核站，从第一个站开始
                StationRoleReqDTO stationRoleReqDTO = StationRoleReqDTO.builder().stationCode(CommonConstants.S2_STATION_ARRAY[0]).stationName(CommonConstants.S2_STATION_NAME[0]).startDate(nextMonthStart).endDate(nextMonthEnd).build();
                stationRoleReqDTO.setCreateBy(CommonConstants.ADMIN);
                stationRoleMapper.add(stationRoleReqDTO);
            }
        }
    }

}
