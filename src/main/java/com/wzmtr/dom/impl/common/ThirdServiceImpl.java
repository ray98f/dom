package com.wzmtr.dom.impl.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.common.OpenDispatchOrderReqDTO;
import com.wzmtr.dom.dto.res.common.OpenConstructPlanResDTO;
import com.wzmtr.dom.dto.res.common.OpenDepotStatisticsRes;
import com.wzmtr.dom.dto.res.common.OpenDispatchOrderRes;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.PlanStatisticsResDTO;
import com.wzmtr.dom.dto.res.operate.UnsaturationConstructResDTO;
import com.wzmtr.dom.enums.PlanStatusEnum;
import com.wzmtr.dom.service.common.ThirdService;
import com.wzmtr.dom.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/8 14:10
 */
@Service
@Slf4j
public class ThirdServiceImpl implements ThirdService {

    @Value("${open-api.csm.constructPlan}")
    private String constructPlanApi;

    @Value("${open-api.csm.importantConstruct}")
    private String importantConstruct;

    @Value("${open-api.csm.unsaturationConstruct}")
    private String unsaturationConstructApi;

    @Value("${open-api.csm.planStatistics}")
    private String planStatisticsApi;

    @Value("${open-api.csm.dispatchOrder}")
    private String dispatchOrderApi;

    @Value("${open-api.odm.depotStatistics}")
    private String depotStatisticsApi;
    @Override
    public List<UnsaturationConstructResDTO> getUnsaturationConstruct(String startTime,String endTime) {
        JSONObject res = JSONObject.parseObject(HttpUtils.doGet(unsaturationConstructApi+"?planBeginTime="+startTime+"&planEndTime="+endTime,null), JSONObject.class);
        if(Objects.isNull(res)){
            return null;
        }
        return res.getJSONArray(CommonConstants.API_RES_DATA).toJavaList(UnsaturationConstructResDTO.class);
    }

    @Override
    public List<PlanStatisticsResDTO> getPlanStatistics(String startTime, String endTime) {
        JSONObject res = JSONObject.parseObject(HttpUtils.doGet(planStatisticsApi+"?planBeginTime="+startTime+"&planEndTime="+endTime,null), JSONObject.class);
        if(Objects.nonNull(res)){
            List<PlanStatisticsResDTO> result = res.getJSONArray(CommonConstants.API_RES_DATA).toJavaList(PlanStatisticsResDTO.class);
            return result;
        }
        return null;
    }

    @Override
    public Page<ConstructPlanResDTO> getCsmConstructPlan(OpenConstructPlanReqDTO constructPlanReqDTO) {

        String reqData = JSONObject.toJSONString(constructPlanReqDTO);
        JSONObject res = JSONObject.parseObject(HttpUtils.doPost(constructPlanApi, reqData, null), JSONObject.class);
        List<OpenConstructPlanResDTO> openList = JSONArray.parseArray(res.getJSONObject(
                CommonConstants.API_RES_DATA).getJSONArray(CommonConstants.API_RES_LIST).toJSONString(),
                OpenConstructPlanResDTO.class);
        List<ConstructPlanResDTO> list = new ArrayList<>();
        PageHelper.startPage(constructPlanReqDTO.getPage(), constructPlanReqDTO.getLimit());
        Page<ConstructPlanResDTO> page = new Page<>();
        for(OpenConstructPlanResDTO o: openList){
            list.add(convertDTO(o));
        }
        page.setRecords(list);
        page.setCurrent(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("pageNum"));
        page.setPages(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("pages"));
        page.setTotal(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("total"));
        page.setSize(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("size"));
        return page;
    }

    @Override
    public Page<ConstructPlanResDTO> getCsmImportantConstructPlan(OpenConstructPlanReqDTO constructPlanReqDTO) {
        PageHelper.startPage(constructPlanReqDTO.getPage(), constructPlanReqDTO.getLimit());
        Page<ConstructPlanResDTO> page = new Page<>();
        List<ConstructPlanResDTO> list = new ArrayList<>();
        String reqData = JSONObject.toJSONString(constructPlanReqDTO);
        JSONObject res = JSONObject.parseObject(HttpUtils.doPost(importantConstruct, reqData, null), JSONObject.class);
        if(res != null){
            List<OpenConstructPlanResDTO> openList = JSONArray.parseArray(res.getJSONObject(
                    CommonConstants.API_RES_DATA).getJSONArray(CommonConstants.API_RES_LIST).toJSONString(),
                    OpenConstructPlanResDTO.class);


            for(OpenConstructPlanResDTO o: openList){
                list.add(convertDTO(o));
            }
            page.setRecords(list);
            page.setCurrent(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("pageNum"));
            page.setPages(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("pages"));
            page.setTotal(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("total"));
            page.setSize(res.getJSONObject(CommonConstants.API_RES_DATA).getInteger("size"));
            return page;
        }
        page.setRecords(list);
        page.setCurrent(constructPlanReqDTO.getPage());
        page.setPages(1);
        page.setTotal(0);
        page.setSize(constructPlanReqDTO.getLimit());
        return page;
    }

    @Override
    public List<OpenDispatchOrderRes> getCsmDispatchOrder(OpenDispatchOrderReqDTO dispatchOrderReqDTO) {
        String reqData = JSONObject.toJSONString(dispatchOrderReqDTO);
        JSONObject res = JSONObject.parseObject(HttpUtils.doPost(dispatchOrderApi, reqData, null), JSONObject.class);

        List<OpenDispatchOrderRes> openList = res.getJSONArray(CommonConstants.API_RES_DATA).toJavaList(OpenDispatchOrderRes.class);
        return openList;
    }

    @Override
    public OpenDepotStatisticsRes getOdmDepotStatistics(String date) {

        JSONObject res = JSONObject.parseObject(HttpUtils.doGet(depotStatisticsApi+"?date="+date,null), JSONObject.class);
        if(Objects.nonNull(res)){
            OpenDepotStatisticsRes result = JSON.parseObject(res.getJSONObject(CommonConstants.API_RES_DATA).toJSONString(),OpenDepotStatisticsRes.class);
            return result;
        }
        return null;
    }


    private ConstructPlanResDTO convertDTO(OpenConstructPlanResDTO openConstructPlan){

        return ConstructPlanResDTO.builder()
                .constructPlanId(openConstructPlan.getConstructplanId())
                .workconcentId(openConstructPlan.getWorkconcentId())
                .workCode(openConstructPlan.getWorkCode())
                .workName(openConstructPlan.getWorkName())
                .workDept(openConstructPlan.getWorkdeptName())
                .workArea(openConstructPlan.getWorkareaDesc())
                .workDetail(openConstructPlan.getWorkName())
                .powerReq(openConstructPlan.getPowerRequest())
                .workType(openConstructPlan.getWorkType())
                .planStatus(PlanStatusEnum.getLabelByValue(openConstructPlan.getPlanStatus()))
                .planBeginTime(openConstructPlan.getPlanbeginTime())
                .planEndTime(openConstructPlan.getPlanendTime())
                .build();
    }
}
