package com.wzmtr.dom.impl.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzmtr.dom.constant.CommonConstants;
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


    @Value("${open-api.csm.unsaturationConstruct}")
    private String unsaturationConstructApi;

    @Override
    public List<UnsaturationConstructResDTO> getUnsaturationConstruct(String startTime,String endTime) {
        JSONObject res = JSONObject.parseObject(HttpUtils.doGet(unsaturationConstructApi+"?planBeginTime="+startTime+"&planEndTime="+endTime,null), JSONObject.class);
        if(Objects.isNull(res)){
            return null;
        }
        List<UnsaturationConstructResDTO> list = res.getJSONArray(CommonConstants.API_RES_DATA).toJavaList(UnsaturationConstructResDTO.class);
        return list;
    }
}
