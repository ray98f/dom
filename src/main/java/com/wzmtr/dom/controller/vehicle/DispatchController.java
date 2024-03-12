package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.DispatchReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchOrderResDTO;
import com.wzmtr.dom.dto.res.vehicle.DispatchRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.DispatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 车辆部-调度命令管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@RestController
@RequestMapping("/vehicle/dispatch")
@Api(tags = "车辆部-调度命令管理")
@Validated
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    /**
     * 分页查询调度命令记录列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageReqDTO 分页参数
     * @return 调度命令记录列表
     */
    @GetMapping("/record/page")
    @ApiOperation(value = "分页查询调度命令记录列表")
    public PageResponse<DispatchRecordResDTO> pageRecord(@RequestParam(required = false) String startTime,
                                                         @RequestParam(required = false) String endTime,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dispatchService.pageRecord(startTime, endTime, pageReqDTO));
    }

    /**
     * 获取调度命令详情列表
     * @param recordId 记录id
     * @return 调度命令详情列表
     */
    @GetMapping("/order/list")
    @ApiOperation(value = "获取调度命令详情列表")
    public DataResponse<List<DispatchOrderResDTO>> listOrder(@RequestParam("recordId") String recordId) {
        return DataResponse.of(dispatchService.listOrder(recordId));
    }

    /**
     * 提取施工调度调度命令数据
     * @param time 日期
     * @return 调度命令数据
     */
    @GetMapping("/csm")
    @ApiOperation(value = "提取施工调度调度命令数据")
    public DataResponse<List<DispatchOrderResDTO>> getCsmDispatch(@RequestParam(required = false) String time) {
        return DataResponse.of(dispatchService.getCsmDispatch(time));
    }

    /**
     * 新增调度命令
     * @param dispatchReqDTO 调度命令参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增调度命令")
    public DataResponse<T> add(@RequestBody DispatchReqDTO dispatchReqDTO) {
        dispatchService.add(dispatchReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑调度命令
     * @param dispatchReqDTO 调度命令参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑调度命令")
    public DataResponse<T> modify(@RequestBody DispatchReqDTO dispatchReqDTO) {
        dispatchService.modify(dispatchReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除调度命令
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除调度命令")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        dispatchService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
