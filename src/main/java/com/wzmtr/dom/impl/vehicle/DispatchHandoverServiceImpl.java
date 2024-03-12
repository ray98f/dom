package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.DispatchHandoverReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchHandoverResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.DispatchHandoverMapper;
import com.wzmtr.dom.service.vehicle.DispatchHandoverService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-车场调度员交接班情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Service
public class DispatchHandoverServiceImpl implements DispatchHandoverService {

    @Autowired
    private DispatchHandoverMapper dispatchHandoverMapper;

    @Override
    public Page<DispatchHandoverResDTO> page(String startTime, String endTime, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dispatchHandoverMapper.page(pageReqDTO.of(), startTime, endTime);
    }

    @Override
    public DispatchHandoverResDTO detail(String id) {
        return dispatchHandoverMapper.detail(id);
    }

    @Override
    public void add(DispatchHandoverReqDTO dispatchHandoverReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = dispatchHandoverMapper.selectIsExist(dispatchHandoverReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车场调度员交接班数据已存在，无法重复新增");
        }
        dispatchHandoverReqDTO.setId(TokenUtils.getUuId());
        dispatchHandoverReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        dispatchHandoverMapper.add(dispatchHandoverReqDTO);
    }

    @Override
    public void modify(DispatchHandoverReqDTO dispatchHandoverReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = dispatchHandoverMapper.selectIsExist(dispatchHandoverReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        dispatchHandoverReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        dispatchHandoverMapper.modify(dispatchHandoverReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dispatchHandoverMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
