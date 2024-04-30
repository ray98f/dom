package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryDetailReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineSummaryListReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.HotLineSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客运部-服务热线汇总
 * @Author: Li.Wang
 * Date: 2024/3/22 16:41
 */
@RestController
@RequestMapping("/traffic/hotline/summary")
@Api(tags = "客运部-服务热线汇总")
@Validated
public class HotLineSummaryController {
    @Autowired
    private HotLineSummaryService hotLineSummaryService;

    /**
     * 服务热线汇总-列表
     * @return 服务热线汇总
     */
    @PostMapping("/list")
    @ApiOperation(value = "服务热线汇总-列表")
    public PageResponse<HotLineSummaryListResDTO> page(@RequestBody HotLineSummaryListReqDTO reqDTO) {
        return PageResponse.of(hotLineSummaryService.list(reqDTO));
    }

    /**
     * 服务热线汇总-详情
     * @return 服务热线汇总
     */
    @PostMapping("/detail")
    @ApiOperation(value = "服务热线汇总")
    public DataResponse<HotLineSummaryDetailResDTO> detail(@RequestBody HotLineSummaryDetailReqDTO reqDTO) {
        return DataResponse.of(hotLineSummaryService.detail(reqDTO));
    }
    @PostMapping("/acc")
    @ApiOperation(value = "服务热线汇总")
    public DataResponse<HotLineSummaryDetailResDTO> acc(@RequestBody SidReqDTO reqDTO) {
        return DataResponse.of(hotLineSummaryService.acc(reqDTO));
    }
    /**
     * 服务热线汇总-新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "服务热线汇总-新增")
    public DataResponse<T> add(@RequestBody HotLineSummaryAddReqDTO reqDTO) {
        hotLineSummaryService.add(reqDTO);
        return DataResponse.success();
    }

    /**
     * 服务热线汇总-编辑
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "服务热线汇总-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody HotLineSummaryAddReqDTO req) {
        hotLineSummaryService.modify(currentLoginUser,req);
        return DataResponse.success();
    }
}
