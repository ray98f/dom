package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.DispatchHandoverReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchHandoverResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.DispatchHandoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-车场调度员交接班情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@RestController
@RequestMapping("/vehicle/dispatch/handover")
@Api(tags = "车辆部-车场调度员交接班情况")
@Validated
public class DispatchHandoverController {

    @Autowired
    private DispatchHandoverService dispatchHandoverService;

    /**
     * 分页查询车场调度员交接班情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 车场调度员交接班情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "车场调度员交接班情况列表(分页)")
    public PageResponse<DispatchHandoverResDTO> page(@RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dispatchHandoverService.page(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取车场调度员交接班情况详情
     * @param id id
     * @return 车场调度员交接班情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "车场调度员交接班情况详情")
    public DataResponse<DispatchHandoverResDTO> detail(@RequestParam("id") String id) {
        return DataResponse.of(dispatchHandoverService.detail(id));
    }

    /**
     * 新增车场调度员交接班情况
     * @param dispatchHandoverReqDTO 车场调度员交接班情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-车场调度员交接班情况")
    public DataResponse<T> add(@RequestBody DispatchHandoverReqDTO dispatchHandoverReqDTO) {
        dispatchHandoverService.add(dispatchHandoverReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑车场调度员交接班情况
     * @param dispatchHandoverReqDTO 车场调度员交接班情况参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-车场调度员交接班情况")
    public DataResponse<T> modify(@RequestBody DispatchHandoverReqDTO dispatchHandoverReqDTO) {
        dispatchHandoverService.modify(dispatchHandoverReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除车场调度员交接班情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-车场调度员交接班情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        dispatchHandoverService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
