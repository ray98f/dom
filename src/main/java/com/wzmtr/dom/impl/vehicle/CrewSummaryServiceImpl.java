package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.vehicle.CrewSummaryReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewSummaryResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.CrewSummaryMapper;
import com.wzmtr.dom.service.vehicle.CrewSummaryService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-乘务中心情况总结
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@Service
public class CrewSummaryServiceImpl implements CrewSummaryService {

    @Autowired
    private CrewSummaryMapper crewSummaryMapper;

    @Override
    public Page<CrewSummaryResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewSummaryMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public CrewSummaryResDTO detail(String id) {
        return crewSummaryMapper.detail(id);
    }

    @Override
    public void add(CrewSummaryReqDTO crewSummaryReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = crewSummaryMapper.selectIsExist(crewSummaryReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期乘务中心情况总结数据已存在，无法重复新增");
        }
        crewSummaryReqDTO.setId(TokenUtils.getUuId());
        crewSummaryReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewSummaryMapper.add(crewSummaryReqDTO);
    }

    @Override
    public void modify(CrewSummaryReqDTO crewSummaryReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = crewSummaryMapper.selectIsExist(crewSummaryReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        crewSummaryReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewSummaryMapper.modify(crewSummaryReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewSummaryMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
