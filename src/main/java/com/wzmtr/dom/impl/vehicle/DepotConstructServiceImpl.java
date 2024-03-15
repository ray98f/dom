package com.wzmtr.dom.impl.vehicle;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanBatchReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructPlanResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DepotConstructMapper;
import com.wzmtr.dom.mapper.vehicle.IndicatorMapper;
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
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DepotConstructMapper depotConstructMapper;

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Override
    public Page<DepotConstructRecordResDTO> list(String depotCode,String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
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
    public Page<DepotConstructPlanResDTO> getCsmConstructPlan(String depotCode, String startDate, String endDate, PageReqDTO pageReqDTO) {
        //TODO 调取施工调度计划
        //JSONObject.toJSONString(convertDto(req));
        String reqData = "{}";
        JSONObject res = JSONObject.parseObject(HttpUtils.doPost(constructPlanApi, reqData, null), JSONObject.class);
        /*List<DepotConstructPlanResDTO> list = JSONArray.parseArray(res.getJSONObject(
                CommonConstants.API_RES_DATA).getJSONArray(CommonConstants.API_RES_LIST).toJSONString(),
                DepotConstructPlanResDTO.class);
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<DepotConstructPlanResDTO> page = new Page<>();
        page.setRecords(list);
        page.setCurrent(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("pageNum"));
        page.setPages(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("pages"));
        page.setTotal(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("total"));
        page.setSize(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("size"));*/
        //TODO TEST
        String test1 = "{\n" +
                "            \"constructPlanId\":\"d4ef94232cd44af69c09d9a69cf9a029\",\n" +
                "            \"workType\":\"A1\",\n" +
                "            \"workconcentId\":\"7ebc015e2d64430a819ab82226b99e8a\",\n" +
                "            \"workCode\":\"S1A104-02\",\n" +
                "            \"workName\":\"工程车动态验收\",\n" +
                "            \"workDept\":\"中铁通轨道运营有限公司\",\n" +
                "            \"workArea\":\"正线:动车南站-新桥站\",\n" +
                "            \"workDetail\":\"123\",\n" +
                "            \"powerReq\":\"正线分区：1A2带电\"\n" +
                "        }";

        List<DepotConstructPlanResDTO> list = new ArrayList<>();
        list.add(JSONObject.parseObject(test1,DepotConstructPlanResDTO.class));
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<DepotConstructPlanResDTO> page = new Page<>();
        page.setRecords(list);
        page.setCurrent(1);
        page.setPages(1);
        page.setTotal(1);
        page.setSize(10);
        return page;
    }

    @Override
    public Page<DepotConstructPlanResDTO> planList(String depotCode, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
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
