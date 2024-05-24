package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanBatchReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructRecordReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.vehicle.*;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.DepotConstructService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部- 车场调车/施工情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 9:31
 */
@RestController
@RequestMapping("/vehicle/construct")
@Api(tags = "车辆部- 车场施工情况")
@Validated
public class DepotConstructController {

    @Autowired
    private DepotConstructService depotConstructService;

    /**
     * 车场施工情况记录-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "车场施工情况记录-列表")
    public PageResponse<DepotConstructRecordResDTO> page(@RequestParam  String depotCode,
                                                         @RequestParam  String dataType,
                                                         @RequestParam(required = false) String startDate,
                                                         @RequestParam(required = false) String endDate,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(depotConstructService.list(depotCode,dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 车场施工情况记录-详情
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "车场施工情况记录详情")
    public DataResponse<DepotConstructDetailResDTO> add(@RequestParam String id) {
        return DataResponse.of(depotConstructService.detail(id));
    }

    /**
     * 车场施工情况记录-新增
     * @param depotConstructRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "车场施工情况记录-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody DepotConstructRecordReqDTO depotConstructRecordReqDTO) {
        depotConstructService.add(currentLoginUser,depotConstructRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 车场施工情况记录-编辑
     * @param depotConstructRecordReqDTO 入参:情况说明、版本
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "车场施工情况记录-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody DepotConstructRecordReqDTO depotConstructRecordReqDTO) {
        depotConstructService.modify(currentLoginUser,depotConstructRecordReqDTO);
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
    public PageResponse<ConstructPlanResDTO> getCsmConstructPlan(@RequestParam String depotCode,
                                                                 @RequestParam String startDate,
                                                                 @RequestParam String endDate,
                                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(depotConstructService.getCsmConstructPlan(depotCode,startDate,endDate,pageReqDTO));
    }

    /**
     * 车场施工计划-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 车场施工计划列表
     */
    @GetMapping("/planList")
    @ApiOperation(value = "车场施工计划-列表")
    public PageResponse<DepotConstructPlanResDTO> planList(@RequestParam  String depotCode,
                                                         @RequestParam String startDate,
                                                         @RequestParam String endDate,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(depotConstructService.planList(depotCode,startDate,endDate,pageReqDTO));
    }

    /**
     * 车场施工计划-新增
     * @param depotConstructPlanBatchReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createPlan")
    @ApiOperation(value = "车场施工计划-新增")
    public DataResponse<T> createPlan(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody DepotConstructPlanBatchReqDTO depotConstructPlanBatchReqDTO) {
        depotConstructService.createPlan(currentLoginUser,depotConstructPlanBatchReqDTO);
        return DataResponse.success();
    }

    /**
     * 车场施工计划-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/deletePlan")
    @ApiOperation(value = "车场施工计划-删除")
    public DataResponse<T> createPlan(@RequestBody BaseIdsEntity baseIdsEntity) {
        depotConstructService.deletePlan(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 车场施工情况-数据提取
     * @param recordId 记录ID
     * @return 成功
     */
    @GetMapping("/syncData")
    @ApiOperation(value = "行车情况-数据提取")
    public DataResponse<T> syncData(@CurrUser CurrentLoginUser currentLoginUser,
                                    @RequestParam String recordId) {
        depotConstructService.syncData(currentLoginUser,recordId);
        return DataResponse.success();
    }
}
