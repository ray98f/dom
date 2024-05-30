package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.ProductionSummaryRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.MonthSummaryResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionSummaryResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.ProductionSummaryMapper;
import com.wzmtr.dom.service.traffic.ProductionSummaryService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 客运部-安全生产情况汇总
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 10:28
 */
@Service
@Slf4j
public class ProductionSummaryServiceImpl implements ProductionSummaryService {

    @Autowired
    private ProductionSummaryMapper productionSummaryMapper;

    @Override
    public Page<ProductionSummaryResDTO> list(String dataType, String stationCode, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return productionSummaryMapper.list(pageReqDTO.of(), dataType, stationCode, startDate, endDate);
    }


    @Override
    public ProductionSummaryResDTO detail(String recordId, String startDate, String endDate) {
        ProductionSummaryResDTO detail = productionSummaryMapper.queryInfoById(recordId, startDate, endDate);
        //获取前一日/周/月数据
        ProductionSummaryResDTO preDetail = productionSummaryMapper.queryPreInfoByDate(detail.getStationCode(), detail.getDataType(),
                DateUtil.formatDate(detail.getStartDate()), DateUtil.formatDate(detail.getEndDate()));
        if (preDetail != null) {
            detail.setType1PreCount(preDetail.getType1Count());
            detail.setType2PreCount(preDetail.getType2Count());
            detail.setType3PreCount(preDetail.getType3Count());
            detail.setType4PreCount(preDetail.getType4Count());
            detail.setType5PreCount(preDetail.getType5Count());
            detail.setType6PreCount(preDetail.getType6Count());
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO) {
        if (currentLoginUser.getStationCode() == null) {
            throw new CommonException(ErrorCode.USER_NOT_BIND_STATION);
        }

        //日报按车站隔离
        int existFlag = 0;
        if(CommonConstants.DATA_TYPE_DAILY.equals(productionSummaryRecordReqDTO.getDataType())){
            existFlag = productionSummaryMapper.checkExist(productionSummaryRecordReqDTO.getDataType(),
                    currentLoginUser.getStationCode(),
                    productionSummaryRecordReqDTO.getStartDate(),
                    productionSummaryRecordReqDTO.getEndDate());
            productionSummaryRecordReqDTO.setStationCode(currentLoginUser.getStationCode());
        }else{
            existFlag = productionSummaryMapper.checkExist(productionSummaryRecordReqDTO.getDataType(),
                    null,
                    productionSummaryRecordReqDTO.getStartDate(),
                    productionSummaryRecordReqDTO.getEndDate());
        }
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(productionSummaryRecordReqDTO.getDataType())) {

            //日期校验
            if (!productionSummaryRecordReqDTO.getStartDate().equals(productionSummaryRecordReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            productionSummaryRecordReqDTO.setDataDate(productionSummaryRecordReqDTO.getStartDate());
        }

        productionSummaryRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        productionSummaryRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        productionSummaryRecordReqDTO.setId(TokenUtils.getUuId());
        try {
            productionSummaryMapper.add(productionSummaryRecordReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //同步更新统计数据,日报更新前一日数据统计，周报/月报更新前一周/月数据统计和本周/月数据统计
        autoModify(productionSummaryRecordReqDTO.getStationCode(),
                productionSummaryRecordReqDTO.getDataType(),
                productionSummaryRecordReqDTO.getStartDate(),
                productionSummaryRecordReqDTO.getEndDate());
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO) {
        productionSummaryRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = productionSummaryMapper.modify(productionSummaryRecordReqDTO);
            if (res <= 0) {
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        } catch (Exception e) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void autoModify(String stationCode,String dataType, String startDate, String endDate) {
        productionSummaryMapper.autoModify(stationCode,dataType,startDate,endDate);
    }


}
