package com.wzmtr.dom.impl.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dto.req.system.DailyStatisticsReqDTO;
import com.wzmtr.dom.dto.res.system.DailyStatisticsResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.system.DailyStatisticsMapper;
import com.wzmtr.dom.service.system.DailyStatisticsService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统参数-日报统计参数设置管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Service
public class DailyStatisticsServiceImpl implements DailyStatisticsService {

    @Autowired
    private DailyStatisticsMapper dailyStatisticsMapper;

    @Override
    public Page<DailyStatisticsResDTO> page(PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return dailyStatisticsMapper.page(pageReqDTO.of());
    }

    @Override
    public DailyStatisticsResDTO detail(String id) {
        return dailyStatisticsMapper.detail(id);
    }

    @Override
    public void add(DailyStatisticsReqDTO dailyStatisticsReqDTO) {
        dailyStatisticsReqDTO.setId(TokenUtils.getUuId());
        dailyStatisticsReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        dailyStatisticsMapper.add(dailyStatisticsReqDTO);
    }

    @Override
    public void modify(DailyStatisticsReqDTO dailyStatisticsReqDTO) {
        dailyStatisticsReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        dailyStatisticsMapper.modify(dailyStatisticsReqDTO);
    }

    @Override
    public void delete(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            dailyStatisticsMapper.delete(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
