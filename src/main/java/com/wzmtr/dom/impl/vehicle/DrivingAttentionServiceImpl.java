package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.DrivingAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingAttentionResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DrivingAttentionMapper;
import com.wzmtr.dom.service.vehicle.DrivingAttentionService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-行车注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Service
public class DrivingAttentionServiceImpl implements DrivingAttentionService {

    @Autowired
    private DrivingAttentionMapper dispatchHandoverMapper;

    @Override
    public Page<DrivingAttentionResDTO> page(String startTime, String endTime, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dispatchHandoverMapper.page(pageReqDTO.of(), startTime, endTime);
    }

    @Override
    public DrivingAttentionResDTO detail(String id) {
        return dispatchHandoverMapper.detail(id);
    }

    @Override
    public void add(DrivingAttentionReqDTO drivingAttentionReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = dispatchHandoverMapper.selectIsExist(drivingAttentionReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期行车注意事项数据已存在，无法重复新增");
        }
        drivingAttentionReqDTO.setId(TokenUtils.getUuId());
        drivingAttentionReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        dispatchHandoverMapper.add(drivingAttentionReqDTO);
    }

    @Override
    public void modify(DrivingAttentionReqDTO drivingAttentionReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = dispatchHandoverMapper.selectIsExist(drivingAttentionReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        drivingAttentionReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        dispatchHandoverMapper.modify(drivingAttentionReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dispatchHandoverMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
