package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.operate.SecurityCleaningReqDTO;
import com.wzmtr.dom.dto.res.operate.SecurityCleaningResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.SecurityCleaningMapper;
import com.wzmtr.dom.service.operate.SecurityCleaningService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运营-安检及保洁情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Service
public class SecurityCleaningServiceImpl implements SecurityCleaningService {

    @Autowired
    private SecurityCleaningMapper securityCleaningMapper;

    @Override
    public Page<SecurityCleaningResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return securityCleaningMapper.page(pageReqDTO.of(), startDate, endDate, dataType);
    }

    @Override
    public SecurityCleaningResDTO detail(String id) {
        return securityCleaningMapper.detail(id);
    }

    @Override
    public void add(SecurityCleaningReqDTO securityCleaningReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = securityCleaningMapper.selectIsExist(securityCleaningReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期安检及保洁情况数据已存在，无法重复新增");
        }
        securityCleaningReqDTO.setId(TokenUtils.getUuId());
        securityCleaningReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        securityCleaningMapper.add(securityCleaningReqDTO);
    }

    @Override
    public void modify(SecurityCleaningReqDTO securityCleaningReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = securityCleaningMapper.selectIsExist(securityCleaningReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        securityCleaningReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        securityCleaningMapper.modify(securityCleaningReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            securityCleaningMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
