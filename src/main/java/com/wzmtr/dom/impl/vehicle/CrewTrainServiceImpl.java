package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.CrewTrainReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewTrainResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.CrewTrainMapper;
import com.wzmtr.dom.service.vehicle.CrewTrainService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-乘务中心班组培训情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@Service
public class CrewTrainServiceImpl implements CrewTrainService {

    @Autowired
    private CrewTrainMapper crewTrainMapper;

    @Override
    public Page<CrewTrainResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return crewTrainMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public CrewTrainResDTO detail(String id) {
        return crewTrainMapper.detail(id);
    }

    @Override
    public void add(CrewTrainReqDTO crewTrainReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = crewTrainMapper.selectIsExist(crewTrainReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期乘务中心班组培训情况数据已存在，无法重复新增");
        }
        crewTrainReqDTO.setId(TokenUtils.getUuId());
        crewTrainReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        crewTrainMapper.add(crewTrainReqDTO);
    }

    @Override
    public void modify(CrewTrainReqDTO crewTrainReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = crewTrainMapper.selectIsExist(crewTrainReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        crewTrainReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        crewTrainMapper.modify(crewTrainReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            crewTrainMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
