package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.WeeklyReportResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 客运部-客运部报表
 * @author  zhangxin
 * @version 1.0
 * @date 2024/03/21
 */
public interface TrafficReportService {

    /**
     * 分页查询日报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 日报列表
     */
    Page<DailyReportResDTO> dailyList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    DailyReportResDTO dailyDetail(String id);

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     */
    void addDaily(CurrentLoginUser currentLoginUser,DailyReportReqDTO dailyReportReqDTO);

    /**
     * 编辑日报
     * @param dailyReportReqDTO 日报参数
     */
    void modifyDaily(CurrentLoginUser currentLoginUser,DailyReportReqDTO dailyReportReqDTO);

    /**
     * 报审日报
     * @param dailyReportReqDTO 日报参数
     */
    void commitDaily(CurrentLoginUser currentLoginUser,DailyReportReqDTO dailyReportReqDTO);
    /**
     * 删除日报
     * @param ids ids
     */
    void deleteDaily(List<String> ids);

    /**
     * 分页查询周报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 周报列表
     */
    Page<WeeklyReportResDTO> weeklyList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    WeeklyReportResDTO detailWeekly(String id);

    /**
     * 新增周报
     * @param currentLoginUser 周报参数
     * @param weeklyReportReqDTO 周报参数
     */
    void addWeekly(CurrentLoginUser currentLoginUser, WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 编辑周报
     * @param weeklyReportReqDTO 周报参数
     */
    void modifyWeekly(CurrentLoginUser currentLoginUser,WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 报审-周报
     * @param weeklyReportReqDTO 周报参数
     */
    void commitWeekly(CurrentLoginUser currentLoginUser,WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 分页查询月报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 月报列表
     */
    Page<MonthlyReportResDTO> monthlyList(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取月报详情
     * @param id id
     * @return 周报详情
     */
    MonthlyReportResDTO detailMonthly(String id);

    /**
     * 新增月报
     * @param currentLoginUser 月报参数
     * @param monthlyReportReqDTO 月报参数
     */
    void addMonthly(CurrentLoginUser currentLoginUser, MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 编辑月报
     * @param monthlyReportReqDTO 月报参数
     */
    void modifyMonthly(CurrentLoginUser currentLoginUser,MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 报审月报
     * @param monthlyReportReqDTO 月报参数
     */
    void commitMonthly(CurrentLoginUser currentLoginUser,MonthlyReportReqDTO monthlyReportReqDTO);
}
