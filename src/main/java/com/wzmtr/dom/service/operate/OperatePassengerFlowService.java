package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dataobject.operate.OperatePassengerFlowDetailDO;
import com.wzmtr.dom.dto.req.operate.OperateEventInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowDetailResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowTopThreeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

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
    PassengerFlowDetailResDTO detail(String id);

    /**
     * @param currentLoginUser 入参数
     * @param operateEventReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, PassengerFlowAddReqDTO operateEventReqDTO);



    /**
     * 事件信息-删除
     * @param ids ids
     */
    void delete(List<String> ids);

    List<PassengerFlowTopThreeListResDTO> topThree(String date,String dataType);

    List<PassengerFlowTopThreeListResDTO> eachStation(String date,String dataType);
}
