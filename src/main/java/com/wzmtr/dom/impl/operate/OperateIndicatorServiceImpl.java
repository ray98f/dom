package com.wzmtr.dom.impl.operate;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
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
import com.wzmtr.dom.utils.TokenUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 *
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
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<IndicatorRecordResDTO> list = operateIndicatorMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
        List<IndicatorRecordResDTO> records = list.getRecords();
        if (CollectionUtils.isEmpty(records)){
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
    public IndicatorDetailResDTO detail(String id,String startDate, String endDate) {
        IndicatorDetailResDTO detail = operateIndicatorMapper.queryInfoById(id, startDate, endDate);
        List<IndicatorInfoResDTO> indicatorList = operateIndicatorMapper.infoList(id, startDate, endDate);
        IndicatorPowerResDTO indicatorPower = operateIndicatorMapper.queryPower(id, startDate, endDate);
        if (CollectionUtils.isNotEmpty(indicatorList)) {
            detail.setIndicatorList(indicatorList);
        }
        if (null != indicatorPower) {
            detail.setIndicatorPower(indicatorPower);
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        int existFlag = operateIndicatorMapper.checkExist(indicatorRecordReqDTO.getDataType(),
                indicatorRecordReqDTO.getStartDate(),indicatorRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        // 日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(indicatorRecordReqDTO.getDataType())){
            if(!indicatorRecordReqDTO.getStartDate().equals(indicatorRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            indicatorRecordReqDTO.setDataDate(indicatorRecordReqDTO.getStartDate());
        }

        indicatorRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        indicatorRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        indicatorRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            operateIndicatorMapper.add(indicatorRecordReqDTO);
            //八项运营指标
            for(String sType:CommonConstants.OPERATE_INDICATOR_TYPE){
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

                operateIndicatorMapper.addInfo(infoReqDTO);
            }
            IndicatorPowerReqDTO powerReqDTO = new IndicatorPowerReqDTO();
            powerReqDTO.setId(TokenUtils.getUuId());
            powerReqDTO.setRecordId(indicatorRecordReqDTO.getId());
            powerReqDTO.setDataType(indicatorRecordReqDTO.getDataType());
            powerReqDTO.setDataDate(indicatorRecordReqDTO.getDataDate());
            powerReqDTO.setStartDate(indicatorRecordReqDTO.getStartDate());
            powerReqDTO.setEndDate(indicatorRecordReqDTO.getEndDate());
            powerReqDTO.setCreateBy(currentLoginUser.getPersonId());
            powerReqDTO.setUpdateBy(currentLoginUser.getPersonId());

            operateIndicatorMapper.addPower(powerReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, IndicatorRecordReqDTO indicatorRecordReqDTO) {
        indicatorRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modify(indicatorRecordReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void modifyInfo(CurrentLoginUser currentLoginUser, IndicatorInfoReqDTO indicatorInfoReqDTO) {
        indicatorInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modifyInfo(indicatorInfoReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void modifyPower(CurrentLoginUser currentLoginUser, IndicatorPowerReqDTO indicatorPowerReqDTO) {
        indicatorPowerReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateIndicatorMapper.modifyPower(indicatorPowerReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }
}
