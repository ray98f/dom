package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.onewaysale.OnewaySaleListReqDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.oneway.OnewaySaleListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.OnewaySaleService;
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
 * 客运部-单程票发售情况
 * @Author: Li.Wang
 * Date: 2024/3/22 14:00
 */
@RestController
@RequestMapping("/traffic/oneway/sale")
@Api(tags = "客运部-单程票发售情况")
@Validated
public class OnewaySaleController {

    @Autowired
    private OnewaySaleService onewaySaleService;
    /**
     * 单程票发售情况-列表
     * @return 单程票发售情况
     */
    @PostMapping("/list")
    @ApiOperation(value = "单程票发售情况-列表")
    public PageResponse<OnewaySaleListResDTO> page(@RequestBody OnewaySaleListReqDTO reqDTO) {
        return PageResponse.of(onewaySaleService.list(reqDTO));
    }
    /**
     * 单程票发售情况-详情
     * @return 单程票发售情况
     */
    @PostMapping("/detail")
    @ApiOperation(value = "单程票发售情况")
    public DataResponse<OnewaySaleDetailResDTO> detail(@RequestBody SidReqDTO reqDTO) {
        return DataResponse.of(onewaySaleService.detail(reqDTO));
    }

    /**
     * 单程票发售情况-新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "单程票发售情况-新增")
    public DataResponse<T> add(@RequestBody OnewaySaleAddReqDTO reqDTO) {
        onewaySaleService.add(reqDTO);
        return DataResponse.success();
    }

    /**
     * 单程票发售情况-编辑
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "单程票发售情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody OnewaySaleAddReqDTO req) {
        onewaySaleService.modify(currentLoginUser,req);
        return DataResponse.success();
    }
}
