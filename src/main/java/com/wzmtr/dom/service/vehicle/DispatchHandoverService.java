package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DispatchHandoverReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchHandoverResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-车场调度员交接班情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
public interface DispatchHandoverService {

    /**
     * 分页查询车场调度员交接班情况列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageReqDTO 分页参数
     * @return 车场调度员交接班情况列表
     */
    Page<DispatchHandoverResDTO> page(String startTime, String endTime, PageReqDTO pageReqDTO);

    /**
     * 获取车场调度员交接班情况详情
     * @param id id
     * @return 车场调度员交接班情况详情
     */
    DispatchHandoverResDTO detail(String id);

    /**
     * 新增车场调度员交接班情况
     * @param dispatchHandoverReqDTO 车场调度员交接班情况参数
     */
    void add(DispatchHandoverReqDTO dispatchHandoverReqDTO);

    /**
     * 编辑车场调度员交接班情况
     * @param dispatchHandoverReqDTO 车场调度员交接班情况参数
     */
    void modify(DispatchHandoverReqDTO dispatchHandoverReqDTO);

    /**
     * 删除车场调度员交接班情况
     * @param ids ids
     */
    void delete(List<String> ids);
}
