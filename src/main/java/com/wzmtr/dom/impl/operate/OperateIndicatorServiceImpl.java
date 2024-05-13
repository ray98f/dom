package com.wzmtr.dom.impl.operate;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.IndicatorInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorPowerReqDTO;
import com.wzmtr.dom.dto.req.operate.IndicatorRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorDetailResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorInfoResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorPowerResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.DataType;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateIndicatorMapper;
import com.wzmtr.dom.service.operate.OperateIndicatorService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 运营日报-初期运营指标
 * @author zhangxin
 * @version 1.0
 * @date 2024/4/2 13:43
 */
@Service
public class OperateIndicatorServiceImpl implements OperateIndicatorService {

    @Autowired
    private OperateIndicatorMapper operateIndicatorMapper;

    @Override
    public Page<IndicatorRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<IndicatorRecordResDTO> list = operateIndicatorMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
        List<IndicatorRecordResDTO> records = list.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new Page<>();
        }
        if (!DataType.DAY.getCode().equals(dataType)) {
            // 周报月报多返回一个正点率和总能耗
            records.forEach(a -> {
                IndicatorInfoResDTO infoRes = operateIndicatorMapper.queryInfoByDate(DateUtil.formatDate(a.getStartDate()), DateUtil.formatDate(a.getEndDate()), dataType, "2");
                IndicatorPowerResDTO indicatorPowerResDTO = operateIndicatorMapper.queryPowerByDate(DateUtil.formatDate(a.getStartDate()), DateUtil.formatDate(a.getEndDate()), dataType);
                if (null != indicatorPowerResDTO) {
                    a.setEnergyConsumption(indicatorPowerResDTO.getEnergyConsumption());
                }
                if (null != infoRes) {
                    a.setPunctualityRate(infoRes.getPunctualityRate());
                }
            });
        }
        return list;
    }

    @Override
    public IndicatorDetailResDTO detail(String id, String dataType, String startDate, String endDate) {
        IndicatorDetailResDTO detail = operateIndicatorMapper.queryInfoById(id, dataType, startDate, endDate);

        if (StringUtils.isNotNull(detail)) {
            buildIndicatorDetail(detail);
            IndicatorPowerResDTO indicatorPower = operateIndicatorMapper.queryPower(id, dataType, startDate, endDate);
            detail.setIndicatorPower(indicatorPower);
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        int existFlag = operateIndicatorMapper.checkExist(indicatorRecordReqDTO.getDataType(),
                indicatorRecordReqDTO.getStartDate(), indicatorRecordReqDTO.getEndDate());
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        // 日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(indicatorRecordReqDTO.getDataType())) {
            if (!indicatorRecordReqDTO.getStartDate().equals(indicatorRecordReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            indicatorRecordReqDTO.setDataDate(indicatorRecordReqDTO.getStartDate());
        }
        indicatorRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        indicatorRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        indicatorRecordReqDTO.setId(TokenUtils.getUuId());
        try {
            operateIndicatorMapper.add(indicatorRecordReqDTO);

            IndicatorPowerReqDTO powerReqDTO = buildIndicatorPower(currentLoginUser, indicatorRecordReqDTO);
            operateIndicatorMapper.addPower(powerReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        indicatorRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modify(indicatorRecordReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void modifyInfo(CurrentLoginUser currentLoginUser, IndicatorInfoReqDTO indicatorInfoReqDTO) {
        //TODO 注释掉 废弃
/*        indicatorInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modifyInfo(indicatorInfoReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }*/
    }

    @Override
    public void modifyPower(CurrentLoginUser currentLoginUser, IndicatorPowerReqDTO indicatorPowerReqDTO) {
        indicatorPowerReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modifyPower(indicatorPowerReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void syncData(String dataType, String startDate, String endDate) {
        //TODO
        // 行调数据接口
        // 乘务数据接口
        // EAM数据接口

    }

    private IndicatorDetailResDTO buildIndicatorDetail(IndicatorDetailResDTO detail){

        DecimalFormat decimalFormat = new DecimalFormat(CommonConstants.DECIMAL_FMT_STRING);
        // 正点率 (实际开行列此-晚点次数) / 实际开行列次
        if(detail.getRealRunCount() > 0){
            detail.setPunctualityRate(decimalFormat.format(((detail.getRealRunCount() - detail.getDelayCount()) / detail.getRealRunCount()) * CommonConstants.ONE_HUNDRED_LONG) + CommonConstants.PERCENTAGE_STRING);
        }else{
            detail.setPunctualityRate(CommonConstants.ZERO_PERCENTAGE_STRING);
        }
        // 兑现率 (计划兑现列次) / 计划开行列次
        if(detail.getPlanRunCount() > 0){
            detail.setFulfillmentRate(decimalFormat.format(( detail.getPlanPromiseCount() / detail.getPlanRunCount()) * CommonConstants.ONE_HUNDRED_LONG) + CommonConstants.PERCENTAGE_STRING);
        }else{
            detail.setFulfillmentRate(CommonConstants.ZERO_PERCENTAGE_STRING);
        }
        // 列出服务可靠度 运营车公里数(万列公里) / 延误次数, 延误次数 15分钟以内延误次数+15分钟以上延误次数
        if((detail.getDelay3Count() + detail.getDelay4Count()) > 0){
            detail.setServiceReliability(decimalFormat.format( detail.getOperate1Kilometer() / (detail.getDelay3Count() + detail.getDelay4Count())));
        }else{
            detail.setServiceReliability(CommonConstants.INFINITY_STRING);
        }

        // 列出退出正线运营故障率  退出正线数 / 运营车公里(万列公里)
        // 车辆系统故障率  车辆系统故障数 / 运营车公里(万列公里)
        // 信号系统故障率  信号系统故障数 / 运营车公里(万列公里)
        // 供电系统故障率  供电系统故障数 / 运营车公里(万列公里)
        if(detail.getOperate1Kilometer() > 0){
            detail.setEvent1Rate(decimalFormat.format( detail.getOperateEvent1() / detail.getOperate1Kilometer()));
            detail.setEvent2Rate(decimalFormat.format( detail.getOperateEvent2() / detail.getOperate1Kilometer()));
            detail.setEvent3Rate(decimalFormat.format( detail.getOperateEvent3() / detail.getOperate1Kilometer()));
            detail.setEvent4Rate(decimalFormat.format( detail.getOperateEvent4() / detail.getOperate1Kilometer()));
        }else{
            detail.setEvent1Rate(CommonConstants.ZERO_STRING);
            detail.setEvent2Rate(CommonConstants.ZERO_STRING);
            detail.setEvent3Rate(CommonConstants.ZERO_STRING);
            detail.setEvent4Rate(CommonConstants.ZERO_STRING);
        }

        return detail;
    }
    @NotNull
    private static IndicatorInfoReqDTO buildIndicatorInfo(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO, String sType) {
        IndicatorInfoReqDTO infoReqDTO = new IndicatorInfoReqDTO();
        infoReqDTO.setId(TokenUtils.getUuId());
        infoReqDTO.setRecordId(indicatorRecordReqDTO.getId());
        infoReqDTO.setIndicatorType(sType);
        infoReqDTO.setDataType(indicatorRecordReqDTO.getDataType());
        infoReqDTO.setDataDate(indicatorRecordReqDTO.getDataDate());
        infoReqDTO.setStartDate(indicatorRecordReqDTO.getStartDate());
        infoReqDTO.setEndDate(indicatorRecordReqDTO.getEndDate());
        infoReqDTO.setCreateBy(currentLoginUser.getPersonId());
        infoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        return infoReqDTO;
    }

    @NotNull
    private static IndicatorPowerReqDTO buildIndicatorPower(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        IndicatorPowerReqDTO powerReqDTO = new IndicatorPowerReqDTO();
        powerReqDTO.setId(TokenUtils.getUuId());
        powerReqDTO.setRecordId(indicatorRecordReqDTO.getId());
        powerReqDTO.setDataType(indicatorRecordReqDTO.getDataType());
        powerReqDTO.setDataDate(indicatorRecordReqDTO.getDataDate());
        powerReqDTO.setStartDate(indicatorRecordReqDTO.getStartDate());
        powerReqDTO.setEndDate(indicatorRecordReqDTO.getEndDate());
        powerReqDTO.setCreateBy(currentLoginUser.getPersonId());
        powerReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        return powerReqDTO;
    }
}
