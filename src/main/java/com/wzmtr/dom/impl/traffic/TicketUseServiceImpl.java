package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.TicketUseReqDTO;
import com.wzmtr.dom.dto.res.traffic.TicketUseResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
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
        compare(res);
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
    public void add(CurrentLoginUser currentLoginUser, TicketUseReqDTO ticketUseReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = ticketUseMapper.selectIsExist(ticketUseReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期线网车票过闸使用情况数据已存在，无法重复新增");
        }
        ticketUseReqDTO.setId(TokenUtils.getUuId());
        ticketUseReqDTO.setCreateBy(currentLoginUser.getPersonId());
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

    private TicketUseResDTO compare(TicketUseResDTO detail){

        //获取前一日/周/月数据
        TicketUseResDTO preDetail;

        switch (detail.getDataType()){
            case CommonConstants.DATA_TYPE_DAILY:
                Date yesterday = DateUtil.offsetDay(detail.getStartDate(),-1);
                preDetail = ticketUseMapper.selectDetailById(null,detail.getDataType(),DateUtil.formatDate(yesterday),DateUtil.formatDate(yesterday));
                break;
            case CommonConstants.DATA_TYPE_WEEKLY:
                Date preMonday = DateUtil.offsetDay(detail.getStartDate(),-7);
                Date preSunday = DateUtil.offsetDay(detail.getEndDate(),-7);
                preDetail = ticketUseMapper.selectDetailById(null,detail.getDataType(),DateUtil.formatDate(preMonday),DateUtil.formatDate(preSunday));
                break;
            default:
                // 获取上个月的第一天
                Date firstDayOfLastMonth = DateUtil.beginOfMonth(DateUtil.offsetMonth(detail.getStartDate(), -1));
                // 获取上个月的最后一天
                Date lastDayOfLastMonth = DateUtil.endOfMonth(DateUtil.offsetMonth(detail.getStartDate(), -1));
                preDetail = ticketUseMapper.selectDetailById(null,detail.getDataType(),DateUtil.formatDate(firstDayOfLastMonth),DateUtil.formatDate(lastDayOfLastMonth));
                break;
        }
        // 环比计算
        if(Objects.nonNull(preDetail)){
            detail.setOneWayQoqRatio(getQoq(detail.getOneWayTicket(),preDetail.getOneWayTicket()));
            detail.setCitizenCardRegularQoqRatio(getQoq(detail.getCitizenCardRegular(),preDetail.getCitizenCardRegular()));
            detail.setCitizenCardDiscountQoqRatio(getQoq(detail.getCitizenCardDiscount(),preDetail.getCitizenCardDiscount()));
            detail.setCitizenCardLoveQoqRatio(getQoq(detail.getCitizenCardLove(),preDetail.getCitizenCardLove()));
            detail.setCitizenCardPreferQoqRatio(getQoq(detail.getCitizenCardPrefer(),preDetail.getCitizenCardPrefer()));
            detail.setOneCardQoqRatio(getQoq(detail.getOneCard(),preDetail.getOneCard()));
            detail.setUnionCardQoqRatio(getQoq(detail.getUnionCard(),preDetail.getUnionCard()));
            detail.setQrcodeQoqRatio(getQoq(detail.getQrcode(),preDetail.getQrcode()));
            detail.setEmpCardQoqRatio(getQoq(detail.getEmpCard(),preDetail.getEmpCard()));
            detail.setTimeTicketQoqRatio(getQoq(detail.getTimeTicket(),preDetail.getTimeTicket()));
            detail.setCountingTicketQoqRatio(getQoq(detail.getCountingTicket(),preDetail.getCountingTicket()));
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
