package com.wzmtr.dom.mapper.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@Mapper
@Repository
public interface IndicatorMapper {

    /**
     * 列表
     * @param page 分页参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @return 列表
     */
    Page<IndicatorResDTO> list(Page<IndicatorResDTO> page,String startDate,String endDate);

    /**
     * 详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    IndicatorResDTO queryInfoById(String id);

    /**
     * 新增
     * @param indicatorReqDTO 入参数
     */
    void add(IndicatorReqDTO indicatorReqDTO);

    /**
     * 编辑
     * @param indicatorReqDTO 入参数
     */
    int modify(IndicatorReqDTO indicatorReqDTO);

    /**
     * 删除
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
