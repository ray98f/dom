package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.DailyReportReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DailyReportResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.ReportService;
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
public class ReportController {

    @Autowired
    private ReportService reportService;

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
        return PageResponse.of(reportService.pageDaily(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取日报详情
     * @param id id
     * @return 日报详情
     */
    @GetMapping("/daily/detail")
    @ApiOperation(value = "日报详情")
    public DataResponse<DailyReportResDTO> detailDaily(@RequestParam String id) {
        return DataResponse.of(reportService.detailDaily(id));
    }

    /**
     * 新增日报
     * @param dailyReportReqDTO 日报参数
     * @return 成功
     */
    @PostMapping("/daily/add")
    @ApiOperation(value = "新增-日报")
    public DataResponse<T> addDaily(@RequestBody DailyReportReqDTO dailyReportReqDTO) {
        reportService.addDaily(dailyReportReqDTO);
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
        reportService.modifyDaily(dailyReportReqDTO);
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
        reportService.deleteDaily(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
