package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.HotLineReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运营-服务热线情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Mapper
@Repository
public interface HotLineMapper {

    /**
     * 分页查询服务热线情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 服务热线情况列表
     */
    Page<HotLineSummaryDetailResDTO> page(Page<HotLineSummaryDetailResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取服务热线汇总详情
     * @param id id
     * @return 服务热线汇总详情
     */
    HotLineSummaryDetailResDTO detailSummary(String id, String startDate, String endDate);

    /**
     * 获取热线重要内容列表
     * @param id id
     * @return 热线重要内容列表
     */
    List<HotLineImportantDetailResDTO> detailImportant(String id ,String startDate, String endDate);

    /**
     * 查询当天服务热线情况是否已存在
     * @param hotLineReqDTO 服务热线情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(HotLineReqDTO hotLineReqDTO);

    /**
     * 新增服务热线情况
     * @param hotLineReqDTO 服务热线情况参数
     */
    void add(HotLineReqDTO hotLineReqDTO);

    /**
     * 删除服务热线情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);
}
