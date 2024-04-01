package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客运部-客运部报表
 * @author  zhangxin
 * @version 1.0
 * @date 2024/03/14
 */
@Mapper
@Repository
public interface TrafficReportMapper {

    /**
     * 分页查询日报列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 日报列表
     */
    Page<DailyReportResDTO> dailyList(Page<DailyReportResDTO> page, String startDate, String endDate);

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    DailyReportResDTO dailyDetail(String id);

    /**
     * 获取日报子报表
     * @param parentId id
     * @return 日报子报表
     */
    List<DailyReportResDTO> queryDailyByParent(String parentId);

    /**
     * 查询当天日报是否已存在
     * @param dailyReportReqDTO 日报参数
     * @return 是否已存在
     */
    Integer checkDailyExist(DailyReportReqDTO dailyReportReqDTO);

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     */
    void addDaily(DailyReportReqDTO dailyReportReqDTO);

    /**
     * 编辑日报
     * @param dailyReportReqDTO 日报参数
     */
    int modifyDaily(DailyReportReqDTO dailyReportReqDTO);

    /**
     * 删除日报
     * @param ids ids
     * @param userId 用户id
     */
    void deleteDaily(List<String> ids, String userId);

}
