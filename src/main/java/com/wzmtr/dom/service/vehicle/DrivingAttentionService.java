package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DrivingAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingAttentionResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-行车注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
public interface DrivingAttentionService {

    /**
     * 分页查询行车注意事项列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageReqDTO 分页参数
     * @return 行车注意事项列表
     */
    Page<DrivingAttentionResDTO> page(String startTime, String endTime, PageReqDTO pageReqDTO);

    /**
     * 获取行车注意事项详情
     * @param id id
     * @return 行车注意事项详情
     */
    DrivingAttentionResDTO detail(String id);

    /**
     * 新增行车注意事项
     * @param drivingAttentionReqDTO 行车注意事项参数
     */
    void add(DrivingAttentionReqDTO drivingAttentionReqDTO);

    /**
     * 编辑行车注意事项
     * @param drivingAttentionReqDTO 行车注意事项参数
     */
    void modify(DrivingAttentionReqDTO drivingAttentionReqDTO);

    /**
     * 删除行车注意事项
     * @param ids ids
     */
    void delete(List<String> ids);
}
