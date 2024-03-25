package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.income.IncomeListReqDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.IncomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 客运部-收益总体情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 14:10
 */
@RestController
@RequestMapping("/traffic/income")
@Api(tags = "客运部-收益总体情况")
@Validated
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    /**
     * 收益总体情况-列表
     * @return 收益总体情况列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "收益总体情况-列表")
    public PageResponse<IncomeListResDTO> page(@RequestBody IncomeListReqDTO reqDTO) {
        return PageResponse.of(incomeService.list(reqDTO));
    }

    /**
     * 收益总体情况-详情
     */
    @PostMapping("/detail")
    @ApiOperation(value = "收益总体情况")
    public DataResponse<IncomeDetailResDTO> add(@RequestBody SidReqDTO reqDTO) {
        return DataResponse.of(incomeService.detail(reqDTO));
    }

    /**
     * 收益总体情况-新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "收益总体情况-新增")
    public DataResponse<T> add(IncomeAddReqDTO reqDTO) {
        incomeService.add(reqDTO);
        return DataResponse.success();
    }

    /**
     * 收益总体情况-编辑
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "收益总体情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                               @RequestBody IncomeAddReqDTO req) {
        incomeService.modify(currentLoginUser,req);
        return DataResponse.success();
    }


}
