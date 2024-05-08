package com.wzmtr.dom.impl.operate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.operate.DebugInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.DebugRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.DebugInfoResDTO;
import com.wzmtr.dom.dto.res.operate.DebugRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.DebugMapper;
import com.wzmtr.dom.service.operate.DebugService;
import com.wzmtr.dom.utils.HttpUtils;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 运营-调试情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Service
public class DebugServiceImpl implements DebugService {

    @Value("${open-api.csm.constructPlan}")
    private String constructPlanApi;

    @Autowired
    private DebugMapper debugMapper;

    @Override
    public Page<DebugRecordResDTO> recordPage(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return debugMapper.recordPage(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public Page<DebugInfoResDTO> infoPage(String id, String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return debugMapper.infoPage(pageReqDTO.of(), id, dataType, startDate, endDate);
    }

    @Override
    public void addRecord(DebugRecordReqDTO debugRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = debugMapper.selectRecordIsExist(debugRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期调试情况记录数据已存在，无法重复新增");
        }
        debugRecordReqDTO.setId(TokenUtils.getUuId());
        debugRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        debugMapper.addRecord(debugRecordReqDTO);
    }

    @Override
    public void addInfo(DebugInfoReqDTO debugInfoReqDTO) {
        debugInfoReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        if (StringUtils.isNotEmpty(debugInfoReqDTO.getCsmList())) {
            debugMapper.addInfo(debugInfoReqDTO);
        }
        // 记录数量增长
        debugMapper.incrementRecord(debugInfoReqDTO.getRecordId(), debugInfoReqDTO.getCsmList().size(), debugInfoReqDTO.getDataType());
    }

    @Override
    public void modifyRecord(DebugRecordReqDTO debugRecordReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = debugMapper.selectRecordIsExist(debugRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        debugRecordReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        debugMapper.modifyRecord(debugRecordReqDTO);
    }

    @Override
    public void modifyInfo(DebugInfoReqDTO debugInfoReqDTO) {
        debugInfoReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        debugMapper.modifyInfo(debugInfoReqDTO);
    }

    @Override
    public void deleteRecord(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            debugMapper.deleteRecord(ids, TokenUtils.getCurrentPersonId());
            debugMapper.deleteInfo(ids, null, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void deleteInfo(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            DebugInfoResDTO res = debugMapper.getInfoDetail(ids.get(0));
            debugMapper.deleteInfo(null, ids, TokenUtils.getCurrentPersonId());
            if (StringUtils.isNotNull(res)) {
                // 记录数量减少
                debugMapper.incrementRecord(res.getRecordId(), -ids.size(), res.getDataType());
            }
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
        list.add(JSONObject.parseObject(test1,ConstructPlanResDTO.class));
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<ConstructPlanResDTO> page = new Page<>();
        page.setRecords(list);
        page.setCurrent(1);
        page.setPages(1);
        page.setTotal(1);
        page.setSize(10);
        return page;
    }

}
