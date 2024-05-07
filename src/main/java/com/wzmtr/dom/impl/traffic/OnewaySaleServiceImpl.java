package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OneWaySaleDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.OnewaySaleMapper;
import com.wzmtr.dom.service.traffic.OnewaySaleService;
import com.wzmtr.dom.utils.StringUtils;
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
    public OnewaySaleDetailResDTO detail(OneWaySaleDetailReqDTO reqDTO) {
        TrafficOnewaySaleDO trafficOnewaySale = onewaySaleMapper.detail(reqDTO);
        if (StringUtils.isNotNull(trafficOnewaySale)) {
            return OnewaySaleDetailResDTO.buildRes(trafficOnewaySale);
        }
        return new OnewaySaleDetailResDTO();
    }

    @Override
    public void add(OnewaySaleAddReqDTO reqDTO) {
        TrafficOnewaySaleDO onewaySaleDO = reqDTO.toDO(reqDTO);
        Integer result = onewaySaleMapper.checkExist(onewaySaleDO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期单程票发售情况数据已存在，无法重复新增");
        }
        onewaySaleDO.setId(TokenUtils.getUuId());
        onewaySaleDO.setCreateBy(TokenUtils.getCurrentPersonId());
        onewaySaleMapper.insert(onewaySaleDO);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, OnewaySaleAddReqDTO passengerRecordReqDTO) {
        TrafficOnewaySaleDO onewaySaleDO = passengerRecordReqDTO.toDO(passengerRecordReqDTO);
        onewaySaleMapper.update(onewaySaleDO, new UpdateWrapper<TrafficOnewaySaleDO>().eq("id", onewaySaleDO.getId()));
    }

    @Override
    public Page<OnewaySaleListResDTO> list(OnewaySaleListReqDTO reqDTO) {
        PageHelper.startPage(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<OnewaySaleListResDTO> list = onewaySaleMapper.list(reqDTO.of(), reqDTO);
        List<OnewaySaleListResDTO> records = list.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        return list;
    }

    @Override
    public OnewaySaleDetailResDTO acc(String date) {
        // todo acc
        return null;
    }
}
