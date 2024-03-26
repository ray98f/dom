package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineHandoverDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.HotLineHandoverMapper;
import com.wzmtr.dom.service.traffic.HotLineHandoverService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.DateUtils;
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
    public HotLineHandoverDetailResDTO detail(SidReqDTO reqDTO) {
        TrafficHotlineHandoverDO trafficHotlineHandoverDO = hotLineHandoverMapper.selectById(reqDTO.getId());
        return BeanUtils.convert(trafficHotlineHandoverDO, HotLineHandoverDetailResDTO.class);
    }

    @Override
    public HotLineHandoverDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }

    @Override
    public void add(HotLineHandoverAddReqDTO reqDTO) {
        Assert.isFalse(hotLineHandoverMapper.selectIsExist(reqDTO) > 0, "所属日期数据已存在，无法重复新增");
        TrafficHotlineHandoverDO trafficHotlineHandoverDO = reqDTO.toDO(reqDTO);
        trafficHotlineHandoverDO.setId(TokenUtils.getUuId());
        trafficHotlineHandoverDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineHandoverDO.setCreateDate(DateUtils.currentDate());
        trafficHotlineHandoverDO.setDelFlag("0");
        trafficHotlineHandoverDO.setVersion("0");
        hotLineHandoverMapper.insert(trafficHotlineHandoverDO);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineHandoverAddReqDTO req) {
        Integer result = hotLineHandoverMapper.selectIsExist(req);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        TrafficHotlineHandoverDO trafficHotlineHandoverDO = req.toDO(req);
        trafficHotlineHandoverDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineHandoverDO.setUpdateDate(DateUtils.currentDate());
        hotLineHandoverMapper.updateById(trafficHotlineHandoverDO);
    }
}
