package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.vehicle.CrewDrillReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewDrillResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.CrewDrillMapper;
import com.wzmtr.dom.service.vehicle.CrewDrillService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-乘务中心演练情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@Service
public class CrewDrillServiceImpl implements CrewDrillService {

    @Autowired
    private CrewDrillMapper crewDrillMapper;

    @Override
    public Page<CrewDrillResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewDrillMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public CrewDrillResDTO detail(String id) {
        return crewDrillMapper.detail(id);
    }

    @Override
    public void add(CrewDrillReqDTO crewDrillReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = crewDrillMapper.selectIsExist(crewDrillReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期乘务中心演练情况数据已存在，无法重复新增");
        }
        crewDrillReqDTO.setId(TokenUtils.getUuId());
        crewDrillReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewDrillMapper.add(crewDrillReqDTO);
    }

    @Override
    public void modify(CrewDrillReqDTO crewDrillReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = crewDrillMapper.selectIsExist(crewDrillReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        crewDrillReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewDrillMapper.modify(crewDrillReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewDrillMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
