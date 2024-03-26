package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantListReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.mapper.traffic.HotLineSummaryMapper;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.DateUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class HotLineImportantServiceImpl implements HotLineImportantService {
    @Autowired
    private HotLineImportantMapper hotLineImportantMapper;
    @Autowired
    private HotLineSummaryMapper hotLineSummaryMapper;

    @Override
    public HotLineImportantDetailResDTO detail(SidReqDTO reqDTO) {
        TrafficHotlineImportantDO trafficHotlineImportantDO = hotLineImportantMapper.selectById(reqDTO.getId());
        return BeanUtils.convert(trafficHotlineImportantDO, HotLineImportantDetailResDTO.class);
    }

    @Override
    public void add(HotLineImportantAddReqDTO req) {
        Assert.isFalse(hotLineImportantMapper.selectIsExist(req) > 0, "数据已存在，无法重复新增");
        List<HotLineImportantAddDataReqDTO> addDataReqDTOS = Assert.notNull(req.getHotlineImportantList(), "参数缺失");
        List<TrafficHotlineImportantDO> list = Lists.newArrayList();
        addDataReqDTOS.forEach(x -> {
            TrafficHotlineImportantDO convert = BeanUtils.convert(x, TrafficHotlineImportantDO.class);
            convert.setId(TokenUtils.getUuId());
            convert.setDelFlag("0");
            convert.setCreateDate(DateUtils.currentDate());
            convert.setCreateBy(TokenUtils.getCurrentPersonId());
            convert.setVersion("0");
            convert.setDataDate(req.getDataDate());
            convert.setStartDate(req.getStartDate());
            convert.setEndDate(req.getEndDate());
            convert.setDataType(req.getDataType());
            list.add(convert);
        });
        if (CollectionUtil.isNotEmpty(list)) {
            hotLineImportantMapper.insertList(list);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineImportantAddReqDTO reqDTO) {
        List<HotLineImportantAddDataReqDTO> addDataReqDTOS = Assert.notNull(reqDTO.getHotlineImportantList(), "参数缺失");
        addDataReqDTOS.forEach(x -> {
            TrafficHotlineImportantDO convert = BeanUtils.convert(x, TrafficHotlineImportantDO.class);
            convert.setUpdateBy(TokenUtils.getCurrentPersonId());
            convert.setUpdateDate(DateUtils.currentDate());
            hotLineImportantMapper.updateById(convert);
        });
    }

    @Override
    public Page<HotLineSummaryListResDTO> list(HotLineSummaryListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<HotLineSummaryListResDTO> list = hotLineSummaryMapper.list(reqDTO.of(), reqDTO);
        List<HotLineSummaryListResDTO> records = list.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        return list;
    }


    @Override
    public HotLineImportantDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }
}
