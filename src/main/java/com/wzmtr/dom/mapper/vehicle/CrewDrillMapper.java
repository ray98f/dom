package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.CrewDrillReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewDrillResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-乘务中心演练情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@Mapper
@Repository
public interface CrewDrillMapper {

    /**
     * 分页查询乘务中心演练情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 乘务中心演练情况列表
     */
    Page<CrewDrillResDTO> page(Page<CrewDrillResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取乘务中心演练情况详情
     * @param id id
     * @return 乘务中心演练情况详情
     */
    CrewDrillResDTO detail(String id);

    /**
     * 查询当天乘务中心演练情况是否已存在
     * @param crewDrillReqDTO 乘务中心演练情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(CrewDrillReqDTO crewDrillReqDTO);

    /**
     * 新增乘务中心演练情况
     * @param crewDrillReqDTO 乘务中心演练情况参数
     */
    void add(CrewDrillReqDTO crewDrillReqDTO);

    /**
     * 编辑乘务中心演练情况
     * @param crewDrillReqDTO 乘务中心演练情况参数
     */
    void modify(CrewDrillReqDTO crewDrillReqDTO);

    /**
     * 删除乘务中心演练情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
