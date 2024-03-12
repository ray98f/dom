package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DispatchHandoverReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchHandoverResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-车场调度员交接班情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Mapper
@Repository
public interface DispatchHandoverMapper {

    /**
     * 分页查询车场调度员交接班情况列表
     * @param page 分页参数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 车场调度员交接班情况列表
     */
    Page<DispatchHandoverResDTO> page(Page<DispatchHandoverResDTO> page, String startTime, String endTime);

    /**
     * 获取车场调度员交接班情况详情
     * @param id id
     * @return 车场调度员交接班情况详情
     */
    DispatchHandoverResDTO detail(String id);

    /**
     * 查询当天车场调度员交接班情况是否已存在
     * @param dispatchHandoverReqDTO 车场调度员交接班情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(DispatchHandoverReqDTO dispatchHandoverReqDTO);

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
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
