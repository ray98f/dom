package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.operate.OperateIndicatorMapper;
import com.wzmtr.dom.service.operate.OperateIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/4/2 13:43
 */
@Service
public class OperateIndicatorServiceImpl implements OperateIndicatorService {

    @Autowired
    private OperateIndicatorMapper operateIndicatorMapper;

    @Override
    public Page<OperateEventResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO) {
        return null;
    }

    @Override
    public OperateEventResDTO detail(String id) {
        return null;
    }

    @Override
    public void add(CurrentLoginUser currentLoginUser, OperateEventReqDTO operateEventReqDTO) {

    }
}
