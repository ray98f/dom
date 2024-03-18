package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.CrewTrainReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewTrainResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-乘务中心班组培训情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@Mapper
@Repository
public interface CrewTrainMapper {

    /**
     * 分页查询乘务中心班组培训情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 乘务中心班组培训情况列表
     */
    Page<CrewTrainResDTO> page(Page<CrewTrainResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取乘务中心班组培训情况详情
     * @param id id
     * @return 乘务中心班组培训情况详情
     */
    CrewTrainResDTO detail(String id);

    /**
     * 查询当天乘务中心班组培训情况是否已存在
     * @param crewTrainReqDTO 乘务中心班组培训情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(CrewTrainReqDTO crewTrainReqDTO);

    /**
     * 新增乘务中心班组培训情况
     * @param crewTrainReqDTO 乘务中心班组培训情况参数
     */
    void add(CrewTrainReqDTO crewTrainReqDTO);

    /**
     * 编辑乘务中心班组培训情况
     * @param crewTrainReqDTO 乘务中心班组培训情况参数
     */
    void modify(CrewTrainReqDTO crewTrainReqDTO);

    /**
     * 删除乘务中心班组培训情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
