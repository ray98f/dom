package com.wzmtr.dom.controller.system;

import com.wzmtr.dom.dto.req.system.DailyStatisticsReqDTO;
import com.wzmtr.dom.dto.res.system.DailyStatisticsResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.system.DailyStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统参数-日报统计参数设置
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@RestController
@RequestMapping("/sys/daily/statistics")
@Api(tags = "系统参数-日报统计参数设置")
@Validated
public class DailyStatisticsController {

    @Autowired
    private DailyStatisticsService dailyStatisticsService;

    /**
     * 分页查询日报统计参数设置列表
     * @param pageReqDTO 分页参数
     * @return 日报统计参数设置列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "日报统计参数设置列表(分页)")
    public PageResponse<DailyStatisticsResDTO> page(@Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dailyStatisticsService.page(pageReqDTO));
    }

    /**
     * 获取日报统计参数设置详情
     * @param id id
     * @return 日报统计参数设置详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "日报统计参数设置详情")
    public DataResponse<DailyStatisticsResDTO> detail(@RequestParam("id") Integer id) {
        return DataResponse.of(dailyStatisticsService.detail(id));
    }

    /**
     * 新增日报统计参数设置
     * @param dailyStatisticsReqDTO 日报统计参数设置参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-日报统计参数设置")
    public DataResponse<T> add(@RequestBody DailyStatisticsReqDTO dailyStatisticsReqDTO) {
        dailyStatisticsService.add(dailyStatisticsReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑日报统计参数设置
     * @param dailyStatisticsReqDTO 日报统计参数设置参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-日报统计参数设置")
    public DataResponse<T> modify(@RequestBody DailyStatisticsReqDTO dailyStatisticsReqDTO) {
        dailyStatisticsService.modify(dailyStatisticsReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除日报统计参数设置
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-日报统计参数设置(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        dailyStatisticsService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
