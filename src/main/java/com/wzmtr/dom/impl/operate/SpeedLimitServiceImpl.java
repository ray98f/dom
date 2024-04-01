package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.operate.SpeedLimitInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.SpeedLimitRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitInfoResDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.SpeedLimitMapper;
import com.wzmtr.dom.service.operate.SpeedLimitService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运营-线路限速情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/01
 */
@Service
public class SpeedLimitServiceImpl implements SpeedLimitService {

    @Autowired
    private SpeedLimitMapper crewDrillMapper;

    @Override
    public Page<SpeedLimitRecordResDTO> recordPage(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewDrillMapper.recordPage(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public Page<SpeedLimitInfoResDTO> infoPage(String id, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewDrillMapper.infoPage(pageReqDTO.of(), id);
    }

    @Override
    public void addRecord(SpeedLimitRecordReqDTO speedLimitRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = crewDrillMapper.selectRecordIsExist(speedLimitRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期线路限速情况记录数据已存在，无法重复新增");
        }
        speedLimitRecordReqDTO.setId(TokenUtils.getUuId());
        speedLimitRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewDrillMapper.addRecord(speedLimitRecordReqDTO);
    }

    @Override
    public void addInfo(SpeedLimitInfoReqDTO speedLimitInfoReqDTO) {
        speedLimitInfoReqDTO.setId(TokenUtils.getUuId());
        speedLimitInfoReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewDrillMapper.addInfo(speedLimitInfoReqDTO);
    }

    @Override
    public void modifyRecord(SpeedLimitRecordReqDTO speedLimitRecordReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = crewDrillMapper.selectRecordIsExist(speedLimitRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        speedLimitRecordReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewDrillMapper.modifyRecord(speedLimitRecordReqDTO);
    }

    @Override
    public void modifyInfo(SpeedLimitInfoReqDTO speedLimitInfoReqDTO) {
        speedLimitInfoReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewDrillMapper.modifyInfo(speedLimitInfoReqDTO);
    }

    @Override
    public void deleteRecord(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewDrillMapper.deleteRecord(ids, TokenUtils.getCurrentPersonId());
            crewDrillMapper.deleteInfo(ids, null, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void deleteInfo(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewDrillMapper.deleteInfo(null, ids, TokenUtils.getCurrentPersonId());
        }
    }

}
