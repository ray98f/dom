package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.traffic.OnewaySaleMapper;
import com.wzmtr.dom.service.traffic.OnewaySaleService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.StreamUtil;
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
public class OnewaySaleServiceImpl implements OnewaySaleService {
    @Autowired
    private OnewaySaleMapper onewaySaleMapper;

    @Override
    public OnewaySaleDetailResDTO detail(SidReqDTO reqDTO) {
        String id = reqDTO.getId();
        TrafficOnewaySaleDO trafficOnewaySaleDO = onewaySaleMapper.selectById(id);
        return OnewaySaleDetailResDTO.buildRes(trafficOnewaySaleDO);
    }

    @Override
    public void add(OnewaySaleAddReqDTO reqDTO) {
        TrafficOnewaySaleDO onewaySaleDO = reqDTO.toDO(reqDTO);
        onewaySaleDO.setId(TokenUtils.getUuId());
        onewaySaleDO.setCreateBy(TokenUtils.getCurrentPersonId());
        onewaySaleDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        onewaySaleMapper.insert(onewaySaleDO);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, OnewaySaleAddReqDTO passengerRecordReqDTO) {
        TrafficOnewaySaleDO onewaySaleDO = passengerRecordReqDTO.toDO(passengerRecordReqDTO);
        onewaySaleMapper.update(onewaySaleDO,new UpdateWrapper<TrafficOnewaySaleDO>().eq("id",onewaySaleDO.getId()));
    }

    @Override
    public Page<OnewaySaleListResDTO> list(OnewaySaleListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<TrafficOnewaySaleDO> list = onewaySaleMapper.list(reqDTO.of(), reqDTO);
        List<TrafficOnewaySaleDO> records = list.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        List<OnewaySaleListResDTO> result = StreamUtil.map(records, trafficOnewaySaleDO -> BeanUtils.convert(trafficOnewaySaleDO, OnewaySaleListResDTO.class));
        Page<OnewaySaleListResDTO> page = new Page<>();
        page.setTotal(list.getTotal());
        page.setRecords(result);
        page.setSize(list.getSize());
        return page;
    }

    @Override
    public OnewaySaleDetailResDTO acc(String date) {
        // todo acc
        return null;
    }
}
