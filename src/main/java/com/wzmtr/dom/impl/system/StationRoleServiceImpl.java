package com.wzmtr.dom.impl.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.system.StationRoleReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.system.StationRoleMapper;
import com.wzmtr.dom.service.system.StationRoleService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统参数-审核站权限管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Service
public class StationRoleServiceImpl implements StationRoleService {

    @Autowired
    private StationRoleMapper stationRoleMapper;

    @Override
    public Page<StationRoleResDTO> page(PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return stationRoleMapper.page(pageReqDTO.of());
    }

    @Override
    public StationRoleResDTO detail(String id) {
        return stationRoleMapper.detail(id);
    }

    @Override
    public void add(StationRoleReqDTO stationRoleReqDTO) {
        stationRoleReqDTO.setId(TokenUtils.getUuId());
        stationRoleReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());

        Integer result = stationRoleMapper.checkExist(stationRoleReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "本月的审核站数据已存在，无法重复新增");
        }

        stationRoleMapper.add(stationRoleReqDTO);
    }

    @Override
    public void modify(StationRoleReqDTO stationRoleReqDTO) {
        stationRoleReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        stationRoleMapper.modify(stationRoleReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            stationRoleMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
