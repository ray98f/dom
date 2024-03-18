package com.wzmtr.dom.service.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0O
 * @date 2024/3/8 16:22
 */
public interface IndicatorService {


    /**
     * 重要指标-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 重要指标列表
     */
    Page<IndicatorResDTO> list(String dataType,String startDate,String endDate,PageReqDTO pageReqDTO);

    /**
     * 重要指标-详情
     * @param id 入参数
     * @return IndicatorResDTO
     */
    IndicatorResDTO detail(String id);

    /**
     * 重要指标-新增
     * @param currentLoginUser 入参数
     * @param indicatorReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO);

    /**
     * 重要指标-编辑
     * @param currentLoginUser 入参数
     * @param indicatorReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, IndicatorReqDTO indicatorReqDTO);

    /**
     * 重要指标-删除
     * @param ids ids
     */
    void delete(List<String> ids);

}
