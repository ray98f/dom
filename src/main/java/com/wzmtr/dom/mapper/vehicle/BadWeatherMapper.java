package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.BadWeatherReqDTO;
import com.wzmtr.dom.dto.res.vehicle.BadWeatherResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-恶劣天气组织
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Mapper
@Repository
public interface BadWeatherMapper {

    /**
     * 分页查询恶劣天气组织列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 恶劣天气组织列表
     */
    Page<BadWeatherResDTO> page(Page<BadWeatherResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取恶劣天气组织详情
     * @param id id
     * @return 恶劣天气组织详情
     */
    BadWeatherResDTO detail(String id);

    /**
     * 查询当天恶劣天气组织是否已存在
     * @param badWeatherReqDTO 恶劣天气组织参数
     * @return 是否已存在
     */
    Integer selectIsExist(BadWeatherReqDTO badWeatherReqDTO);

    /**
     * 新增恶劣天气组织
     * @param badWeatherReqDTO 恶劣天气组织参数
     */
    void add(BadWeatherReqDTO badWeatherReqDTO);

    /**
     * 编辑恶劣天气组织
     * @param badWeatherReqDTO 恶劣天气组织参数
     */
    void modify(BadWeatherReqDTO badWeatherReqDTO);

    /**
     * 删除恶劣天气组织
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
