package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.CrewAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewAttentionResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.CrewAttentionMapper;
import com.wzmtr.dom.service.vehicle.CrewAttentionService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-乘务中心注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@Service
public class CrewAttentionServiceImpl implements CrewAttentionService {

    @Autowired
    private CrewAttentionMapper crewAttentionMapper;

    @Override
    public Page<CrewAttentionResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewAttentionMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public CrewAttentionResDTO detail(String id) {
        return crewAttentionMapper.detail(id);
    }

    @Override
    public void add(CrewAttentionReqDTO crewAttentionReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = crewAttentionMapper.selectIsExist(crewAttentionReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期乘务中心注意事项数据已存在，无法重复新增");
        }
        crewAttentionReqDTO.setId(TokenUtils.getUuId());
        crewAttentionReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewAttentionMapper.add(crewAttentionReqDTO);
    }

    @Override
    public void modify(CrewAttentionReqDTO crewAttentionReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = crewAttentionMapper.selectIsExist(crewAttentionReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        crewAttentionReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewAttentionMapper.modify(crewAttentionReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewAttentionMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
