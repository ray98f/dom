package com.wzmtr.dom.impl.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.mapper.traffic.HotLineImportantMapper;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 11:12
 */
@Service
@Slf4j
public class HotLineImportantServiceImpl implements HotLineImportantService {
    @Autowired
    private HotLineImportantMapper hotLineImportantMapper;

    @Override
    public HotLineImportantDetailResDTO detail(SidReqDTO reqDTO) {
        return null;
    }

    @Override
    public void add(HotLineImportantAddReqDTO reqDTO) {

    }

    @Override
    public void modify(CurrentLoginUser currentLoginUser, HotLineImportantAddReqDTO passengerRecordReqDTO) {

    }

    @Override
    public Page<HotLineImportantListResDTO> list(HotLineSummaryListReqDTO reqDTO) {
        return null;
    }

    @Override
    public HotLineImportantDetailResDTO acc(SidReqDTO reqDTO) {
        return null;
    }
}
