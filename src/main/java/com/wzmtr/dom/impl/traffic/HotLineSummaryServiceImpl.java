package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dataobject.traffic.IncomeRecordDO;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineSummaryDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeListReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.traffic.HotLineSummaryMapper;
import com.wzmtr.dom.mapper.traffic.IncomeRecordMapper;
import com.wzmtr.dom.service.traffic.HotLineSummaryService;
import com.wzmtr.dom.service.traffic.IncomeService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class HotLineSummaryServiceImpl implements HotLineSummaryService {
    @Autowired
    private HotLineSummaryMapper hotLineSummaryMapper;

    @Override
    public HotLineSummaryDetailResDTO detail(SidReqDTO reqDTO) {
        TrafficHotlineSummaryDO trafficHotlineSummaryDO = hotLineSummaryMapper.selectById(reqDTO.getId());
        return BeanUtils.convert(trafficHotlineSummaryDO,HotLineSummaryDetailResDTO.class);
    }

    @Override
    public void add(HotLineSummaryAddReqDTO reqDTO) {
        TrafficHotlineSummaryDO trafficHotlineSummaryDO = reqDTO.toDO(reqDTO);
        trafficHotlineSummaryDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineSummaryDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        hotLineSummaryMapper.insert(trafficHotlineSummaryDO);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineSummaryAddReqDTO reqDTO) {
        String id = Assert.notNull(reqDTO.getId(), "id can not be null");
        TrafficHotlineSummaryDO now = hotLineSummaryMapper.selectById(id);
        TrafficHotlineSummaryDO incomeRecordDO = reqDTO.toDO(reqDTO);
        incomeRecordDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        incomeRecordDO.setVersion(String.valueOf(Integer.parseInt(now.getVersion()) + 1));
        hotLineSummaryMapper.updateById(incomeRecordDO);
    }

    @Override
    public Page<HotLineSummaryListResDTO> list(HotLineSummaryListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(),reqDTO.getPageSize());
        Page<HotLineSummaryListResDTO> list = hotLineSummaryMapper.list(reqDTO.of(), reqDTO);
        if (CollectionUtil.isEmpty(list.getRecords())){
            return new Page<>();
        }
        return list;
    }

    @Override
    public HotLineSummaryDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }


}
