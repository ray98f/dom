package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.res.operate.fault.FaultStatisticsResDTO;
import com.wzmtr.dom.dto.res.operate.fault.ReportFaultStatisticsResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateFaultStatisticsMapper;
import com.wzmtr.dom.service.operate.OperateFaultStatisticsService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/4/2 13:43
 */
@Service
public class OperateFaultStatisticsServiceImpl implements OperateFaultStatisticsService {

    @Autowired
    private OperateFaultStatisticsMapper operateFaultStatisticsMapper;

    @Override
    public Page<FaultStatisticsResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<FaultStatisticsResDTO> list = operateFaultStatisticsMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
        List<FaultStatisticsResDTO> records = list.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return list;
        }
        records.forEach(a -> a.setSum(getFaultSum(a)));
        return list;
    }

    @Override
    public ReportFaultStatisticsResDTO report(String date) {
        ReportFaultStatisticsResDTO res = new ReportFaultStatisticsResDTO();
        FaultStatisticsResDTO today = operateFaultStatisticsMapper.getTodayDetail(date);
        if (StringUtils.isNotNull(today)) {
            res.setToday(today);
        }
        FaultStatisticsResDTO currentMonth =
                operateFaultStatisticsMapper.getCurrentMonthDetail(date.substring(0, 7) + "01", date);
        if (StringUtils.isNotNull(currentMonth)) {
            res.setCurrentMonth(currentMonth);
        }
        return res;
    }

    public static long getFaultSum(FaultStatisticsResDTO a) {
        return a.getChangeDistributionNum() + a.getContactNetworkNum() + a.getCommunicationNum() +
                a.getSignalNum() + a.getPlatformDoorsNum() + a.getHydropowerNum() + a.getBuildingConstructionNum() +
                a.getMonitorNum() + a.getAfcNum() + a.getFasNum() + a.getEscalatorNum() + a.getOfficialDutiesNum() +
                a.getBridgeTunnelNum() + a.getEngineeringVehicleNum() + a.getVehicleNum();
    }


    @Override
    public void add(CurrentLoginUser currentLoginUser, OperateFaultStatisticsReqDTO reqDTO) {
        Integer result = operateFaultStatisticsMapper.selectIsExist(reqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期数据已存在，无法重复新增");
        }
        // 日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(reqDTO.getDataType())) {
            if (!reqDTO.getStartDate().equals(reqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            reqDTO.setDataDate(reqDTO.getStartDate());
        }
        reqDTO.setCreateBy(currentLoginUser.getPersonId());
        reqDTO.setUpdateBy(currentLoginUser.getPersonId());
        reqDTO.setId(TokenUtils.getUuId());
        operateFaultStatisticsMapper.add(reqDTO);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, OperateFaultStatisticsReqDTO operateEventInfoReqDTO) {
        operateEventInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        operateFaultStatisticsMapper.modify(operateEventInfoReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            operateFaultStatisticsMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }
}
