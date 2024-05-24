package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.operate.DailyReportResDTO;
import com.wzmtr.dom.dto.res.operate.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.operate.WeeklyReportResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.text.ParseException;
import java.util.List;

/**
 * 运营-运营报表
 * @author  Ray
 * @version 1.0
 * @date 2024/04/24
 */
public interface OperateReportService {

    /**
     * 获取安全运营天数
     * @return 安全运营天数
     */
    String getSafeDay() throws ParseException;

    /**
     * 分页查询日报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 日报列表
     */
    Page<DailyReportResDTO> pageDaily(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    DailyReportResDTO detailDaily(String id);

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     */
    void addDaily(DailyReportReqDTO dailyReportReqDTO);

    /**
     * 编辑日报
     * @param dailyReportReqDTO 日报参数
     */
    void modifyDaily(DailyReportReqDTO dailyReportReqDTO);

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
    Page<WeeklyReportResDTO> pageWeekly(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    WeeklyReportResDTO detailWeekly(String id);

    /**
     * 新增周报
     * @param weeklyReportReqDTO 周报参数
     */
    void addWeekly(WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 编辑周报
     * @param weeklyReportReqDTO 周报参数
     */
    void modifyWeekly(WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 删除周报
     * @param ids ids
     */
    void deleteWeekly(List<String> ids);

    /**
     * 分页查询月报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 月报列表
     */
    Page<MonthlyReportResDTO> pageMonthly(String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 获取月报详情
     * @param id id
     * @return 月报详情
     */
    MonthlyReportResDTO detailMonthly(String id);

    /**
     * 新增月报
     * @param monthlyReportReqDTO 月报参数
     */
    void addMonthly(MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 编辑月报
     * @param monthlyReportReqDTO 月报参数
     */
    void modifyMonthly(MonthlyReportReqDTO monthlyReportReqDTO);

    /**
     * 删除月报
     * @param ids ids
     */
    void deleteMonthly(List<String> ids);

    /**
     * 报审日报
     * @param currentLoginUser 登录用户信息
     * @param dailyReportReqDTO 日报参数
     */
    void commitDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO);

    /**
     * 报审-周报
     * @param currentLoginUser 登录用户信息
     * @param weeklyReportReqDTO 周报参数
     */
    void commitWeekly(CurrentLoginUser currentLoginUser,WeeklyReportReqDTO weeklyReportReqDTO);

    /**
     * 报审月报
     * @param currentLoginUser 登录用户信息
     * @param monthlyReportReqDTO 月报参数
     */
    void commitMonthly(CurrentLoginUser currentLoginUser, MonthlyReportReqDTO monthlyReportReqDTO);
}
