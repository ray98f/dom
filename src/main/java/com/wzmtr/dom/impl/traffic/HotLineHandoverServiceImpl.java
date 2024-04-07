package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineHandoverDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HandoverAddData;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.HotLineHandoverMapper;
import com.wzmtr.dom.service.traffic.HotLineHandoverService;
import com.wzmtr.dom.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class HotLineHandoverServiceImpl implements HotLineHandoverService {
    @Autowired
    private HotLineHandoverMapper hotLineHandoverMapper;

    @Override
    public Page<HotLineHandoverListResDTO> list(HotLineHandoverListReqDTO reqDTO) {
        Page<HotLineHandoverListResDTO> page = hotLineHandoverMapper.list(reqDTO.of(), reqDTO);
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return new Page<>();
        }
        return page;
    }

    @Override
    public List<HotLineHandoverDetailResDTO> detail(String date,String dataType) {
        // 查日期内所有数据
        Assert.notNull(date, "参数缺失");
        return hotLineHandoverMapper.selectListByDate(date,dataType);
    }

    @Override
    public HotLineHandoverDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }

    @Override
    public void add(HotLineHandoverAddReqDTO reqDTO) {
        Assert.isFalse(hotLineHandoverMapper.selectIsExist(reqDTO) > 0, "所属日期数据已存在，无法重复新增");
        List<HandoverAddData> addData = reqDTO.getDataList();
        if (CollectionUtil.isEmpty(addData)) {
            // 新建一条当天的数据
            TrafficHotlineHandoverDO convert = BeanUtils.convert(reqDTO, TrafficHotlineHandoverDO.class);
            convert.setId(TokenUtils.getUuId());
            convert.setDelFlag("0");
            convert.setCreateDate(DateUtils.currentDate());
            convert.setDataDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(reqDTO.getStartDate()));
            convert.setStartDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(reqDTO.getStartDate()));
            convert.setEndDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(reqDTO.getEndDate()));
            convert.setCreateBy(TokenUtils.getCurrentPersonId());
            convert.setVersion("0");
        } else {
            addData.forEach(x -> {
                TrafficHotlineHandoverDO convert = BeanUtils.convert(x, TrafficHotlineHandoverDO.class);
                convert.setId(TokenUtils.getUuId());
                convert.setDelFlag("0");
                convert.setCreateDate(DateUtils.currentDate());
                convert.setDataDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(reqDTO.getDataDate()));
                convert.setStartDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(reqDTO.getStartDate()));
                convert.setEndDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(reqDTO.getEndDate()));
                convert.setCreateBy(TokenUtils.getCurrentPersonId());
                convert.setVersion("0");
                hotLineHandoverMapper.insert(convert);
            });
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineHandoverAddReqDTO req) {
        List<HandoverAddData> addData = Assert.notNull(req.getDataList(), "参数缺失");
        addData.forEach(x -> {
            TrafficHotlineHandoverDO convert = BeanUtils.convert(x, TrafficHotlineHandoverDO.class);
            if (StringUtils.isNotEmpty(convert.getId())) {
                convert.setUpdateBy(TokenUtils.getCurrentPersonId());
                convert.setUpdateDate(DateUtils.currentDate());
                convert.setDataDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(req.getStartDate()));
                convert.setStartDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(req.getStartDate()));
                convert.setEndDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(req.getEndDate()));
                convert.setVersion(StringUtils.incrementToString(convert.getVersion()));
                hotLineHandoverMapper.updateById(convert);
            } else {
                convert.setId(TokenUtils.getUuId());
                convert.setDelFlag("0");
                convert.setCreateDate(DateUtils.currentDate());
                convert.setDataDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(req.getStartDate()));
                convert.setStartDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(req.getStartDate()));
                convert.setEndDate(DateUtils.formatDateYYYY_MM_DD_HH_MM_SS(req.getEndDate()));
                convert.setCreateBy(TokenUtils.getCurrentPersonId());
                convert.setVersion("0");
                hotLineHandoverMapper.insert(convert);
            }
        });
    }
}
