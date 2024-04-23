package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanBatchReqDTO;
import com.wzmtr.dom.dto.res.operate.*;
import com.wzmtr.dom.dto.res.vehicle.DepotConstructPlanResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.OperateConstructService;
import com.wzmtr.dom.service.operate.OperateEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 运营日报-施工情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 19:19
 */
@RestController
@RequestMapping("/operate/construct")
@Api(tags = "运营日报-施工情况 ")
@Validated
public class OperateConstructController {

    @Autowired
    private OperateConstructService operateConstructService;

    /**
     * 施工情况-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 施工情况-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "施工情况-列表")
    public PageResponse<ConstructRecordResDTO> list(@RequestParam  String dataType,
                                                 @RequestParam(required = false) String startDate,
                                                 @RequestParam(required = false) String endDate,
                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateConstructService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 施工情况-详情
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "施工情况详情")
    public DataResponse<ConstructRecordResDTO> add(@RequestParam String id,
                                                   @RequestParam(required = false) String startDate,
                                                   @RequestParam(required = false) String endDate) {
        return DataResponse.of(operateConstructService.detail(id,startDate,endDate));
    }

    /**
     * 施工情况-新增
     * @param constructRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "施工情况-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody ConstructRecordReqDTO constructRecordReqDTO) {
        operateConstructService.add(currentLoginUser,constructRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 施工情况-编辑
     * @param constructRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "施工情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody ConstructRecordReqDTO constructRecordReqDTO) {
        operateConstructService.modify(currentLoginUser,constructRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 选择施工计划(查询施工调度系统)-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    @GetMapping("/getCsmConstructPlan")
    @ApiOperation(value = "选择施工计划-列表")
    public PageResponse<ConstructPlanResDTO> getCsmConstructPlan(@RequestParam String startDate,
                                                                 @RequestParam String endDate,
                                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateConstructService.getCsmConstructPlan(startDate,endDate,pageReqDTO));
    }

    /**
     * 施工计划-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    @GetMapping("/planList")
    @ApiOperation(value = "施工计划-列表")
    public PageResponse<ConstructPlanResDTO> planList(@RequestParam String startDate,
                                                           @RequestParam String endDate,
                                                           @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateConstructService.planList(startDate,endDate,pageReqDTO));
    }

    /**
     * 施工计划-新增
     * @param constructPlanBatchReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createPlan")
    @ApiOperation(value = "施工计划-新增")
    public DataResponse<T> createPlan(@CurrUser CurrentLoginUser currentLoginUser,
                                      @RequestBody ConstructPlanBatchReqDTO constructPlanBatchReqDTO) {
        operateConstructService.createPlan(currentLoginUser,constructPlanBatchReqDTO);
        return DataResponse.success();
    }

    /**
     * 施工计划-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/deletePlan")
    @ApiOperation(value = "施工计划-删除")
    public DataResponse<T> createPlan(@RequestBody BaseIdsEntity baseIdsEntity) {
        operateConstructService.deletePlan(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 施工情况-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 施工情况列表
     */
    @GetMapping("/eventList")
    @ApiOperation(value = "施工事件-列表")
    public PageResponse<ConstructEventResDTO> eventList(@RequestParam String startDate,
                                                        @RequestParam String endDate,
                                                        @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateConstructService.eventList(startDate,endDate,pageReqDTO));
    }

    /**
     * 事件信息-新增
     * @param constructEventReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> createEvent(@CurrUser CurrentLoginUser currentLoginUser,
                               @RequestBody ConstructEventReqDTO constructEventReqDTO) {
        operateConstructService.createEvent(currentLoginUser,constructEventReqDTO);
        return DataResponse.success();
    }

    /**
     * 事件信息-编辑
     * @param constructEventReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> modifyEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @RequestBody ConstructEventReqDTO constructEventReqDTO) {
        operateConstructService.modifyEvent(currentLoginUser,constructEventReqDTO);
        return DataResponse.success();
    }

    /**
     * 事件信息-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/deleteEvent")
    @ApiOperation(value = "事件信息-删除)")
    public DataResponse<T> deleteEvent(@RequestBody BaseIdsEntity baseIdsEntity) {
        operateConstructService.deleteEvent(baseIdsEntity.getIds());
        return DataResponse.success();
    }

}
