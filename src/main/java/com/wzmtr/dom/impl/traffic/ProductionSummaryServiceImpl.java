package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.ProductionSummaryRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionSummaryResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.ProductionSummaryMapper;
import com.wzmtr.dom.service.traffic.ProductionSummaryService;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客运部-安全生产情况汇总
 *
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
    public Page<ProductionSummaryResDTO> list(String dataType,String stationCode, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return productionSummaryMapper.list(pageReqDTO.of(),dataType,stationCode,startDate,endDate);
    }

    @Override
    public ProductionSummaryResDTO detail(String recordId) {
        ProductionSummaryResDTO detail = productionSummaryMapper.queryInfoById(recordId);
        //获取前一日/周/月数据
        ProductionSummaryResDTO preDetail = productionSummaryMapper.queryPreInfoByDate(detail.getStationCode(),detail.getDataType(),
                DateUtil.formatDate(detail.getStartDate()),DateUtil.formatDate(detail.getEndDate()));
        if(preDetail != null){
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
    public void add(CurrentLoginUser currentLoginUser, ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO) {
        if(currentLoginUser.getStationCode() == null){
            throw new CommonException(ErrorCode.USER_NOT_BIND_STATION);
        }
        int existFlag = productionSummaryMapper.checkExist(productionSummaryRecordReqDTO.getDataType(),
                currentLoginUser.getStationCode(),
                productionSummaryRecordReqDTO.getStartDate(),
                productionSummaryRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(productionSummaryRecordReqDTO.getDataType())){

            //日期校验
            if(!productionSummaryRecordReqDTO.getStartDate().equals(productionSummaryRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }

            productionSummaryRecordReqDTO.setDataDate(productionSummaryRecordReqDTO.getStartDate());
        }

        productionSummaryRecordReqDTO.setStationCode(currentLoginUser.getStationCode());
        productionSummaryRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        productionSummaryRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        productionSummaryRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            productionSummaryMapper.add(productionSummaryRecordReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO) {
        productionSummaryRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = productionSummaryMapper.modify(productionSummaryRecordReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }
}
