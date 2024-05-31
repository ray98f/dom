package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.TicketUseReqDTO;
import com.wzmtr.dom.dto.res.traffic.TicketUseResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 客运部-线网车票过闸使用情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
public interface TicketUseService {

    /**
     * 分页查询线网车票过闸使用情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 线网车票过闸使用情况列表
     */
    Page<TicketUseResDTO> page(String startDate, String endDate, String dataType, PageReqDTO pageReqDTO);

    /**
     * 获取线网车票过闸使用情况详情
     * @param id id
     * @return 线网车票过闸使用情况详情
     */
    TicketUseResDTO detail(String id,String startDate, String endDate);

    /**
     * 获取ACC系统线网车票过闸使用情况
     * @param dataType 数据类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 线网车票过闸使用情况
     */
    TicketUseResDTO acc(String dataType, String startDate, String endDate);

    /**
     * 新增线网车票过闸使用情况
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     */
    void add(CurrentLoginUser currentLoginUser, TicketUseReqDTO ticketUseReqDTO);

    /**
     * 编辑线网车票过闸使用情况
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     */
    void modify(TicketUseReqDTO ticketUseReqDTO);

    /**
     * 删除线网车票过闸使用情况
     * @param ids ids
     */
    void delete(List<String> ids);

    /**
     * 线网车票过闸使用情况统计
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModify(String dataType, String startDate, String endDate);

    /**
     * 线网车票过闸使用情况统计
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModifyByDaily(String dataType, String startDate, String endDate);
}
