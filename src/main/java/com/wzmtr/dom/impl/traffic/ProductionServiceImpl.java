package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.traffic.ProductionApprovalReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionRecordReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.dto.res.traffic.*;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.system.StationRoleMapper;
import com.wzmtr.dom.mapper.traffic.ProductionMapper;
import com.wzmtr.dom.mapper.traffic.ProductionSummaryMapper;
import com.wzmtr.dom.service.traffic.ProductionService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 客运部-安全生产情况
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 17:23
 */
@Service
@Slf4j
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionSummaryMapper productionSummaryMapper;

    @Autowired
    private ProductionMapper productionMapper;

    @Autowired
    private StationRoleMapper stationRoleMapper;

    @Override
    public Page<ProductionRecordResDTO> list(String dataType, String stationCode, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return productionMapper.list(pageReqDTO.of(), dataType, stationCode, startDate, endDate);
    }

    @Override
    public ProductionDetailResDTO detail(String id, String startDate, String endDate) {
        ProductionDetailResDTO detail = productionMapper.queryInfoById(id, startDate, endDate);
        ProductionSummaryResDTO summaryRes = productionSummaryMapper.queryInfoByDate(detail.getStationCode(), detail.getDataType(),
                DateUtil.formatDate(detail.getStartDate()), DateUtil.formatDate(detail.getEndDate()));

        if (summaryRes != null) {
            detail.setType1Desc(summaryRes.getType1Desc());
            detail.setType2Desc(summaryRes.getType2Desc());
            detail.setType3Desc(summaryRes.getType3Desc());
            detail.setType4Desc(summaryRes.getType4Desc());
            detail.setType5Desc(summaryRes.getType5Desc());
            detail.setType6Desc(summaryRes.getType6Desc());
        }

        //获取前一日/周/月数据
        ProductionSummaryResDTO preSummaryRes = productionSummaryMapper.queryPreInfoByDate(detail.getStationCode(), detail.getDataType(),
                DateUtil.formatDate(detail.getStartDate()), DateUtil.formatDate(detail.getEndDate()));
        if (preSummaryRes != null) {
            detail.setType1PreCount(preSummaryRes.getType1Count());
            detail.setType2PreCount(preSummaryRes.getType2Count());
            detail.setType3PreCount(preSummaryRes.getType3Count());
            detail.setType4PreCount(preSummaryRes.getType4Count());
            detail.setType5PreCount(preSummaryRes.getType5Count());
            detail.setType6PreCount(preSummaryRes.getType6Count());
        }

        return detail;
    }

    @Override
    public ProductionDetailResDTO queryInfo(String dataType, String stationCode, String startDate,
                                            String endDate) {
        ProductionDetailResDTO detail = productionMapper.queryInfoByStation(dataType, stationCode, startDate, endDate);
        if (detail != null) {
            ProductionSummaryResDTO summaryRes = productionSummaryMapper.queryInfoByDate(detail.getStationCode(), detail.getDataType(),
                    DateUtil.formatDate(detail.getStartDate()), DateUtil.formatDate(detail.getEndDate()));

            if (summaryRes != null) {
                detail.setType1Desc(summaryRes.getType1Desc());
                detail.setType2Desc(summaryRes.getType2Desc());
                detail.setType3Desc(summaryRes.getType3Desc());
                detail.setType4Desc(summaryRes.getType4Desc());
                detail.setType5Desc(summaryRes.getType5Desc());
                detail.setType6Desc(summaryRes.getType6Desc());
            }

            //获取前一日/周/月数据
            ProductionSummaryResDTO preSummaryRes = productionSummaryMapper.queryPreInfoByDate(detail.getStationCode(), detail.getDataType(),
                    DateUtil.formatDate(detail.getStartDate()), DateUtil.formatDate(detail.getEndDate()));
            if (preSummaryRes != null) {
                detail.setType1PreCount(preSummaryRes.getType1Count());
                detail.setType2PreCount(preSummaryRes.getType2Count());
                detail.setType3PreCount(preSummaryRes.getType3Count());
                detail.setType4PreCount(preSummaryRes.getType4Count());
                detail.setType5PreCount(preSummaryRes.getType5Count());
                detail.setType6PreCount(preSummaryRes.getType6Count());
            }
        }

        return detail;
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, ProductionRecordReqDTO productionRecordReqDTO) {
        if (currentLoginUser.getStationCode() == null) {
            throw new CommonException(ErrorCode.USER_NOT_BIND_STATION);
        }
        int existFlag = productionMapper.checkExist(productionRecordReqDTO.getDataType(),
                currentLoginUser.getStationCode(),
                productionRecordReqDTO.getStartDate(),
                productionRecordReqDTO.getEndDate());
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        //日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(productionRecordReqDTO.getDataType())) {

            //日期校验
            if (!productionRecordReqDTO.getStartDate().equals(productionRecordReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }

            productionRecordReqDTO.setDataDate(productionRecordReqDTO.getStartDate());
        }

        productionRecordReqDTO.setStationCode(currentLoginUser.getStationCode());
        productionRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        productionRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        productionRecordReqDTO.setId(TokenUtils.getUuId());
        try {
            productionMapper.add(productionRecordReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新周、月统计数
        if (CommonConstants.DATA_TYPE_MONTHLY.equals(productionRecordReqDTO.getDataType())
                || CommonConstants.DATA_TYPE_WEEKLY.equals(productionRecordReqDTO.getDataType())) {
            productionMapper.modifyRecordCount(productionRecordReqDTO.getId(),
                    productionRecordReqDTO.getStationCode(),
                    productionRecordReqDTO.getStartDate(),
                    productionRecordReqDTO.getEndDate());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitApproval(CurrentLoginUser currentLoginUser, ProductionRecordReqDTO productionRecordReqDTO) {
        productionRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());

        //设置为审核中
        productionRecordReqDTO.setStatus(CommonConstants.ONE_STRING);
        try {
            //查询本日是否已有审核记录
            ProductionApprovalResDTO approvalRes = productionMapper.queryApprovalByDate(productionRecordReqDTO.getDataType(),
                    productionRecordReqDTO.getStartDate(), productionRecordReqDTO.getEndDate());
            String approvalId = "";

            if (approvalRes == null) {

                //获取当前审核站
                StationRoleResDTO stationRoleRes = stationRoleMapper.getCurrentStation();

                //先创建本日审核记录
                ProductionApprovalReqDTO approvalReq = new ProductionApprovalReqDTO();
                approvalReq.setId(TokenUtils.getUuId());
                approvalReq.setTitle(CommonConstants.DEFAULT_PRODUCTION_TITLE);
                approvalReq.setDataType(productionRecordReqDTO.getDataType());
                approvalReq.setDataDate(productionRecordReqDTO.getStartDate());
                approvalReq.setStartDate(productionRecordReqDTO.getStartDate());
                approvalReq.setEndDate(productionRecordReqDTO.getEndDate());
                approvalReq.setApprovalStation(stationRoleRes.getStationCode());
                approvalReq.setSubmitStation(productionRecordReqDTO.getStationCode());
                approvalReq.setCreateBy(currentLoginUser.getPersonId());
                productionMapper.createProductionApproval(approvalReq);
                approvalId = approvalReq.getId();

            } else {
                approvalId = approvalRes.getId();

                // 已提交车站
                String submitStation = approvalRes.getSubmitStation();
                List<String> stationList = Arrays.asList(submitStation.split(CommonConstants.COMMA));
                if (!stationList.contains(productionRecordReqDTO.getStationCode())) {
                    stationList.add(productionRecordReqDTO.getStationCode());
                    submitStation = String.join(CommonConstants.COMMA, stationList);
                }
                ProductionApprovalReqDTO approvalReq = new ProductionApprovalReqDTO();
                approvalReq.setId(approvalId);
                approvalReq.setSubmitStation(submitStation);
                productionMapper.modifyProductionApproval(approvalReq);
            }

            productionMapper.createProductionApprovalRelation(TokenUtils.getUuId(), productionRecordReqDTO.getId(), approvalId, currentLoginUser.getPersonId());
            int res = productionMapper.modify(productionRecordReqDTO);
            if (res <= 0) {
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        } catch (Exception e) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //查询驳回记录 ,存在 则发送审核站消息提醒 TODO
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void productionApproval(CurrentLoginUser currentLoginUser, ProductionApprovalReqDTO productionApprovalReqDTO) {

        try {
            ProductionRecordReqDTO productionRecordReqDTO = new ProductionRecordReqDTO();
            productionRecordReqDTO.setId(productionApprovalReqDTO.getRecordId());

            // 驳回操作
            if (CommonConstants.ONE_STRING.equals(productionApprovalReqDTO.getApprovalStatus())) {
                productionRecordReqDTO.setStatus(CommonConstants.ZERO_STRING);

                //更新已提交车站
                ProductionApprovalRelationResDTO approvalRelationRes = productionMapper.queryApprovalRelationById(productionApprovalReqDTO.getId(),
                        productionApprovalReqDTO.getRecordId());
                String submitStation = approvalRelationRes.getSubmitStation();

                if (StringUtils.isNotEmpty(submitStation)) {
                    List<String> submitStationList = Arrays.asList(submitStation.split(CommonConstants.COMMA));

                    // 已提交车站中删除该车站
                    submitStationList.removeIf(item -> item.equals(approvalRelationRes.getSubmitStation()));
                    submitStation = String.join(CommonConstants.COMMA, submitStationList);
                }

                ProductionApprovalReqDTO approvalReq = new ProductionApprovalReqDTO();
                approvalReq.setId(productionApprovalReqDTO.getId());
                approvalReq.setSubmitStation(submitStation);
                productionMapper.modifyProductionApproval(approvalReq);

                //通过
            } else {
                productionRecordReqDTO.setStatus(CommonConstants.TWO_STRING);
            }

            //更新审批记录
            productionMapper.modifyProductionApprovalRelation(productionApprovalReqDTO.getId(),
                    productionApprovalReqDTO.getRecordId(),
                    productionApprovalReqDTO.getApprovalStatus(),
                    currentLoginUser.getPersonId());
            productionMapper.modify(productionRecordReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //通知车站修改
        if (CommonConstants.ONE_STRING.equals(productionApprovalReqDTO.getApprovalStatus())) {
            //TODO
        }
    }

    @Override
    public Page<ProductionInfoResDTO> eventList(String stationCode, String productionType, String dataType,
                                                String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return productionMapper.eventList(pageReqDTO.of(), stationCode, productionType, dataType, startDate, endDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEvent(CurrentLoginUser currentLoginUser, ProductionInfoReqDTO productionInfoReqDTO) {
        if (CommonConstants.DATA_TYPE_DAILY.equals(productionInfoReqDTO.getDataType())) {
            productionInfoReqDTO.setDataDate(productionInfoReqDTO.getStartDate());
        }
        productionInfoReqDTO.setId(TokenUtils.getUuId());
        productionInfoReqDTO.setCreateBy(currentLoginUser.getPersonId());
        productionInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        productionMapper.createEvent(productionInfoReqDTO);
        //更新事件统计
        updateSummaryCount(productionInfoReqDTO.getStationCode(), productionInfoReqDTO.getStartDate(), productionInfoReqDTO.getEndDate());
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, ProductionInfoReqDTO productionInfoReqDTO) {
        productionInfoReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = productionMapper.modifyEvent(productionInfoReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //更新事件统计
        List<String> ids = new ArrayList<>();
        ids.add(productionInfoReqDTO.getId());
        ProductionInfoResDTO eventInfo = productionMapper.queryDateRange(ids);
        updateSummaryCount(eventInfo.getStationCode(),
                DateUtil.formatDate(eventInfo.getStartDate()), DateUtil.formatDate(eventInfo.getEndDate()));
    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            ProductionInfoResDTO eventInfo = productionMapper.queryDateRange(ids);
            try {
                productionMapper.deleteEvent(ids, TokenUtils.getCurrentPersonId());
            } catch (Exception e) {
                throw new CommonException(ErrorCode.DELETE_ERROR);
            }

            //更新事件统计
            updateSummaryCount(eventInfo.getStationCode(),
                    DateUtil.formatDate(eventInfo.getStartDate()), DateUtil.formatDate(eventInfo.getEndDate()));
        }
    }

    @Override
    public Page<ProductionApprovalResDTO> queryApproval(CurrentLoginUser currentLoginUser, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return productionMapper.queryApproval(pageReqDTO.of(), startDate, endDate);
    }

    /**
     * 事件统计更新
     */
    @Transactional(rollbackFor = Exception.class)
    private void updateSummaryCount(String stationCode, String startDate, String endDate) {
        try {
            List<ProductionRecordResDTO> res = productionMapper.listAll(stationCode, startDate, endDate);
            if (StringUtils.isNotEmpty(res)) {
                for (ProductionRecordResDTO item : res) {
                    //更新记录统计数据
                    productionMapper.modifyRecordCount(item.getId(),
                            stationCode,
                            DateUtil.formatDate(item.getStartDate()),
                            DateUtil.formatDate(item.getEndDate()));
                    //更新汇总统计数据
                    productionMapper.modifySummaryCount(item.getId(),
                            item.getDataType(),
                            stationCode,
                            DateUtil.formatDate(item.getStartDate()),
                            DateUtil.formatDate(item.getEndDate()));
                }
            }
        } catch (Exception e) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

    }

}
