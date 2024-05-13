package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowDetailResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

/**
 * 运营日报-客流
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface OperatePassengerFlowService {

    /**
     * 正客流记录-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 客流记录列表
     */
    Page<PassengerFlowListResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    PassengerFlowDetailResDTO detail(String id, String dataType, String startDate, String endDate);

    /**
     * @param currentLoginUser 入参数
     * @param operateEventReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, PassengerFlowAddReqDTO operateEventReqDTO);


    void modifyStationPassenger(PassengerInfoReqDTO req);

    void modify(PassengerFlowAddReqDTO req);
}
