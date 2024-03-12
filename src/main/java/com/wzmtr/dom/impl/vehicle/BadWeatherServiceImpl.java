package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.BadWeatherReqDTO;
import com.wzmtr.dom.dto.res.vehicle.BadWeatherResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.BadWeatherMapper;
import com.wzmtr.dom.service.vehicle.BadWeatherService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-恶劣天气组织
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Service
public class BadWeatherServiceImpl implements BadWeatherService {

    @Autowired
    private BadWeatherMapper badWeatherMapper;

    @Override
    public Page<BadWeatherResDTO> page(String startTime, String endTime, String dataType, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return badWeatherMapper.page(pageReqDTO.of(), startTime, endTime, dataType);
    }

    @Override
    public BadWeatherResDTO detail(String id) {
        return badWeatherMapper.detail(id);
    }

    @Override
    public void add(BadWeatherReqDTO badWeatherReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = badWeatherMapper.selectIsExist(badWeatherReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期恶劣天气组织数据已存在，无法重复新增");
        }
        badWeatherReqDTO.setId(TokenUtils.getUuId());
        badWeatherReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        badWeatherMapper.add(badWeatherReqDTO);
    }

    @Override
    public void modify(BadWeatherReqDTO badWeatherReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = badWeatherMapper.selectIsExist(badWeatherReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        badWeatherReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        badWeatherMapper.modify(badWeatherReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            badWeatherMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
