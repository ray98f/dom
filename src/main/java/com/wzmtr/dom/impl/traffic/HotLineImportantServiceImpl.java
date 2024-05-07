package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.collection.CollectionUtil;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dataobject.traffic.TrafficHotlineImportantDO;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.res.common.DictResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.common.DictMapper;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import com.wzmtr.dom.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private DictMapper dictMapper;

    @Override
    public List<HotLineImportantDetailResDTO> detail(String date, String startDate, String endDate, String dataType) {
        return hotLineImportantMapper.selectByDate(date, startDate, endDate, dataType);
    }

    @Override
    public void add(HotLineImportantAddReqDTO req) {
        List<TrafficHotlineImportantDO> list = new ArrayList<>();
        List<DictResDTO> dictList = dictMapper.list(CommonConstants.HOTLINE_TYPE, null, CommonConstants.ZERO_STRING);
        if (StringUtils.isNotEmpty(dictList)) {
            for (DictResDTO dict : dictList) {
                TrafficHotlineImportantDO res = new TrafficHotlineImportantDO();
                res.setId(TokenUtils.getUuId());
                res.setCreateBy(TokenUtils.getCurrentPersonId());
                res.setDataDate(req.getDataDate());
                res.setStartDate(req.getStartDate());
                res.setEndDate(req.getEndDate());
                res.setDataType(req.getDataType());
                res.setHotlineType(dict.getCode());
                list.add(res);
            }
        }
        if (CollectionUtil.isNotEmpty(list)) {
            hotLineImportantMapper.insertList(list);
        }
    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineImportantAddReqDTO reqDTO) {
        List<HotLineImportantAddDataReqDTO> addDataReq = Assert.notNull(reqDTO.getHotlineImportantList(), "参数缺失");
        addDataReq.forEach(x -> {
            TrafficHotlineImportantDO convert = BeanUtils.convert(x, TrafficHotlineImportantDO.class);
            convert.setUpdateBy(TokenUtils.getCurrentPersonId());
            convert.setUpdateDate(DateUtils.currentDate());
            hotLineImportantMapper.updateById(convert);
        });
    }


    @Override
    public List<HotLineImportantDetailResDTO> acc(SidReqDTO reqDTO) {
        return null;
    }
}
