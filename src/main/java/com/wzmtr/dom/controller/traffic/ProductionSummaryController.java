package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.traffic.ProductionSummaryRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.MonthSummaryResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionSummaryResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.ProductionSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客运部-安全生产情况汇总
 * @author  zhangxin
 * @version 1.0
 * @date 2024/03/19
 */
@RestController
@RequestMapping("/traffic/production/summary")
@Api(tags = "客运部-安全生产情况汇总")
@Validated
public class ProductionSummaryController {

    @Autowired
    private ProductionSummaryService productionSummaryService;

    /**
     * 安全生产情况汇总-列表
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param dataType   数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 安全生产情况汇总-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "安全生产情况汇总-列表")
    public PageResponse<ProductionSummaryResDTO> page(@CurrUser CurrentLoginUser currentLoginUser,
                                                      @RequestParam(required = false) String startDate,
                                                      @RequestParam(required = false) String endDate,
                                                      @RequestParam String dataType,
                                                      @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(productionSummaryService.list(dataType, currentLoginUser.getStationCode(), startDate, endDate, pageReqDTO));
    }

    /**
     * 安全生产情况汇总-详情
     * @param id id
     * @return 安全生产情况汇总详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "安全生产情况汇总-详情")
    public DataResponse<ProductionSummaryResDTO> detail(@RequestParam(required = false) String id, @RequestParam(required = false) String startDate,
                                                        @RequestParam(required = false) String endDate) {
        return DataResponse.of(productionSummaryService.detail(id, startDate, endDate));
    }

    /**
     * 安全生产情况汇总-新增
     * @param productionSummaryRecordReqDTO 安全生产情况汇总参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "安全生产情况汇总-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO) {
        productionSummaryService.add(currentLoginUser, productionSummaryRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 安全生产情况汇总-编辑
     * @param productionSummaryRecordReqDTO 安全生产情况汇总参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "安全生产情况汇总-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody ProductionSummaryRecordReqDTO productionSummaryRecordReqDTO) {
        productionSummaryService.modify(currentLoginUser, productionSummaryRecordReqDTO);
        return DataResponse.success();
    }

}
