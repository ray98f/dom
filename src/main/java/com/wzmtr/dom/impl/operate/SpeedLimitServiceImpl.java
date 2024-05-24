package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
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
    private SpeedLimitMapper speedLimitMapper;

    @Override
    public Page<SpeedLimitRecordResDTO> recordPage(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return speedLimitMapper.recordPage(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public Page<SpeedLimitInfoResDTO> infoPage(String id,String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return speedLimitMapper.infoPage(pageReqDTO.of(), startDate, endDate, id);
    }

    @Override
    public void addRecord(SpeedLimitRecordReqDTO speedLimitRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = speedLimitMapper.selectRecordIsExist(speedLimitRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期线路限速情况记录数据已存在，无法重复新增");
        }
        speedLimitRecordReqDTO.setId(TokenUtils.getUuId());
        speedLimitRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        speedLimitRecordReqDTO.setLimitNum(0);
        speedLimitMapper.addRecord(speedLimitRecordReqDTO);
    }

    @Override
    public void addInfo(SpeedLimitInfoReqDTO speedLimitInfoReqDTO) {
        speedLimitInfoReqDTO.setId(TokenUtils.getUuId());
        speedLimitInfoReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        speedLimitMapper.addInfo(speedLimitInfoReqDTO);
        // 记录数量增长
        speedLimitMapper.incrementRecord(speedLimitInfoReqDTO.getRecordId(), 1);
    }

    @Override
    public void modifyRecord(SpeedLimitRecordReqDTO speedLimitRecordReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = speedLimitMapper.selectRecordIsExist(speedLimitRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        speedLimitRecordReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        speedLimitMapper.modifyRecord(speedLimitRecordReqDTO);
    }

    @Override
    public void modifyInfo(SpeedLimitInfoReqDTO speedLimitInfoReqDTO) {
        speedLimitInfoReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        speedLimitMapper.modifyInfo(speedLimitInfoReqDTO);
    }

    @Override
    public void deleteRecord(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            speedLimitMapper.deleteRecord(ids, TokenUtils.getCurrentPersonId());
            speedLimitMapper.deleteInfo(ids, null, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void deleteInfo(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            SpeedLimitInfoResDTO res = speedLimitMapper.getInfoDetail(ids.get(0));
            speedLimitMapper.deleteInfo(null, ids, TokenUtils.getCurrentPersonId());
            if (StringUtils.isNotNull(res)) {
                // 记录数量减少
                speedLimitMapper.incrementRecord(res.getRecordId(), -ids.size());
            }
        }
    }

}
