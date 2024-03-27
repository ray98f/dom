package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineSummaryDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.mapper.traffic.HotLineSummaryMapper;
import com.wzmtr.dom.service.traffic.HotLineSummaryService;
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
public class HotLineSummaryServiceImpl implements HotLineSummaryService {
    @Autowired
    private HotLineSummaryMapper hotLineSummaryMapper;
    @Autowired
    private HotLineImportantMapper hotLineImportantMapper;
    @Override
    public HotLineSummaryDetailResDTO detail(SidReqDTO reqDTO) {
        TrafficHotlineSummaryDO trafficHotlineSummaryDO = hotLineSummaryMapper.selectById(reqDTO.getId());
        return BeanUtils.convert(trafficHotlineSummaryDO,HotLineSummaryDetailResDTO.class);
    }

    @Override
    public void add(HotLineSummaryAddReqDTO reqDTO) {
        TrafficHotlineSummaryDO trafficHotlineSummaryDO = reqDTO.toDO(reqDTO);
        trafficHotlineSummaryDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineSummaryDO.setId(TokenUtils.getUuId());
        trafficHotlineSummaryDO.setDelFlag("0");
        trafficHotlineSummaryDO.setCreateDate(DateUtils.currentDate());
        trafficHotlineSummaryDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineSummaryDO.setVersion("0");
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
        List<HotLineSummaryListResDTO> records = list.getRecords();
        if (CollectionUtil.isEmpty(records)){
            return new Page<>();
        }
        records.forEach(a->{
            HotLineSummaryListReqDTO req = new HotLineSummaryListReqDTO();
            req.setStartDate(DateUtils.dateTime(a.getStartDate()));
            req.setEndDate(DateUtils.dateTime(a.getEndDate()));
            req.setDataType(a.getDataType());
            List<HotLineImportantListResDTO> list1 = hotLineImportantMapper.list(req);
            //查日期内是否存在重要内容数据
            if (CollectionUtil.isNotEmpty(list1)){
                a.setHotLineImportant(list1);
            }
        });
        return list;
    }

    @Override
    public HotLineSummaryDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }


}
