package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.traffic.MainWorkReqDTO;
import com.wzmtr.dom.dto.res.traffic.MainWorkResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.MainWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-主要工作情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 17:13
 */
@RestController
@RequestMapping("/traffic/mainWork")
@Api(tags = "客运部-主要工作情况")
@Validated
public class MainWorkController {

    @Autowired
    private MainWorkService mainWorkService;

    /**
     * 主要工作情况-列表
     * @param pageReqDTO 分页参数
     * @return 主要工作情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "主要工作情况-列表")
    public PageResponse<MainWorkResDTO> page(@RequestParam String dataType,
                                             @RequestParam(required = false) String startDate,
                                             @RequestParam(required = false) String endDate,
                                             @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(mainWorkService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 主要工作情况-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "主要工作情况")
    public DataResponse<MainWorkResDTO> add(@RequestParam String id) {
        return DataResponse.of(mainWorkService.detail(id));
    }

    /**
     * 主要工作情况-新增
     * @param mainWorkReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "主要工作情况-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody MainWorkReqDTO mainWorkReqDTO) {
        mainWorkService.add(currentLoginUser,mainWorkReqDTO);
        return DataResponse.success();
    }

    /**
     * 主要工作情况-编辑
     * @param mainWorkReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "主要工作情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody MainWorkReqDTO mainWorkReqDTO) {
        mainWorkService.modify(currentLoginUser,mainWorkReqDTO);
        return DataResponse.success();
    }
}
