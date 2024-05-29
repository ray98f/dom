package com.wzmtr.dom.impl.operate;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.res.operate.ConstructEventResDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.ConstructRecordResDTO;
import com.wzmtr.dom.dto.res.operate.PlanStatisticsResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateConstructMapper;
import com.wzmtr.dom.service.common.ThirdService;
import com.wzmtr.dom.service.operate.OperateConstructService;
import com.wzmtr.dom.utils.HttpUtils;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 运营日报-施工情况
 * @author zhangxin
 * @version 1.0
 * @date 2024/4/3 10:25
 */
@Service
public class OperateConstructServiceImpl implements OperateConstructService {


    @Value("${open-api.csm.constructPlan}")
    private String constructPlanApi;

    @Autowired
    private ThirdService thirdService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private OperateConstructMapper operateConstructMapper;

    @Override
    public Page<ConstructRecordResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateConstructMapper.list(pageReqDTO.of(), dataType, startDate, endDate);
    }

    @Override
    public void syncStatistics(String dataType, String startDate, String endDate) {
        PlanStatisticsReqDTO req = PlanStatisticsReqDTO.builder().dataType(dataType).startDate(startDate).endDate(endDate)
                .build();

        List<PlanStatisticsResDTO> openRes = thirdService.getPlanStatistics(startDate,endDate);

        for(PlanStatisticsResDTO p : openRes){
            switch (p.getPlanType()){
                case CommonConstants.WEEK_PLAN:
                    req.setPlan1Count(p.getTotalnum());
                    req.setReal1Count(p.getFinishednum());
                    req.setLinePlan1Count(p.getTotalnumA() + p.getTotalnumC());
                    req.setLineCanceledCount1(p.getCancelednumA() + p.getCancelednumC());
                    req.setLineChangedCount1(p.getChangednumA() + p.getChangednumC());
                    req.setLineDelayCount1(p.getDelaynumA() + p.getDelaynumC());
                    req.setLineReal1Count(p.getFinishednumA() + p.getFinishednumC());

                    req.setDepotPlan1Count(p.getTotalnumB());
                    req.setDepotCanceledCount1(p.getCancelednumB());
                    req.setDepotChangedCount1(p.getChangednumB());
                    req.setDepotDelayCount1(p.getDelaynumB());
                    req.setDepotReal1Count(p.getFinishednumB());
                    break;
                case CommonConstants.DAY_PLAN:
                    req.setPlan2Count(p.getTotalnum());
                    req.setReal2Count(p.getFinishednum());
                    req.setLinePlan2Count(p.getTotalnumA() + p.getTotalnumC());
                    req.setLineCanceledCount2(p.getCancelednumA() + p.getCancelednumC());
                    req.setLineChangedCount2(p.getChangednumA() + p.getChangednumC());
                    req.setLineDelayCount2(p.getDelaynumA() + p.getDelaynumC());
                    req.setLineReal2Count(p.getFinishednumA()+p.getFinishednumB());

                    req.setDepotPlan2Count(p.getTotalnumB());
                    req.setDepotCanceledCount2(p.getCancelednumB());
                    req.setDepotChangedCount2(p.getChangednumB());
                    req.setDepotDelayCount2(p.getDelaynumB());
                    req.setDepotReal2Count(p.getFinishednumB());
                    break;
                case CommonConstants.TEMP_PLAN:
                    req.setPlan3Count(p.getTotalnum());
                    req.setReal3Count(p.getFinishednum());
                    req.setLinePlan3Count(p.getTotalnumA() + p.getTotalnumC());
                    req.setLineCanceledCount3(p.getCancelednumA() + p.getCancelednumC());
                    req.setLineChangedCount3(p.getChangednumA() + p.getChangednumC());
                    req.setLineDelayCount3(p.getDelaynumA() + p.getDelaynumC());
                    req.setLineReal3Count(p.getFinishednumA()+p.getFinishednumC());

                    req.setDepotPlan3Count(p.getTotalnumB());
                    req.setDepotCanceledCount3(p.getCancelednumB());
                    req.setDepotChangedCount3(p.getChangednumB());
                    req.setDepotDelayCount3(p.getDelaynumB());
                    req.setDepotReal3Count(p.getFinishednumB());
                    break;
                default:
                    break;
            }
        }
        req.setTotalCount(req.getPlan1Count() + req.getPlan2Count() + req.getPlan3Count());
        req.setRealCount(req.getReal1Count() + req.getReal2Count() + req.getReal3Count());

        req.setLineCanceledCount(req.getLineCanceledCount1() + req.getLineCanceledCount2() + req.getLineCanceledCount3());
        req.setLineChangedCount(req.getLineChangedCount1() + req.getLineChangedCount2() + req.getLineChangedCount3());
        req.setLineDelayCount(req.getLineDelayCount1() + req.getLineDelayCount2() + req.getLineDelayCount3());
        req.setDepotCanceledCount(req.getDepotCanceledCount1() + req.getDepotCanceledCount2() + req.getDepotCanceledCount3());
        req.setDepotChangedCount(req.getDepotChangedCount1() + req.getDepotChangedCount2() + req.getDepotChangedCount3());
        req.setDepotDelayCount(req.getDepotDelayCount1() + req.getDepotDelayCount2() + req.getDepotDelayCount3());
        operateConstructMapper.modifyBySync(req);

        if(CommonConstants.DATA_TYPE_DAILY.equals(dataType)){
            autoModifyByDaily(dataType,startDate,endDate);
        }

    }

    @Override
    public ConstructRecordResDTO detail(String id, String dataType,String startDate, String endDate) {
        ConstructRecordResDTO detail = operateConstructMapper.queryInfoById(id, dataType,startDate, endDate);

        // TODO 增加不饱和施工数据
        if (StringUtils.isNotNull(detail)) {
            if(CommonConstants.DATA_TYPE_WEEKLY.equals(detail.getDataType()) || CommonConstants.DATA_TYPE_MONTHLY.equals(detail.getDataType())){
                detail.setUnsaturationConstruct(thirdService.getUnsaturationConstruct(DateUtil.formatDate(detail.getStartDate()),
                        DateUtil.formatDate(detail.getEndDate())));
                detail.setRemark(getConstructRemark(detail));

            }
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, ConstructRecordReqDTO constructRecordReqDTO) {
        int existFlag = operateConstructMapper.checkExist(constructRecordReqDTO.getDataType(),
                constructRecordReqDTO.getStartDate(), constructRecordReqDTO.getEndDate());
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        // 日报类型
        if (CommonConstants.DATA_TYPE_DAILY.equals(constructRecordReqDTO.getDataType())) {
            if (!constructRecordReqDTO.getStartDate().equals(constructRecordReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            constructRecordReqDTO.setDataDate(constructRecordReqDTO.getStartDate());
        }

        constructRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        constructRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        constructRecordReqDTO.setId(TokenUtils.getUuId());
        try {
            operateConstructMapper.add(constructRecordReqDTO);

            //同步统计数据 TODO 先注释 云服务器调用不了其他系统接口
            //syncStatistics(constructRecordReqDTO.getDataType(),constructRecordReqDTO.getStartDate(),constructRecordReqDTO.getEndDate());
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        if(!CommonConstants.DATA_TYPE_DAILY.equals(constructRecordReqDTO.getDataType())){
            autoModifyByDaily(constructRecordReqDTO.getDataType(),constructRecordReqDTO.getStartDate(),constructRecordReqDTO.getEndDate());
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, ConstructRecordReqDTO constructRecordReqDTO) {
        constructRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        constructRecordReqDTO.setTotalCount(constructRecordReqDTO.getPlan1Count()+constructRecordReqDTO.getPlan2Count()+constructRecordReqDTO.getPlan3Count());
        constructRecordReqDTO.setRealCount(constructRecordReqDTO.getReal1Count()+constructRecordReqDTO.getReal2Count()+constructRecordReqDTO.getReal3Count());
        int res = operateConstructMapper.modify(constructRecordReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        if(CommonConstants.DATA_TYPE_DAILY.equals(constructRecordReqDTO.getDataType())){
            autoModifyByDaily(constructRecordReqDTO.getDataType(),constructRecordReqDTO.getStartDate(),constructRecordReqDTO.getEndDate());
        }

    }

    @Override
    public void autoModify(String dataType, String startDate, String endDate) {

    }

    @Override
    public void autoModifyByDaily(String dataType, String startDate, String endDate) {
        //获取周 周一、周日
        Date monday = DateUtil.beginOfWeek(DateUtil.parseDate(startDate));
        Date sunday = DateUtil.endOfWeek(DateUtil.parseDate(startDate));


        operateConstructMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_WEEKLY,DateUtil.formatDate(monday),DateUtil.formatDate(sunday));
        //获取月 月初、月末
        Date monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
        Date monthEnd = DateUtil.endOfMonth(DateUtil.parseDate(startDate));
        operateConstructMapper.autoModifyByDaily(CommonConstants.DATA_TYPE_MONTHLY,DateUtil.formatDate(monthStart),DateUtil.formatDate(monthEnd));


    }

    @Override
    public Page<ConstructPlanResDTO> getCsmConstructPlan(String startDate, String endDate, PageReqDTO pageReqDTO) {
        Date date = DateUtil.parse(endDate);
        String endDateNext = DateUtil.formatDate(DateUtil.offsetDay(date, 1)) + CommonConstants.SYNC_DATA_TIME;
        OpenConstructPlanReqDTO req = OpenConstructPlanReqDTO.builder()
                .planbeginTime(startDate + CommonConstants.SYNC_DATA_TIME)
                .planendTime(endDateNext)
                .page(pageReqDTO.getPageNo())
                .limit(pageReqDTO.getPageSize())
                .build();
        return thirdService.getCsmImportantConstructPlan(req);
    }

    @Override
    public Page<ConstructPlanResDTO> planList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateConstructMapper.planList(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public void createPlan(CurrentLoginUser currentLoginUser, ConstructPlanBatchReqDTO constructPlanBatchReqDTO) {
        List<ConstructPlanReqDTO> planList = constructPlanBatchReqDTO.getPlanList();
        List<ConstructPlanReqDTO> newPlanList = new ArrayList<>();
        //数据填充
        for (ConstructPlanReqDTO item : planList) {
            item.setId(TokenUtils.getUuId());
            item.setCreateBy(currentLoginUser.getPersonId());
            item.setUpdateBy(currentLoginUser.getPersonId());
            item.setDataType(constructPlanBatchReqDTO.getDataType());
            item.setRecordId(constructPlanBatchReqDTO.getRecordId());

            item.setDataDate(constructPlanBatchReqDTO.getStartDate());
            item.setStartDate(constructPlanBatchReqDTO.getStartDate());
            item.setEndDate(constructPlanBatchReqDTO.getEndDate());
            newPlanList.add(item);
        }
        if (!newPlanList.isEmpty()) {
            doPlanInsertBatch(newPlanList);
        }
    }

    @Override
    public void deletePlan(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            operateConstructMapper.deletePlan(ids);
        }
    }

    @Override
    public Page<ConstructEventResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateConstructMapper.eventList(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public void createEvent(CurrentLoginUser currentLoginUser, ConstructEventReqDTO constructEventReqDTO) {
        try {
            if (CommonConstants.DATA_TYPE_DAILY.equals(constructEventReqDTO.getDataType())) {
                constructEventReqDTO.setDataDate(constructEventReqDTO.getStartDate());
            }
            constructEventReqDTO.setId(TokenUtils.getUuId());
            constructEventReqDTO.setCreateBy(currentLoginUser.getPersonId());
            constructEventReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            operateConstructMapper.createEvent(constructEventReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //更新事件统计 TODO
    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, ConstructEventReqDTO constructEventReqDTO) {
        constructEventReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateConstructMapper.modifyEvent(constructEventReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteEvent(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            // OperateEventInfoResDTO eventInfo = operateEventMapper.queryDateRange(ids);
            try {
                operateConstructMapper.deleteEvent(ids, TokenUtils.getCurrentPersonId());
            } catch (Exception e) {
                throw new CommonException(ErrorCode.DELETE_ERROR);
            }

        }
    }

    /**
     * 批量添加施工计划
     */
    @SneakyThrows
    private void doPlanInsertBatch(List<ConstructPlanReqDTO> list) {
        //批处理
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            OperateConstructMapper mapper = sqlSession.getMapper(OperateConstructMapper.class);
            for (ConstructPlanReqDTO item : list) {
                mapper.createPlan(item);
            }
            sqlSession.commit();
            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.clearCache();
            sqlSession.close();
            batchResults.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getConstructRemark(ConstructRecordResDTO detail){
        String baseRemark = CommonConstants.OPERATE_CONSTRUCT_REMARK_TPL;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        baseRemark = MessageFormat.format(baseRemark,
                detail.getLinePlan1Count()+detail.getLinePlan2Count(),
                detail.getLinePlan3Count(),
                detail.getLineCanceledCount(),
                detail.getLineExpiredCount(),
                detail.getLineChangedCount(),
                detail.getLineAddCount(),
                detail.getLineReal1Count()+detail.getLineReal2Count()+detail.getLineReal3Count(),
                detail.getLineCancledByair(),
                detail.getLineDelayCount(),
                detail.getDepotPlan1Count()+detail.getDepotPlan2Count(),
                detail.getDepotPlan3Count(),
                detail.getDepotCanceledCount(),
                detail.getDepotExpiredCount(),
                detail.getDepotAddCount(),
                detail.getDepotAddCount(),
                detail.getDepotReal1Count()+detail.getDepotReal2Count()+detail.getDepotReal3Count(),
                detail.getDepotCancledByair(),
                detail.getDepotDelayCount(),
                (detail.getLinePlan1Count()) > 0 ? decimalFormat.format(((detail.getLineReal1Count()) / (detail.getLinePlan1Count())) * 100) + "%":"0%",
                (detail.getLinePlan1Count()) > 0 ? decimalFormat.format(((detail.getLinePlan1Count() - detail.getLineChangedCount()-detail.getLineCanceledCount()) / (detail.getLinePlan1Count())) * 100) + "%":"0%",
                (detail.getDepotPlan1Count()) > 0 ? decimalFormat.format(((detail.getDepotReal1Count()) / (detail.getDepotPlan1Count())) * 100) + "%":"0%",
                (detail.getDepotPlan1Count()) > 0 ? decimalFormat.format(((detail.getDepotPlan1Count() - detail.getDepotChangedCount()-detail.getDepotCanceledCount()) / (detail.getDepotPlan1Count())) * 100) + "%":"0%",
                "0",
                "0",
                "0",
                "0"
                );
        return baseRemark;
    }
}
