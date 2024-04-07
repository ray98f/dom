package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.CrewTrainReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewTrainResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 车辆部-乘务中心班组培训情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
public interface CrewTrainService {

    /**
     * 分页查询乘务中心班组培训情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 乘务中心班组培训情况列表
     */
    Page<CrewTrainResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO);

    /**
     * 获取乘务中心班组培训情况详情
     * @param id id
     * @return 乘务中心班组培训情况详情
     */
    CrewTrainResDTO detail(String id);

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
     */
    void delete(List<String> ids);
}