package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.TicketUseReqDTO;
import com.wzmtr.dom.dto.res.traffic.TicketUseResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.TicketUseMapper;
import com.wzmtr.dom.service.traffic.TicketUseService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 客运部-线网车票过闸使用情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Service
public class TicketUseServiceImpl implements TicketUseService {

    @Autowired
    private TicketUseMapper ticketUseMapper;

    @Override
    public Page<TicketUseResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return ticketUseMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public TicketUseResDTO detail(String id, String startDate, String endDate) {
        TicketUseResDTO res = ticketUseMapper.detail(id, startDate, endDate);
        if (Objects.isNull(res)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        TicketUseResDTO lastRes;
        if (CommonConstants.ONE_STRING.equals(res.getDataType())) {
            lastRes = ticketUseMapper.getLastDetail(res.getDataDate(), null);
        } else {
            lastRes = ticketUseMapper.getLastDetail(null, res.getStartDate());
        }
        buildTicketUseQoq(res, lastRes);
        return res;
    }

    @Override
    public TicketUseResDTO acc(String dataType, String startDate, String endDate) {
        // todo 根据日期获取当天 ACC系统线 网车票过闸使用情况

        //同步后更新日报
        //modify
        //更新周/月报
        if(CommonConstants.DATA_TYPE_DAILY.equals(dataType)){
            autoModifyByDaily(dataType,startDate,endDate);
        }
        return new TicketUseResDTO();
    }

    @Override
    public void add(TicketUseReqDTO ticketUseReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = ticketUseMapper.selectIsExist(ticketUseReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期线网车票过闸使用情况数据已存在，无法重复新增");
        }
        ticketUseReqDTO.setId(TokenUtils.getUuId());
        ticketUseReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        ticketUseMapper.add(ticketUseReqDTO);

        // 统计周/月报数据
        if(!CommonConstants.DATA_TYPE_DAILY.equals(ticketUseReqDTO.getDataType())){
            autoModifyByDaily(ticketUseReqDTO.getDataType(),DateUtil.formatDate(ticketUseReqDTO.getStartDate()),DateUtil.formatDate(ticketUseReqDTO.getEndDate()));
        }
    }

    @Override
    public void modify(TicketUseReqDTO ticketUseReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = ticketUseMapper.selectIsExist(ticketUseReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        ticketUseReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        ticketUseMapper.modify(ticketUseReqDTO);

        // 统计周/月报数据
        if(CommonConstants.DATA_TYPE_DAILY.equals(ticketUseReqDTO.getDataType())){
            autoModifyByDaily(ticketUseReqDTO.getDataType(),DateUtil.formatDate(ticketUseReqDTO.getStartDate()),DateUtil.formatDate(ticketUseReqDTO.getEndDate()));
        }
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            ticketUseMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void autoModify(String dataType, String startDate, String endDate) {
        ticketUseMapper.autoModify(dataType,startDate,endDate);
    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {
        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));

        ticketUseMapper.autoModify(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));

        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));

        ticketUseMapper.autoModify(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));
    }

    private void buildTicketUseQoq(TicketUseResDTO res, TicketUseResDTO lastRes) {
        if (Objects.isNull(lastRes)) {
            return;
        }
        res.setOneWayQoqRatio((res.getOneWayRatio() - lastRes.getOneWayRatio()) / lastRes.getOneWayRatio() * 100);
        res.setCitizenCardRegularQoqRatio((res.getCitizenCardRegularRatio() - lastRes.getCitizenCardRegularRatio()) / lastRes.getCitizenCardRegularRatio() * 100);
        res.setCitizenCardDiscountQoqRatio((res.getCitizenCardDiscountRatio() - lastRes.getCitizenCardDiscountRatio()) / lastRes.getCitizenCardDiscountRatio() * 100);
        res.setCitizenCardLoveQoqRatio((res.getCitizenCardLoveRatio() - lastRes.getCitizenCardLoveRatio()) / lastRes.getCitizenCardLoveRatio() * 100);
        res.setCitizenCardPreferQoqRatio((res.getCitizenCardPreferRatio() - lastRes.getCitizenCardPreferRatio()) / lastRes.getCitizenCardPreferRatio() * 100);
        res.setOneCardQoqRatio((res.getOneCardRatio() - lastRes.getOneCardRatio()) / lastRes.getOneCardRatio() * 100);
        res.setUnionCardQoqRatio((res.getUnionCardRatio() - lastRes.getUnionCardRatio()) / lastRes.getUnionCardRatio() * 100);
        res.setQrcodeQoqRatio((res.getQrcodeRatio() - lastRes.getQrcodeRatio()) / lastRes.getQrcodeRatio() * 100);
        res.setEmpCardQoqRatio((res.getEmpCardRatio() - lastRes.getEmpCardRatio()) / lastRes.getEmpCardRatio() * 100);
        res.setTimeTicketQoqRatio((res.getTimeTicketRatio() - lastRes.getTimeTicketRatio()) / lastRes.getTimeTicketRatio() * 100);
        res.setCountingTicketQoqRatio((res.getCountingTicketRatio() - lastRes.getCountingTicketRatio()) / lastRes.getCountingTicketRatio() * 100);
    }

}
