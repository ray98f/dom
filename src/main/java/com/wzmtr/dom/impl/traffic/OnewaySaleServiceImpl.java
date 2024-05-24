package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OneWaySaleDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.OnewaySaleMapper;
import com.wzmtr.dom.service.traffic.OnewaySaleService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class OnewaySaleServiceImpl implements OnewaySaleService {
    @Autowired
    private OnewaySaleMapper onewaySaleMapper;

    @Override
    public OnewaySaleDetailResDTO detail(OneWaySaleDetailReqDTO reqDTO) {
        TrafficOnewaySaleDO trafficOnewaySale = onewaySaleMapper.detail(reqDTO);
        if (StringUtils.isNotNull(trafficOnewaySale)) {
            OnewaySaleDetailResDTO detail = OnewaySaleDetailResDTO.buildRes(trafficOnewaySale);
            compare(detail);
            return detail;
        }
        return new OnewaySaleDetailResDTO();
    }

    @Override
    public void add(OnewaySaleAddReqDTO reqDTO) {
        TrafficOnewaySaleDO onewaySaleDO = reqDTO.toDO(reqDTO);
        Integer result = onewaySaleMapper.checkExist(onewaySaleDO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期单程票发售情况数据已存在，无法重复新增");
        }
        onewaySaleDO.setId(TokenUtils.getUuId());
        onewaySaleDO.setCreateBy(TokenUtils.getCurrentPersonId());
        onewaySaleMapper.insert(onewaySaleDO);

        // 统计周/月报数据
        if(!CommonConstants.DATA_TYPE_DAILY.equals(reqDTO.getDataType())){
            autoModifyByDaily(reqDTO.getDataType(),DateUtil.formatDate(reqDTO.getStartDate()),DateUtil.formatDate(reqDTO.getEndDate()));
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, OnewaySaleAddReqDTO passengerRecordReqDTO) {
        TrafficOnewaySaleDO onewaySaleDO = passengerRecordReqDTO.toDO(passengerRecordReqDTO);
        onewaySaleMapper.update(onewaySaleDO, new UpdateWrapper<TrafficOnewaySaleDO>().eq("id", onewaySaleDO.getId()));

        // 统计周/月报数据
        if(CommonConstants.DATA_TYPE_DAILY.equals(passengerRecordReqDTO.getDataType())){
            autoModifyByDaily(passengerRecordReqDTO.getDataType(),DateUtil.formatDate(passengerRecordReqDTO.getStartDate()),DateUtil.formatDate(passengerRecordReqDTO.getEndDate()));
        }
    }

    @Override
    public Page<OnewaySaleListResDTO> list(OnewaySaleListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<OnewaySaleListResDTO> list = onewaySaleMapper.list(reqDTO.of(), reqDTO);
        List<OnewaySaleListResDTO> records = list.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        return list;
    }

    @Override
    public OnewaySaleDetailResDTO acc(String dataType, String startDate, String endDate) {
        // todo acc

        //同步后更新日报
        //modify
        //更新周/月报
        if(CommonConstants.DATA_TYPE_DAILY.equals(dataType)){
            autoModifyByDaily(dataType,startDate,endDate);
        }
        return null;
    }

    @Override
    public void autoModify(String dataType, String startDate, String endDate) {
        onewaySaleMapper.autoModify(dataType,startDate,endDate);
    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {
        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));

        onewaySaleMapper.autoModify(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        onewaySaleMapper.autoModify(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }

    /**
     * 获取环比
     * */
    private OnewaySaleDetailResDTO compare(OnewaySaleDetailResDTO detail){

        //获取前一日/周/月数据
        OnewaySaleDetailResDTO preDetail;
        switch (detail.getDataType()){
            case CommonConstants.DATA_TYPE_DAILY:
                Date yesterday = DateUtil.offsetDay(detail.getStartDate(),-1);
                preDetail = onewaySaleMapper.selectDetailById(null,detail.getDataType(),DateUtil.formatDate(yesterday),DateUtil.formatDate(yesterday));
                break;
            case CommonConstants.DATA_TYPE_WEEKLY:
                Date preMonday = DateUtil.offsetDay(detail.getStartDate(),-7);
                Date preSunday = DateUtil.offsetDay(detail.getEndDate(),-7);
                preDetail = onewaySaleMapper.selectDetailById(null,detail.getDataType(),DateUtil.formatDate(preMonday),DateUtil.formatDate(preSunday));
                break;
            default:
                // 获取上个月的第一天
                Date firstDayOfLastMonth = DateUtil.beginOfMonth(DateUtil.offsetMonth(detail.getStartDate(), -1));
                // 获取上个月的最后一天
                Date lastDayOfLastMonth = DateUtil.endOfMonth(DateUtil.offsetMonth(detail.getStartDate(), -1));
                preDetail = onewaySaleMapper.selectDetailById(null,detail.getDataType(),DateUtil.formatDate(firstDayOfLastMonth),DateUtil.formatDate(lastDayOfLastMonth));
                break;
        }

        // 环比计算
        if(Objects.nonNull(preDetail)){
            detail.setCashRatioQoqRatio(getQoq(detail.getCash(),preDetail.getCash()));
            detail.setUnionCardRatioQoqRatio(getQoq(detail.getUnionCard(),preDetail.getUnionCard()));
            detail.setTvmCodeRatioQoqRatio(getQoq(detail.getTvmCode(),preDetail.getTvmCode()));
            detail.setItvmCodeRatioQoqRatio(getQoq(detail.getItvmCode(),preDetail.getItvmCode()));
            detail.setBomCodeRatioQoqRatio(getQoq(detail.getBomCode(),preDetail.getBomCode()));
            detail.setIbomCodeRatioQoqRatio(getQoq(detail.getIbomCode(),preDetail.getIbomCode()));
            detail.setFreeTicketRatioQoqRatio(getQoq(detail.getFreeTicket(),preDetail.getFreeTicket()));
            detail.setOff20TicketRatioQoqRatio(getQoq(detail.getOff20Ticket(),preDetail.getOff20Ticket()));
            detail.setPayExitRatioQoqRatio(getQoq(detail.getPayExit(),preDetail.getPayExit()));
            detail.setFreeExitRatioQoqRatio(getQoq(detail.getFreeExit(),preDetail.getFreeExit()));
            detail.setPreAssignRatioQoqRatio(getQoq(detail.getPreAssign(),preDetail.getPreAssign()));
        }
        return detail;
    }

    private Double getQoq(double source,double pre){
        double res = 0.00;
        if(pre > 0){
            res = NumberUtil.div((source-pre), pre, 4) * 100;
        }
        return res;
    }
}
