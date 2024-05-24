package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.vehicle.OtherRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.OtherRecordResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.OtherRecordMapper;
import com.wzmtr.dom.service.vehicle.OtherRecordService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-其他情况说明
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Service
public class OtherRecordServiceImpl implements OtherRecordService {

    @Autowired
    private OtherRecordMapper otherRecordMapper;

    @Override
    public Page<OtherRecordResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return otherRecordMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public OtherRecordResDTO detail(String id) {
        return otherRecordMapper.detail(id);
    }

    @Override
    public void add(OtherRecordReqDTO otherRecordReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = otherRecordMapper.selectIsExist(otherRecordReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期其他情况说明数据已存在，无法重复新增");
        }
        otherRecordReqDTO.setId(TokenUtils.getUuId());
        otherRecordReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        otherRecordMapper.add(otherRecordReqDTO);
    }

    @Override
    public void modify(OtherRecordReqDTO otherRecordReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = otherRecordMapper.selectIsExist(otherRecordReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        otherRecordReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        otherRecordMapper.modify(otherRecordReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            otherRecordMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
