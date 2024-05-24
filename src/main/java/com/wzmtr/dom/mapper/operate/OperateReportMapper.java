package com.wzmtr.dom.mapper.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.ReportUpdateReqDTO;
import com.wzmtr.dom.dto.req.operate.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.operate.DailyReportResDTO;
import com.wzmtr.dom.dto.res.operate.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.operate.WeeklyReportResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 运营-运营报表
 * @author  Ray
 * @version 1.0
 * @date 2024/04/24
 */
@Mapper
@Repository
public interface OperateReportMapper {

    /**
     * 分页查询日报列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 日报列表
     */
    Page<DailyReportResDTO> pageDaily(Page<DailyReportResDTO> page, String startDate, String endDate);

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    DailyReportResDTO detailDaily(String id);

    /**
     * 查询当天日报是否已存在
     * @param dailyReportReqDTO 日报参数
     * @return 是否已存在
     */
    Integer selectDailyIsExist(DailyReportReqDTO dailyReportReqDTO);

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
    Page<WeeklyReportResDTO> pageWeekly(Page<WeeklyReportResDTO> page, String startDate, String endDate);

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    WeeklyReportResDTO detailWeekly(String id);

    /**
     * 查询当天周报是否已存在
     * @param weeklyReportReqDTO 周报参数
     * @return 是否已存在
     */
    Integer selectWeeklyIsExist(WeeklyReportReqDTO weeklyReportReqDTO);

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
     * @param userId 用户id
     */
    void deleteWeekly(List<String> ids, String userId);

    /**
     * 分页查询月报列表
     * @param page 分页参数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 月报列表
     */
    Page<MonthlyReportResDTO> pageMonthly(Page<MonthlyReportResDTO> page, String startDate, String endDate);

    /**
     * 获取月报详情
     * @param id id
     * @return 月报详情
     */
    MonthlyReportResDTO detailMonthly(String id);

    /**
     * 查询当天月报是否已存在
     * @param monthlyReportReqDTO 月报参数
     * @return 是否已存在
     */
    Integer selectMonthlyIsExist(MonthlyReportReqDTO monthlyReportReqDTO);

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
     * @param userId 用户id
     */
    void deleteMonthly(List<String> ids, String userId);

    /**
     * 根据流程修改日报状态
     * @param reqDTO 报表修改参数
     */
    void modifyDailyByFlow(ReportUpdateReqDTO reqDTO);

    /**
     * 根据流程修改周报状态
     * @param reqDTO 报表修改参数
     */
    void modifyWeeklyByFlow(ReportUpdateReqDTO reqDTO);

    /**
     * 根据流程修改月报状态
     * @param reqDTO 报表修改参数
     */
    void modifyMonthlyByFlow(ReportUpdateReqDTO reqDTO);

    /**
     * 获取最新日报
     * @return 获取最新日报
     */
    String getLastDaily();

    /**
     * 获取最新周报
     * @return 获取最新周报
     */
    String getLastWeekly();

    /**
     * 获取最新月报
     * @return 获取最新月报
     */
    String getLastMonthly();
}
