package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.DispatchOrderReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DispatchRecordReqDTO;
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
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 调度命令记录列表
     */
    @GetMapping("/record/page")
    @ApiOperation(value = "分页查询调度命令记录列表")
    public PageResponse<DispatchRecordResDTO> pageRecord(@RequestParam(required = false) String startDate,
                                                         @RequestParam(required = false) String endDate,
                                                         @RequestParam String dataType,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dispatchService.pageRecord(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 分页查询调度命令详情列表
     * @param recordId 记录id
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 调度命令详情列表
     */
    @GetMapping("/order/page")
    @ApiOperation(value = "获取调度命令详情列表")
    public PageResponse<DispatchOrderResDTO> pageOrder(@RequestParam String recordId,
                                                       @RequestParam String dataType,
                                                       @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dispatchService.pageOrder(recordId, dataType, pageReqDTO));
    }

    @GetMapping("/order/list")
    @ApiOperation(value = "获取调度命令详情列表")
    public DataResponse<List<DispatchOrderResDTO>> orderList(@RequestParam(required = false) String startDate,
                                               @RequestParam(required = false) String endDate,
                                               @RequestParam(required = false) String dataType,
                                               @RequestParam(required = false) String recordId) {
        return DataResponse.of(dispatchService.orderList(recordId, dataType, startDate,endDate));
    }

    /**
     * 提取施工调度调度命令数据
     * @param id 记录id
     * @param dataType 数据类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 调度命令数据
     */
    @GetMapping("/csm")
    @ApiOperation(value = "提取施工调度调度命令数据")
    public DataResponse<List<DispatchOrderResDTO>> getCsmDispatch(@RequestParam(required = false) String startDate,
                                                                  @RequestParam(required = false) String endDate,
                                                                  @RequestParam(required = false) String id,
                                                                  @RequestParam(required = false) String dataType) {
        return DataResponse.of(dispatchService.getCsmDispatch(id,dataType,startDate,endDate));
    }

    /**
     * 新增调度命令记录
     * @param dispatchRecordReqDTO 调度命令记录参数
     * @return 成功
     */
    @PostMapping("/record/add")
    @ApiOperation(value = "新增调度命令记录")
    public DataResponse<T> addRecord(@RequestBody DispatchRecordReqDTO dispatchRecordReqDTO) {
        dispatchService.addRecord(dispatchRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑调度命令详情
     * @param dispatchOrderReqDTO 调度命令详情参数
     * @return 成功
     */
    @PostMapping("/order/modify")
    @ApiOperation(value = "编辑调度命令详情")
    public DataResponse<T> modifyOrder(@RequestBody DispatchOrderReqDTO dispatchOrderReqDTO) {
        dispatchService.modifyOrder(dispatchOrderReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除调度命令记录
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/record/delete")
    @ApiOperation(value = "删除调度命令记录")
    public DataResponse<T> deleteRecord(@RequestBody BaseIdsEntity baseIdsEntity) {
        dispatchService.deleteRecord(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 删除调度命令详情
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/order/delete")
    @ApiOperation(value = "删除调度命令详情")
    public DataResponse<T> deleteOrder(@RequestBody BaseIdsEntity baseIdsEntity) {
        dispatchService.deleteOrder(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
