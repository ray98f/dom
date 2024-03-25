package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineHandoverDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverListReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.traffic.HotLineHandoverMapper;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.service.traffic.HotLineHandoverService;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import com.wzmtr.dom.utils.BeanUtils;
import com.wzmtr.dom.utils.DateUtils;
import com.wzmtr.dom.utils.StringUtils;
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
        Page<HotLineHandoverListResDTO> page = hotLineHandoverMapper.list(reqDTO.of(),reqDTO);
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        return page;
    }

    @Override
    public HotLineHandoverDetailResDTO detail(SidReqDTO reqDTO) {
        TrafficHotlineHandoverDO trafficHotlineHandoverDO = hotLineHandoverMapper.selectById(reqDTO.getId());
        return BeanUtils.convert(trafficHotlineHandoverDO,HotLineHandoverDetailResDTO.class);
    }

    @Override
    public HotLineHandoverDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }

    @Override
    public void add(HotLineHandoverAddReqDTO reqDTO) {
        Assert.isFalse(hotLineHandoverMapper.selectIsExist(reqDTO) > 0, "所属日期其他情况说明数据已存在，无法重复新增");
        TrafficHotlineHandoverDO trafficHotlineHandoverDO = reqDTO.toDO(reqDTO);
        trafficHotlineHandoverDO.setCreateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineHandoverDO.setUpdateBy(TokenUtils.getCurrentPersonId());
        trafficHotlineHandoverDO.setCreateDate(DateUtils.currentDate());
        trafficHotlineHandoverDO.setUpdateDate(DateUtils.currentDate());
        hotLineHandoverMapper.insert(trafficHotlineHandoverDO);
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineHandoverAddReqDTO req) {
        //
    }
}
