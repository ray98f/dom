package com.wzmtr.dom.impl.operate;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.res.operate.*;
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

import java.util.ArrayList;
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
    public ConstructRecordResDTO detail(String id, String startDate, String endDate) {
        ConstructRecordResDTO detail = operateConstructMapper.queryInfoById(id, startDate, endDate);

        // TODO 增加不饱和施工数据
        if(CommonConstants.DATA_TYPE_WEEKLY.equals(detail.getDataType()) || CommonConstants.DATA_TYPE_MONTHLY.equals(detail.getDataType())){
            detail.setUnsaturationConstruct(thirdService.getUnsaturationConstruct(DateUtil.formatDate(detail.getStartDate()),
                    DateUtil.formatDate(detail.getEndDate())));
            detail.setRemark(CommonConstants.OPERATE_CONSTRUCT_REMARK_TPL);
        }

        return detail;
    }

    @Override
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
        } else {
            //周报 月报 //TODO 施工概况 REMARK 参数来源未知
            String baseRemark = CommonConstants.OPERATE_CONSTRUCT_REMARK_TPL;

            //constructRecordReqDTO.
        }

        constructRecordReqDTO.setCreateBy(currentLoginUser.getPersonId());
        constructRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        constructRecordReqDTO.setId(TokenUtils.getUuId());
        try {
            operateConstructMapper.add(constructRecordReqDTO);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, ConstructRecordReqDTO constructRecordReqDTO) {
        constructRecordReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = operateConstructMapper.modify(constructRecordReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public Page<ConstructPlanResDTO> getCsmConstructPlan(String startDate, String endDate, PageReqDTO pageReqDTO) {
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

        List<ConstructPlanResDTO> list = new ArrayList<>();
        list.add(JSONObject.parseObject(test1, ConstructPlanResDTO.class));
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<ConstructPlanResDTO> page = new Page<>();
        page.setRecords(list);
        page.setCurrent(1);
        page.setPages(1);
        page.setTotal(1);
        page.setSize(10);
        return page;
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
}
