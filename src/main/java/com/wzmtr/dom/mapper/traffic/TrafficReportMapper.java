package com.wzmtr.dom.mapper.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.WeeklyReportResDTO;
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
     * 获取日报子报表
     * @param id
     * @return 日报子报表
     */
    List<DailyReportResDTO> queryDailyById(String id);

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
     * 编辑日报
     * @param dailyReportReqDTO 日报参数
     */
    void dailyApprovalComplete(DailyReportReqDTO dailyReportReqDTO);

    /**
     * 更新主日报
     * @param dailyReportReqDTO 日报参数
     */
    void modifyMainDaily(DailyReportReqDTO dailyReportReqDTO);

    /**
     * 删除日报
     * @param ids ids
     * @param userId 用户id
     */
    void deleteDaily(List<String> ids, String userId);

    /**
     * 分页查询周报列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 周报列表
     */
    Page<WeeklyReportResDTO> weeklyList(Page<WeeklyReportResDTO> page, String startDate, String endDate);

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    WeeklyReportResDTO weeklyDetail(String id);

    /**
     * 获取周报子报表
     * @param id
     * @return 周报子报表
     */
    List<WeeklyReportResDTO> queryWeeklyById(String id);

    /**
     * 获取周报子报表
     * @param parentId id
     * @return 周报子报表
     */
    List<WeeklyReportResDTO> queryWeeklyByParent(String parentId);

    /**
     * 查询周报是否已存在
     * @param weeklyReportReqDTO 周报参数
     * @return 是否已存在
     */
    Integer checkWeeklyExist(WeeklyReportReqDTO weeklyReportReqDTO);


    /**
     * 新增周报
     * @param weeklyReportReqDTO 周报参数
     */
    void addWeekly(WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 编辑周报
     * @param weeklyReportReqDTO 周报参数
     */
    int modifyWeekly(WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 编辑周报
     * @param weeklyReportReqDTO 周报参数
     */
    void weeklyApprovalComplete(WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 更新主周报
     * @param weeklyReportReqDTO 周报参数
     */
    void modifyMainWeekly(WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 分页查询月报列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 月报列表
     */
    Page<MonthlyReportResDTO> monthlyList(Page<MonthlyReportResDTO> page, String startDate, String endDate);

    /**
     * 获取月报详情
     * @param id id
     * @return 月报详情
     */
    MonthlyReportResDTO monthlyDetail(String id);

    /**
     * 获取月报子报表
     * @param id
     * @return 月报子报表
     */
    List<MonthlyReportResDTO> queryMonthlyById(String id);

    /**
     * 获取月报子报表
     * @param parentId id
     * @return 月报子报表
     */
    List<MonthlyReportResDTO> queryMonthlyByParent(String parentId);

    /**
     * 查询月报是否已存在
     * @param monthlyReportReqDTO 月报参数
     * @return 是否已存在
     */
    Integer checkMonthlyExist(MonthlyReportReqDTO monthlyReportReqDTO);


    /**
     * 新增月报
     * @param monthlyReportReqDTO 月报参数
     */
    void addMonthly(MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 编辑月报
     * @param monthlyReportReqDTO 月报参数
     */
    int modifyMonthly(MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 编辑月报
     * @param monthlyReportReqDTO 月报参数
     */
    void monthlyApprovalComplete(MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 更新主周报
     * @param monthlyReportReqDTO 周报参数
     */
    void modifyMainMonthly(MonthlyReportReqDTO monthlyReportReqDTO);
}
