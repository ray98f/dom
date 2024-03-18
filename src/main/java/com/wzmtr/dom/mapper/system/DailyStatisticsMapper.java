package com.wzmtr.dom.mapper.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.DailyStatisticsReqDTO;
import com.wzmtr.dom.dto.res.system.DailyStatisticsResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统参数-日报统计参数设置管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@Mapper
@Repository
public interface DailyStatisticsMapper {

    /**
     * 分页查询日报统计参数设置列表
     * @param page 分页参数
     * @return 日报统计参数设置列表
     */
    Page<DailyStatisticsResDTO> page(Page<DailyStatisticsResDTO> page);

    /**
     * 获取日报统计参数设置详情
     * @param id id
     * @return 日报统计参数设置详情
     */
    DailyStatisticsResDTO detail(String id);

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
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
