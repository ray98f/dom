package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.CrewAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewAttentionResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-乘务中心注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@Mapper
@Repository
public interface CrewAttentionMapper {

    /**
     * 分页查询乘务中心注意事项列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 乘务中心注意事项列表
     */
    Page<CrewAttentionResDTO> page(Page<CrewAttentionResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取乘务中心注意事项详情
     * @param id id
     * @return 乘务中心注意事项详情
     */
    CrewAttentionResDTO detail(String id);

    /**
     * 查询当天乘务中心注意事项是否已存在
     * @param crewAttentionReqDTO 乘务中心注意事项参数
     * @return 是否已存在
     */
    Integer selectIsExist(CrewAttentionReqDTO crewAttentionReqDTO);

    /**
     * 新增乘务中心注意事项
     * @param crewAttentionReqDTO 乘务中心注意事项参数
     */
    void add(CrewAttentionReqDTO crewAttentionReqDTO);

    /**
     * 编辑乘务中心注意事项
     * @param crewAttentionReqDTO 乘务中心注意事项参数
     */
    void modify(CrewAttentionReqDTO crewAttentionReqDTO);

    /**
     * 删除乘务中心注意事项
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
