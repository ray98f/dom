package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.traffic.ImportantWorkReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.ImportantWorkResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.ImportantWorkService;
import com.wzmtr.dom.service.traffic.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-重要工作落实情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 17:13
 */
@RestController
@RequestMapping("/traffic/important")
@Api(tags = "客运部-重要工作落实情况")
@Validated
public class ImportantWorkController {

    @Autowired
    private ImportantWorkService importantWorkService;

    /**
     * 重要工作落实情况-列表
     * @param pageReqDTO 分页参数
     * @return 重要工作落实情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "重要工作落实情况-列表")
    public PageResponse<ImportantWorkResDTO> page(@RequestParam String dataType,
                                                  @RequestParam(required = false) String startDate,
                                                  @RequestParam(required = false) String endDate,
                                                  @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(importantWorkService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 重要工作落实情况-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "重要工作落实情况")
    public DataResponse<ImportantWorkResDTO> add(@RequestParam String id) {
        return DataResponse.of(importantWorkService.detail(id));
    }

    /**
     * 重要工作落实情况-新增
     * @param importantWorkReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要工作落实情况-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody ImportantWorkReqDTO importantWorkReqDTO) {
        importantWorkService.add(currentLoginUser,importantWorkReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要工作落实情况-编辑
     * @param importantWorkReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "重要工作落实情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody ImportantWorkReqDTO importantWorkReqDTO) {
        importantWorkService.modify(currentLoginUser,importantWorkReqDTO);
        return DataResponse.success();
    }
}
