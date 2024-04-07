package com.wzmtr.dom.controller.traffic;


import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
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
    public DataResponse<DailyReportResDTO> detailDaily(@RequestParam String id) {
        return DataResponse.of(reportService.dailyDetail(id));
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






}