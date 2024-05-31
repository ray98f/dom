package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.HotLineImportantService;
import com.wzmtr.dom.service.traffic.HotLineSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客运部-重要热线内容
 *
 * @Author: Li.Wang
 * Date: 2024/3/22 16:41
 */
@RestController
@RequestMapping("/traffic/hotline/important")
@Api(tags = "客运部-重要热线内容")
@Validated
public class HotLineImportantController {
    @Autowired
    private HotLineImportantService hotLineImportantService;
    @Autowired
    private HotLineSummaryService hotLineSummaryService;

     @PostMapping("/list")
     @ApiOperation(value = "重要热线内容-列表")
     public PageResponse<HotLineSummaryListResDTO> page(@RequestBody HotLineSummaryListReqDTO reqDTO) {
         return PageResponse.of(hotLineSummaryService.list(reqDTO));
     }

    /**
     * 重要热线内容-详情
     *
     * @return 重要热线内容
     */
    @GetMapping("/detail")
    @ApiOperation(value = "重要热线内容")
    public DataResponse<List<HotLineImportantDetailResDTO>> detail(@RequestParam(required = false) String date,
                                                                   @RequestParam(required = false) String startDate,
                                                                   @RequestParam(required = false) String endDate,
                                                                   @RequestParam(required = false) String dataType) {
        return DataResponse.of(hotLineImportantService.detail(date, startDate, endDate, dataType));
    }

    @PostMapping("/acc")
    @ApiOperation(value = "重要热线内容")
    public DataResponse<List<HotLineImportantDetailResDTO>> acc(@RequestBody SidReqDTO reqDTO) {
        return DataResponse.of(hotLineImportantService.acc(reqDTO));
    }

    /**
     * 重要热线内容-新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要热线内容-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,@RequestBody HotLineImportantAddReqDTO reqDTO) {
        hotLineImportantService.add(currentLoginUser,reqDTO);
        return DataResponse.success();
    }

    /**
     * 重要热线内容-编辑
     *
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "重要热线内容-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody HotLineImportantAddReqDTO req) {
        hotLineImportantService.modify(currentLoginUser, req);
        return DataResponse.success();
    }
}
