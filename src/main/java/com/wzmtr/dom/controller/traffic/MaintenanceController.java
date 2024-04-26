package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.traffic.MainWorkReqDTO;
import com.wzmtr.dom.dto.req.traffic.MaintenanceInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.MaintenanceRecordReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.res.traffic.MainWorkResDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.MaintenanceRecordResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventInfoResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.MainWorkService;
import com.wzmtr.dom.service.traffic.MaintenanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-设备维保施工情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 17:13
 */
@RestController
@RequestMapping("/traffic/maintenance")
@Api(tags = "客运部-设备维保施工情况")
@Validated
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    /**
     * 设备维保施工情况-列表
     * @param pageReqDTO 分页参数
     * @return 设备维保施工情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "设备维保施工情况-列表")
    public PageResponse<MaintenanceRecordResDTO> page(@RequestParam String dataType,
                                                      @RequestParam(required = false) String startDate,
                                                      @RequestParam(required = false) String endDate,
                                                      @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(maintenanceService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 设备维保施工情况-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "设备维保施工情况")
    public DataResponse<MaintenanceRecordResDTO> add(@RequestParam(required = false) String id  , @RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate) {
        return DataResponse.of(maintenanceService.detail(id,startDate,endDate));
    }

    /**
     * 设备维保施工情况-新增
     * @param maintenanceRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "设备维保施工情况-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody MaintenanceRecordReqDTO maintenanceRecordReqDTO) {
        maintenanceService.add(currentLoginUser,maintenanceRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 维修信息-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 维修信息-列表
     */
    @GetMapping("/infoList")
    @ApiOperation(value = "维修信息-列表")
    public PageResponse<MaintenanceInfoResDTO> eventList(@RequestParam String startDate,
                                                         @RequestParam String endDate,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(maintenanceService.eventList(startDate,endDate,pageReqDTO));
    }

    /**
     * 维修事件信息-新增
     * @param maintenanceInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createEvent")
    @ApiOperation(value = "维修事件信息-新增")
    public DataResponse<T> createEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                      @RequestBody MaintenanceInfoReqDTO maintenanceInfoReqDTO) {
        maintenanceService.createEvent(currentLoginUser,maintenanceInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 维修事件信息-编辑
     * @param maintenanceInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> modifyEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody MaintenanceInfoReqDTO maintenanceInfoReqDTO) {
        maintenanceService.modifyEvent(currentLoginUser,maintenanceInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 维修事件信息-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/deleteInfo")
    @ApiOperation(value = "事件信息-删除)")
    public DataResponse<T> deleteEvent(@RequestBody BaseIdsEntity baseIdsEntity) {
        maintenanceService.deleteEvent(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
