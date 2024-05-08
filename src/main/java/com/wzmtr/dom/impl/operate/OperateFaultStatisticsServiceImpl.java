package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.res.operate.fault.FaultStatisticsResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateFaultStatisticsMapper;
import com.wzmtr.dom.service.operate.OperateFaultStatisticsService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<FaultStatisticsResDTO> page = operateFaultStatisticsMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
        List<FaultStatisticsResDTO> list = page.getRecords();
        if (StringUtils.isNotEmpty(list)) {
            for (FaultStatisticsResDTO res : list) {
                if (CommonConstants.ONE_STRING.equals(res.getDataType())) {
                    res.setSum(getFaultSum(res));
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    FaultStatisticsResDTO currentDetail = operateFaultStatisticsMapper.getCurrentDetail(
                            sdf.format(res.getStartDate()), sdf.format(res.getEndDate()));
                    if (StringUtils.isNotNull(currentDetail)) {
                        res.setSum(getFaultSum(currentDetail));
                    }
                }
            }
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public FaultStatisticsResDTO report(String dataType, String startDate, String endDate) {
        FaultStatisticsResDTO res;
        if (CommonConstants.ONE_STRING.equals(dataType)) {
            res = operateFaultStatisticsMapper.getDayDetail(startDate);
        } else {
            res = operateFaultStatisticsMapper.getCurrentDetail(startDate, endDate);
        }
        if (StringUtils.isNotNull(res)) {
            res.setSum(getFaultSum(res));
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
