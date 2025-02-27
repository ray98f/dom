package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.vehicle.CrewBusinessReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewBusinessResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.CrewBusinessMapper;
import com.wzmtr.dom.service.vehicle.CrewBusinessService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-乘务中心业务情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@Service
public class CrewBusinessServiceImpl implements CrewBusinessService {

    @Autowired
    private CrewBusinessMapper crewBusinessMapper;

    @Override
    public Page<CrewBusinessResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewBusinessMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public CrewBusinessResDTO detail(String id) {
        return crewBusinessMapper.detail(id);
    }

    @Override
    public void add(CrewBusinessReqDTO crewBusinessReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = crewBusinessMapper.selectIsExist(crewBusinessReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期乘务中心业务情况数据已存在，无法重复新增");
        }
        crewBusinessReqDTO.setId(TokenUtils.getUuId());
        crewBusinessReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewBusinessMapper.add(crewBusinessReqDTO);
    }

    @Override
    public void modify(CrewBusinessReqDTO crewBusinessReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = crewBusinessMapper.selectIsExist(crewBusinessReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        crewBusinessReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewBusinessMapper.modify(crewBusinessReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewBusinessMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
