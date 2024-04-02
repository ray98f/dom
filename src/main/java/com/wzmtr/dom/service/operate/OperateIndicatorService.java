package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateEventInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 运营日报-运营事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface OperateIndicatorService {

    /**
     * 正运营事件记录-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 运营事件记录列表
     */
    Page<OperateEventResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 正线/车场事件记录-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    OperateEventResDTO detail(String id);

    /**
     * 正线/车场事件记录-新增
     * @param currentLoginUser 入参数
     * @param operateEventReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, OperateEventReqDTO operateEventReqDTO);


}
