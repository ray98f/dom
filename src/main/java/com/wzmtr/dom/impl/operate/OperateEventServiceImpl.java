package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateEventInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.operate.OperateEventMapper;
import com.wzmtr.dom.service.operate.OperateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 17:02
 */
@Service
public class OperateEventServiceImpl implements OperateEventService {

    @Autowired
    private OperateEventMapper operateEventMapper;

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

    @Override
    public Page<OperateEventInfoResDTO> eventList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        return null;
    }

    @Override
    public void createEvent(CurrentLoginUser currentLoginUser, OperateEventInfoReqDTO operateEventInfoReqDTO) {

    }

    @Override
    public void modifyEvent(CurrentLoginUser currentLoginUser, OperateEventInfoReqDTO operateEventInfoReqDTO) {

    }

    @Override
    public void deleteEvent(List<String> ids) {

    }
}
