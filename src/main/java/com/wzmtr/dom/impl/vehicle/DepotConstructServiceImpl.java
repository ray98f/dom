package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanBatchReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructPlanResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DepotConstructMapper;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
import com.wzmtr.dom.service.common.ThirdService;
import com.wzmtr.dom.service.vehicle.DepotConstructService;
import com.wzmtr.dom.utils.HttpUtils;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 车辆部- 车场调车/施工情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 9:35
 */
@Service
@Slf4j
public class DepotConstructServiceImpl implements DepotConstructService {

    @Value("${open-api.csm.constructPlan}")
    private String constructPlanApi;

    @Autowired
    private ThirdService thirdService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DepotConstructMapper depotConstructMapper;

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public Page<DepotConstructRecordResDTO> list(String depotCode,String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return depotConstructMapper.list(pageReqDTO.of(),depotCode,dataType,startDate,endDate);
    }

    @Override
    public DepotConstructDetailResDTO detail(String id) {
        return depotConstructMapper.queryInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CurrentLoginUser currentLoginUser, DepotConstructRecordReqDTO depotConstructRecordReqDTO) {
        int existFlag = depotConstructMapper.checkExist(depotConstructRecordReqDTO.getDepotCode(),depotConstructRecordReqDTO.getDataType(),
                depotConstructRecordReqDTO.getStartDate(),depotConstructRecordReqDTO.getEndDate());
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        // 日报类型
        if(CommonConstants.DATA_TYPE_DAILY.equals(depotConstructRecordReqDTO.getDataType())){
            //日报起始和终止日期需相等
            if(!depotConstructRecordReqDTO.getStartDate().equals(depotConstructRecordReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }

            //日报-数据所属日期
            depotConstructRecordReqDTO.setDataDate(depotConstructRecordReqDTO.getStartDate());
        }

        depotConstructRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        depotConstructRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        depotConstructRecordReqDTO.setId(TokenUtils.getUuId());
        try{
            depotConstructMapper.add(depotConstructRecordReqDTO);
        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }

        //周报/月报类型，需要做数据统计
        if(CommonConstants.DATA_TYPE_WEEKLY.equals(depotConstructRecordReqDTO.getDataType())
                || CommonConstants.DATA_TYPE_MONTHLY.equals(depotConstructRecordReqDTO.getDataType())){
            updateDepotCount(depotConstructRecordReqDTO.getId(),
                             depotConstructRecordReqDTO.getDepotCode(),
                             depotConstructRecordReqDTO.getStartDate(),
                             depotConstructRecordReqDTO.getEndDate());
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, DepotConstructRecordReqDTO depotConstructRecordReqDTO) {
        depotConstructRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        try {
            int res = depotConstructMapper.modify(depotConstructRecordReqDTO);
            if( res <= 0){
                throw new CommonException(ErrorCode.UPDATE_ERROR);
            }
        }catch (Exception e){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void syncData(CurrentLoginUser currentLoginUser, String recordId) {
        DepotConstructDetailResDTO resDTO = depotConstructMapper.queryInfoById(recordId);
        //TODO 调取数据 施工计划统计 行车调度统计

        //TODO TEST
        DepotConstructRecordReqDTO recordReqDTO = new DepotConstructRecordReqDTO();
        recordReqDTO.setId(recordId);
        recordReqDTO.setA1Plan(1);
        recordReqDTO.setBPlan(2);
        recordReqDTO.setDaySupPlan(3);
        recordReqDTO.setTempPlan(4);
        recordReqDTO.setA1Complete(5);
        recordReqDTO.setBPlan(6);
        recordReqDTO.setDaySupComplete(7);
        recordReqDTO.setTempComplete(8);
        recordReqDTO.setPlanConstruct(1+2+3+4);
        recordReqDTO.setRealConstruct(5+6+7+8);
        recordReqDTO.setPowerSupply(1);
        recordReqDTO.setShuntCount(2);
        recordReqDTO.setShuntHook(3);
        recordReqDTO.setShuntTime(20);
        //TODO 更新日统计数据 重要指标
        depotConstructMapper.modifyCount(recordReqDTO);
        indicatorMapper.modifyDayCount(DateUtil.formatDate(resDTO.getStartDate()),DateUtil.formatDate(resDTO.getEndDate()));
    }

    @Override
    public Page<ConstructPlanResDTO> getCsmConstructPlan(String depotCode, String startDate, String endDate, PageReqDTO pageReqDTO) {

        Date date = DateUtil.parse(endDate);
        String endDateNext = DateUtil.formatDate(DateUtil.offsetDay(date, 1)) + CommonConstants.SYNC_DATA_TIME;
        String workAreaDesc = "";
        switch (depotCode){
            case CommonConstants.STATION_280:
                workAreaDesc = CommonConstants.XT;
                break;
            default:
                workAreaDesc = CommonConstants.TT;
                break;
        }

        OpenConstructPlanReqDTO req = OpenConstructPlanReqDTO.builder()
                .planbeginTime(startDate + CommonConstants.SYNC_DATA_TIME)
                .planendTime(endDateNext)
                .workType(CommonConstants.CONSTRUCT_DEPOT)
                .workAreaDesc(workAreaDesc)
                .page(pageReqDTO.getPageNo())
                .limit(pageReqDTO.getPageSize())
                .build();
        return thirdService.getCsmConstructPlan(req);
    }

    @Override
    public Page<DepotConstructPlanResDTO> planList(String depotCode, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return depotConstructMapper.planList(pageReqDTO.of(),depotCode,startDate,endDate);
    }

    @Override
    public void createPlan(CurrentLoginUser currentLoginUser, DepotConstructPlanBatchReqDTO depotConstructPlanBatchReqDTO) {
        List<DepotConstructPlanReqDTO> planList = depotConstructPlanBatchReqDTO.getPlanList();
        List<DepotConstructPlanReqDTO> newPlanList = new ArrayList<>();
        //数据填充
        for(DepotConstructPlanReqDTO item: planList){
            item.setId(TokenUtils.getUuId());
            item.setCreateBy(currentLoginUser.getPersonId());
            item.setUpdateBy(currentLoginUser.getPersonId());
            item.setDataType(depotConstructPlanBatchReqDTO.getDataType());
            item.setRecordId(depotConstructPlanBatchReqDTO.getRecordId());
            item.setDepotCode(depotConstructPlanBatchReqDTO.getDepotCode());
            item.setDataDate(depotConstructPlanBatchReqDTO.getStartDate());
            item.setStartDate(depotConstructPlanBatchReqDTO.getStartDate());
            item.setEndDate(depotConstructPlanBatchReqDTO.getEndDate());
            newPlanList.add(item);
        }
        if(newPlanList.size()>0){
            doPlanInsertBatch(newPlanList);
        }

    }

    @Override
    public void deletePlan(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            depotConstructMapper.deletePlan(ids);
        }
    }

    /**
     * 周报、月报更新车场数据
     */
    private void updateDepotCount(String id,String depotCode,String startDate,String endDate){
        depotConstructMapper.modifyDepotCount(id,depotCode,startDate,endDate);
    }

    /**
     * 批量添加施工计划
     */
    @SneakyThrows
    private void doPlanInsertBatch(List<DepotConstructPlanReqDTO> list){
        //批处理
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        try{
            DepotConstructMapper mapper = sqlSession.getMapper(DepotConstructMapper.class);
            for(DepotConstructPlanReqDTO item : list){
                mapper.createPlan(item);
            }
            sqlSession.commit();
            List<BatchResult> batchResults = sqlSession.flushStatements();
            sqlSession.clearCache();
            sqlSession.close();
            batchResults.clear();
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }
}
