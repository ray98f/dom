package com.wzmtr.dom.controller.traffic;


import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.WeeklyReportResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.TrafficReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-客运部报表
 * @author  zhangxin
 * @version 1.0
 * @date 2024/03/21
 */
@RestController
@RequestMapping("/traffic/report")
@Api(tags = "客运部-客运部报表")
@Validated
public class TrafficReportController {

    @Autowired
    private TrafficReportService reportService;

    /**
     * 分页查询日报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 日报列表
     */
    @GetMapping("/daily/list")
    @ApiOperation(value = "日报列表(分页)")
    public PageResponse<DailyReportResDTO> dailyList(@RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(reportService.dailyList(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    @GetMapping("/daily/detail")
    @ApiOperation(value = "日报详情")
    public DataResponse<DailyReportResDTO> detailDaily(@RequestParam(required = false) String id,@RequestParam(required = false) String startDate,
                                                       @RequestParam(required = false) String endDate) {
        return DataResponse.of(reportService.dailyDetail(id,startDate,endDate));
    }

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/add")
    @ApiOperation(value = "新增-日报")
    public DataResponse<T> addDaily(@CurrUser CurrentLoginUser currentLoginUser,
                                    @RequestBody DailyReportReqDTO dailyReportReqDTO) {
        reportService.addDaily(currentLoginUser,dailyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑日报
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/modify")
    @ApiOperation(value = "编辑-日报")
    public DataResponse<T> modifyDaily(@CurrUser CurrentLoginUser currentLoginUser,
                                       @RequestBody DailyReportReqDTO dailyReportReqDTO) {
        reportService.modifyDaily(currentLoginUser,dailyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 报审-日报
     * @param currentLoginUser 登录用户信息
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/commit")
    @ApiOperation(value = "报审-日报")
    public DataResponse<T> commitDaily(@CurrUser CurrentLoginUser currentLoginUser,
                                       @RequestBody DailyReportReqDTO dailyReportReqDTO) {
        reportService.commitDaily(currentLoginUser,dailyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 分页查询周报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 周报列表
     */
    @GetMapping("/weekly/list")
    @ApiOperation(value = "周报列表(分页)")
    public PageResponse<WeeklyReportResDTO> weeklyList(@RequestParam(required = false) String startDate,
                                                       @RequestParam(required = false) String endDate,
                                                       @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(reportService.weeklyList(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    @GetMapping("/weekly/detail")
    @ApiOperation(value = "周报详情")
    public DataResponse<WeeklyReportResDTO> detailWeekly(@RequestParam String id) {
        return DataResponse.of(reportService.detailWeekly(id));
    }

    /**
     * 新增周报
     * @param weeklyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/weekly/add")
    @ApiOperation(value = "新增-周报")
    public DataResponse<T> addWeekly(@CurrUser CurrentLoginUser currentLoginUser,
                                     @RequestBody WeeklyReportReqDTO weeklyReportReqDTO) {
        reportService.addWeekly(currentLoginUser,weeklyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑周报
     * @param weeklyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/weekly/modify")
    @ApiOperation(value = "编辑-周报")
    public DataResponse<T> modifyWeekly(@CurrUser CurrentLoginUser currentLoginUser,
                                        @RequestBody WeeklyReportReqDTO weeklyReportReqDTO) {
        reportService.modifyWeekly(currentLoginUser,weeklyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 报审-周报
     * @param currentLoginUser 登录用户信息
     * @param weeklyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/weekly/commit")
    @ApiOperation(value = "报审-周报")
    public DataResponse<T> commitWeekly(@CurrUser CurrentLoginUser currentLoginUser,
                                        @RequestBody WeeklyReportReqDTO weeklyReportReqDTO) {
        reportService.commitWeekly(currentLoginUser,weeklyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 分页查询月报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 月报列表
     */
    @GetMapping("/monthly/list")
    @ApiOperation(value = "月报列表(分页)")
    public PageResponse<MonthlyReportResDTO> monthlyList(@RequestParam(required = false) String startDate,
                                                         @RequestParam(required = false) String endDate,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(reportService.monthlyList(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取月报详情
     * @param id id
     * @return 月报详情
     */
    @GetMapping("/monthly/detail")
    @ApiOperation(value = "月报详情")
    public DataResponse<MonthlyReportResDTO> detailMonthly(@RequestParam String id) {
        return DataResponse.of(reportService.detailMonthly(id));
    }

    /**
     * 新增月报
     * @param monthlyReportReqDTO 月报参数
     * @return 成功
     */
    @PostMapping("/monthly/add")
    @ApiOperation(value = "新增-月报")
    public DataResponse<T> addWeekly(@CurrUser CurrentLoginUser currentLoginUser,
                                     @RequestBody MonthlyReportReqDTO monthlyReportReqDTO) {
        reportService.addMonthly(currentLoginUser,monthlyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑月报
     * @param monthlyReportReqDTO 月报参数
     * @return 成功
     */
    @PostMapping("/monthly/modify")
    @ApiOperation(value = "编辑-月报")
    public DataResponse<T> modifyMonthly(@CurrUser CurrentLoginUser currentLoginUser,
                                         @RequestBody MonthlyReportReqDTO monthlyReportReqDTO) {
        reportService.modifyMonthly(currentLoginUser,monthlyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 报审-月报
     * @param currentLoginUser 登录用户信息
     * @param monthlyReportReqDTO 月报参数
     * @return 成功
     */
    @PostMapping("/monthly/commit")
    @ApiOperation(value = "报审-月报")
    public DataResponse<T> commitMonthly(@CurrUser CurrentLoginUser currentLoginUser,
                                         @RequestBody MonthlyReportReqDTO monthlyReportReqDTO) {
        reportService.commitMonthly(currentLoginUser,monthlyReportReqDTO);
        return DataResponse.success();
    }
}
