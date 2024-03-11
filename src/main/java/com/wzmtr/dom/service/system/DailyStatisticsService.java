package com.wzmtr.dom.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.DailyStatisticsReqDTO;
import com.wzmtr.dom.dto.res.system.DailyStatisticsResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 系统参数-日报统计参数设置
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
public interface DailyStatisticsService {

    /**
     * 分页查询日报统计参数设置列表
     * @param pageReqDTO 分页参数
     * @return 日报统计参数设置列表
     */
    Page<DailyStatisticsResDTO> page(PageReqDTO pageReqDTO);

    /**
     * 获取日报统计参数设置详情
     * @param id id
     * @return 日报统计参数设置详情
     */
    DailyStatisticsResDTO detail(Integer id);

    /**
     * 新增日报统计参数设置
     * @param dailyStatisticsReqDTO 日报统计参数设置参数
     */
    void add(DailyStatisticsReqDTO dailyStatisticsReqDTO);

    /**
     * 编辑日报统计参数设置
     * @param dailyStatisticsReqDTO 日报统计参数设置参数
     */
    void modify(DailyStatisticsReqDTO dailyStatisticsReqDTO);

    /**
     * 删除日报统计参数设置
     * @param ids ids
     */
    void delete(List<String> ids);
}
