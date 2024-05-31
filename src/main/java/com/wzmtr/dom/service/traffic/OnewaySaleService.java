package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OneWaySaleDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:21
 */
public interface OnewaySaleService {

    OnewaySaleDetailResDTO detail(OneWaySaleDetailReqDTO reqDTO);

    void add(CurrentLoginUser currentLoginUser,OnewaySaleAddReqDTO reqDTO);

    void modify(CurrentLoginUser currentLoginUser, OnewaySaleAddReqDTO passengerRecordReqDTO);

    Page<OnewaySaleListResDTO> list(OnewaySaleListReqDTO reqDTO);

    OnewaySaleDetailResDTO acc(String dataType, String startDate, String endDate);


    /**
     * 数据统计
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModify(String dataType, String startDate, String endDate);

    /**
     * 数据统计
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModifyByDaily(String dataType, String startDate, String endDate);
}
