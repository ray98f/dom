package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.TicketUseReqDTO;
import com.wzmtr.dom.dto.res.traffic.TicketUseResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 客运部-线网车票过闸使用情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Mapper
@Repository
public interface TicketUseMapper {

    /**
     * 分页查询线网车票过闸使用情况列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 线网车票过闸使用情况列表
     */
    Page<TicketUseResDTO> page(Page<TicketUseResDTO> page, String startDate, String endDate, String dataType);

    /**
     * 获取线网车票过闸使用情况详情
     * @param id id
     * @return 线网车票过闸使用情况详情
     */
    TicketUseResDTO detail(String id,String startDate, String endDate);

    /**
     * 获取前一周期的线网车票过闸使用情况详情
     * @param dataDate 日报日期
     * @param startDate 周报、月报开始日期
     * @return 线网车票过闸使用情况详情
     */
    TicketUseResDTO getLastDetail(Date dataDate, Date startDate);

    /**
     * 查询当天线网车票过闸使用情况是否已存在
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     * @return 是否已存在
     */
    Integer selectIsExist(TicketUseReqDTO ticketUseReqDTO);

    /**
     * 新增线网车票过闸使用情况
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     */
    void add(TicketUseReqDTO ticketUseReqDTO);

    /**
     * 编辑线网车票过闸使用情况
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     */
    void modify(TicketUseReqDTO ticketUseReqDTO);

    /**
     * 删除线网车票过闸使用情况
     * @param ids ids
     * @param userId 用户id
     */
    void delete(List<String> ids, String userId);

    /**
     * 线网车票过闸使用数据更新
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModify(String dataType,String startDate,String endDate);

    /**
     * 线网车票过闸使用数据更新
     * @param dataType 入参数
     * @param startDate 入参数
     * @param endDate 入参数
     */
    void autoModifyByDaily(String dataType,String startDate,String endDate);
}
