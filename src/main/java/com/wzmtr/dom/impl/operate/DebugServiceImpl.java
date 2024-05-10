package com.wzmtr.dom.impl.operate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.controller.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.req.operate.DebugInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.DebugRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.DebugInfoResDTO;
import com.wzmtr.dom.dto.res.operate.DebugRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.DebugMapper;
import com.wzmtr.dom.service.common.ThirdService;
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
    private ThirdService thirdService;

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
        OpenConstructPlanReqDTO req = OpenConstructPlanReqDTO.builder()
                .planbeginTime(startDate)
                .planendTime(endDate)
                .workType(CommonConstants.CONSTRUCT_DEBUG)
                .page(pageReqDTO.getPageNo())
                .limit(pageReqDTO.getPageSize())
                .build();

        return thirdService.getCsmConstructPlan(req);
    }

}
