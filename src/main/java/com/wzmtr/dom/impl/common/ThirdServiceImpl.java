package com.wzmtr.dom.impl.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.controller.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.res.common.OpenConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.PlanStatisticsResDTO;
import com.wzmtr.dom.dto.res.operate.UnsaturationConstructResDTO;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructPlanResDTO;
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

    @Value("${open-api.csm.unsaturationConstruct}")
    private String unsaturationConstructApi;

    @Value("${open-api.csm.planStatistics}")
    private String planStatisticsApi;
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
                .build();
    }
}
