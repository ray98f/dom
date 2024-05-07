package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.operate.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.operate.DailyReportResDTO;
import com.wzmtr.dom.dto.res.operate.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.operate.WeeklyReportResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.OperateReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营-运营报表
 * @author  Ray
 * @version 1.0
 * @date 2024/04/24
 */
@RestController
@RequestMapping("/operate/report")
@Api(tags = "运营-运营报表")
@Validated
public class OperateReportController {

    @Autowired
    private OperateReportService operateReportService;

    /**
     * 分页查询日报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 日报列表
     */
    @GetMapping("/daily/page")
    @ApiOperation(value = "日报列表(分页)")
    public PageResponse<DailyReportResDTO> pageDaily(@RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateReportService.pageDaily(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    @GetMapping("/daily/detail")
    @ApiOperation(value = "日报详情")
    public DataResponse<DailyReportResDTO> detailDaily(@RequestParam String id) {
        return DataResponse.of(operateReportService.detailDaily(id));
    }

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/add")
    @ApiOperation(value = "新增-日报")
    public DataResponse<T> addDaily(@RequestBody DailyReportReqDTO dailyReportReqDTO) {
        operateReportService.addDaily(dailyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑日报
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/modify")
    @ApiOperation(value = "编辑-日报")
    public DataResponse<T> modifyDaily(@RequestBody DailyReportReqDTO dailyReportReqDTO) {
        operateReportService.modifyDaily(dailyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除日报
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/daily/delete")
    @ApiOperation(value = "删除-日报(单删+批量删除)")
    public DataResponse<T> deleteDaily(@RequestBody BaseIdsEntity baseIdsEntity) {
        operateReportService.deleteDaily(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 分页查询周报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 周报列表
     */
    @GetMapping("/weekly/page")
    @ApiOperation(value = "周报列表(分页)")
    public PageResponse<WeeklyReportResDTO> pageWeekly(@RequestParam(required = false) String startDate,
                                                       @RequestParam(required = false) String endDate,
                                                       @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateReportService.pageWeekly(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    @GetMapping("/weekly/detail")
    @ApiOperation(value = "周报详情")
    public DataResponse<WeeklyReportResDTO> detailWeekly(@RequestParam String id) {
        return DataResponse.of(operateReportService.detailWeekly(id));
    }

    /**
     * 新增周报
     * @param weeklyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/weekly/add")
    @ApiOperation(value = "新增-周报")
    public DataResponse<T> addWeekly(@RequestBody WeeklyReportReqDTO weeklyReportReqDTO) {
        operateReportService.addWeekly(weeklyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑周报
     * @param weeklyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/weekly/modify")
    @ApiOperation(value = "编辑-周报")
    public DataResponse<T> modifyWeekly(@RequestBody WeeklyReportReqDTO weeklyReportReqDTO) {
        operateReportService.modifyWeekly(weeklyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除周报
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/weekly/delete")
    @ApiOperation(value = "删除-周报(单删+批量删除)")
    public DataResponse<T> deleteWeekly(@RequestBody BaseIdsEntity baseIdsEntity) {
        operateReportService.deleteWeekly(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 分页查询月报列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 月报列表
     */
    @GetMapping("/monthly/page")
    @ApiOperation(value = "月报列表(分页)")
    public PageResponse<MonthlyReportResDTO> pageMonthly(@RequestParam(required = false) String startDate,
                                                         @RequestParam(required = false) String endDate,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateReportService.pageMonthly(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取月报详情
     * @param id id
     * @return 周报详情
     */
    @GetMapping("/monthly/detail")
    @ApiOperation(value = "月报详情")
    public DataResponse<MonthlyReportResDTO> detailMonthly(@RequestParam String id) {
        return DataResponse.of(operateReportService.detailMonthly(id));
    }

    /**
     * 新增月报
     * @param monthlyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/monthly/add")
    @ApiOperation(value = "新增-月报")
    public DataResponse<T> addMonthly(@RequestBody MonthlyReportReqDTO monthlyReportReqDTO) {
        operateReportService.addMonthly(monthlyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑月报
     * @param monthlyReportReqDTO 月报参数
     * @return 成功
     */
    @PostMapping("/monthly/modify")
    @ApiOperation(value = "编辑-月报")
    public DataResponse<T> modifyMonthly(@RequestBody MonthlyReportReqDTO monthlyReportReqDTO) {
        operateReportService.modifyMonthly(monthlyReportReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除月报
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/monthly/delete")
    @ApiOperation(value = "删除-月报(单删+批量删除)")
    public DataResponse<T> deleteMonthly(@RequestBody BaseIdsEntity baseIdsEntity) {
        operateReportService.deleteMonthly(baseIdsEntity.getIds());
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
        operateReportService.commitDaily(currentLoginUser,dailyReportReqDTO);
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
        operateReportService.commitWeekly(currentLoginUser,weeklyReportReqDTO);
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
        operateReportService.commitMonthly(currentLoginUser,monthlyReportReqDTO);
        return DataResponse.success();
    }
}
