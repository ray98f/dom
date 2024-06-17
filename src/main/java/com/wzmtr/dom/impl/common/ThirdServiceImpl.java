package com.wzmtr.dom.impl.common;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.common.OpenDispatchOrderReqDTO;
import com.wzmtr.dom.dto.res.OpenDriverInfoRes;
import com.wzmtr.dom.dto.res.common.*;
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

    @Value("${open-api.eam.faultStatistics}")
    private String faultStatistics;

    @Value("${open-api.eam.trainMile}")
    private String trainMile;

    @Value("${open-api.ocm.driverInfo}")
    private String driverInfoApi;

    @Override
    public List<UnsaturationConstructResDTO> getUnsaturationConstruct(String startTime,String endTime) {
        JSONObject res = JSONObject.parseObject(HttpUtils.doGet(unsaturationConstructApi+"?planBeginTime="+startTime+"&planEndTime="+endTime,null), JSONObject.class);
        if(Objects.isNull(res) || Objects.isNull(res.getJSONArray(CommonConstants.API_RES_DATA))){
            return new ArrayList<>();
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
        return new ArrayList<>();
    }

    @Override
    public Page<ConstructPlanResDTO> getCsmConstructPlan(OpenConstructPlanReqDTO constructPlanReqDTO) {

        String reqData = JSONObject.toJSONString(constructPlanReqDTO);
        JSONObject res = JSONObject.parseObject(HttpUtils.doPost(constructPlanApi, reqData, null), JSONObject.class);
        Page<ConstructPlanResDTO> page = new Page<>();
        if(Objects.isNull(res.getJSONObject(CommonConstants.API_RES_DATA))){
            page.setRecords(null);
            page.setCurrent(1);
            page.setPages(1);
            page.setTotal(0);
            page.setSize(0);
            return page;
        }

        List<OpenConstructPlanResDTO> openList = JSONArray.parseArray(res.getJSONObject(
                CommonConstants.API_RES_DATA).getJSONArray(CommonConstants.API_RES_LIST).toJSONString(),
                OpenConstructPlanResDTO.class);
        List<ConstructPlanResDTO> list = new ArrayList<>();
        PageHelper.startPage(constructPlanReqDTO.getPage(), constructPlanReqDTO.getLimit());

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
            return JSON.parseObject(res.getJSONObject(CommonConstants.API_RES_DATA).toJSONString(),OpenDepotStatisticsRes.class);
        }
        return new OpenDepotStatisticsRes();
    }

    @Override
    public List<OpenFaultStatisticsRes> getEamFaultStatistics(String startTime, String endTime) {
        String condition = "?startTime="+startTime+"&endTime="+endTime;
        String result = HttpUtil.createGet(faultStatistics+condition)
                .execute()
                .body();
        JSONObject res = JSONObject.parseObject(result, JSONObject.class);
        if(Objects.nonNull(res)){
            return res.getJSONArray(CommonConstants.API_RES_DATA).toJavaList(OpenFaultStatisticsRes.class);
        }
        return new ArrayList<>();
    }

    @Override
    public OpenTrainMileRes getEamTrainMile(String day) {
        String result = HttpUtil.createGet(trainMile+"?day="+day)
                .execute()
                .body();
        JSONObject res = JSONObject.parseObject(result, JSONObject.class);
        if(Objects.nonNull(res)){

            return JSONObject.parseObject(res.getJSONObject(CommonConstants.API_RES_DATA).toJSONString(),OpenTrainMileRes.class);
        }
        return OpenTrainMileRes.builder().sumDailyWorkMile(CommonConstants.ZERO).sumDailyMile(CommonConstants.ZERO).build();
    }

    @Override
    public OpenDriverInfoRes getDriverInfo(String date) {
        String result = HttpUtil.createGet(driverInfoApi+"?date="+date)
                .execute()
                .body();
        JSONObject res = JSONObject.parseObject(result, JSONObject.class);
        if(Objects.nonNull(res)){

            return JSONObject.parseObject(res.getJSONObject(CommonConstants.API_RES_DATA).toJSONString(),OpenDriverInfoRes.class);
        }
        return new OpenDriverInfoRes();
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
