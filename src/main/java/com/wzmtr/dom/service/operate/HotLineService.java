package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.HotLineReqDTO;
import com.wzmtr.dom.dto.res.operate.HotLineResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 运营-服务热线情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
public interface HotLineService {

    /**
     * 分页查询服务热线情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 服务热线情况列表
     */
    Page<HotLineSummaryDetailResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO);

    /**
     * 获取服务热线情况详情
     * @param id id
     * @return 服务热线情况详情
     */
    HotLineResDTO detail(String id);

    /**
     * 新增服务热线情况
     * @param hotLineReqDTO 服务热线情况参数
     */
    void add(HotLineReqDTO hotLineReqDTO);

    /**
     * 删除服务热线情况
     * @param ids ids
     */
    void delete(List<String> ids);
}
