package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.DrivingAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingAttentionResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-行车注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Mapper
@Repository
public interface DrivingAttentionMapper {

    /**
     * 分页查询行车注意事项列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 行车注意事项列表
     */
    Page<DrivingAttentionResDTO> page(Page<DrivingAttentionResDTO> page, String startDate, String endDate);

    /**
     * 获取行车注意事项详情
     * @param id id
     * @return 行车注意事项详情
     */
    DrivingAttentionResDTO detail(String id);

    /**
     * 查询当天行车注意事项是否已存在
     * @param drivingAttentionReqDTO 行车注意事项参数
     * @return 是否已存在
     */
    Integer selectIsExist(DrivingAttentionReqDTO drivingAttentionReqDTO);

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
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
