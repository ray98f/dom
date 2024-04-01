package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.vehicle.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.vehicle.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DailyReportResDTO;
import com.wzmtr.dom.dto.res.vehicle.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.vehicle.WeeklyReportResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.VehicleReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-车辆部报表
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@RestController
@RequestMapping("/vehicle/report")
@Api(tags = "车辆部-车辆部报表")
@Validated
public class VehicleReportController {

    @Autowired
    private VehicleReportService vehicleReportService;

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
        return PageResponse.of(vehicleReportService.pageDaily(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    @GetMapping("/daily/detail")
    @ApiOperation(value = "日报详情")
    public DataResponse<DailyReportResDTO> detailDaily(@RequestParam String id) {
        return DataResponse.of(vehicleReportService.detailDaily(id));
    }

    /**
     * 获取乘务系统当天人员
     * @param date 日期
     * @return 乘务系统当天人员
     */
    @GetMapping("/daily/user/ocm")
    @ApiOperation(value = "获取乘务系统当天人员")
    public DataResponse<DailyReportResDTO> ocmUserDaily(@RequestParam String date) {
        return DataResponse.of(vehicleReportService.ocmUserDaily(date));
    }

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/add")
    @ApiOperation(value = "新增-日报")
    public DataResponse<T> addDaily(@RequestBody DailyReportReqDTO dailyReportReqDTO) {
        vehicleReportService.addDaily(dailyReportReqDTO);
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
        vehicleReportService.modifyDaily(dailyReportReqDTO);
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
        vehicleReportService.deleteDaily(baseIdsEntity.getIds());
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
        return PageResponse.of(vehicleReportService.pageWeekly(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取周报详情
     * @param id id
     * @return 周报详情
     */
    @GetMapping("/weekly/detail")
    @ApiOperation(value = "周报详情")
    public DataResponse<WeeklyReportResDTO> detailWeekly(@RequestParam String id) {
        return DataResponse.of(vehicleReportService.detailWeekly(id));
    }

    /**
     * 新增周报
     * @param weeklyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/weekly/add")
    @ApiOperation(value = "新增-周报")
    public DataResponse<T> addWeekly(@RequestBody WeeklyReportReqDTO weeklyReportReqDTO) {
        vehicleReportService.addWeekly(weeklyReportReqDTO);
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
        vehicleReportService.modifyWeekly(weeklyReportReqDTO);
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
        vehicleReportService.deleteWeekly(baseIdsEntity.getIds());
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
        return PageResponse.of(vehicleReportService.pageMonthly(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取月报详情
     * @param id id
     * @return 周报详情
     */
    @GetMapping("/monthly/detail")
    @ApiOperation(value = "月报详情")
    public DataResponse<MonthlyReportResDTO> detailMonthly(@RequestParam String id) {
        return DataResponse.of(vehicleReportService.detailMonthly(id));
    }

    /**
     * 新增月报
     * @param monthlyReportReqDTO 周报参数
     * @return 成功
     */
    @PostMapping("/monthly/add")
    @ApiOperation(value = "新增-月报")
    public DataResponse<T> addMonthly(@RequestBody MonthlyReportReqDTO monthlyReportReqDTO) {
        vehicleReportService.addMonthly(monthlyReportReqDTO);
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
        vehicleReportService.modifyMonthly(monthlyReportReqDTO);
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
        vehicleReportService.deleteMonthly(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
