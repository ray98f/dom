package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.CrewEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.CrewEventSummaryReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.res.vehicle.*;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.CrewEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-乘务中心行车事件总结
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/14 8:40
 */
@RestController
@RequestMapping("/vehicle/crew/eventSummary")
@Api(tags = "车辆部-乘务中心行车事件总结")
@Validated
public class CrewEventSummaryController {

    @Autowired
    private CrewEventService crewEventService;

    /**
     * 乘务中心行车事件总结-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "乘务中心行车事件总结-列表")
    public PageResponse<CrewEventSummaryResDTO> list(@RequestParam  String dataType,
                                              @RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(crewEventService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 乘务中心行车事件总结-详情
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "乘务中心行车事件总结详情")
    public DataResponse<CrewEventSummaryResDTO> detail(@RequestParam String id) {
        return DataResponse.of(crewEventService.detail(id));
    }

    /**
     * 乘务中心行车事件总结-新增
     * @param crewEventSummaryReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "乘务中心行车事件总结-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody CrewEventSummaryReqDTO crewEventSummaryReqDTO) {
        crewEventService.add(currentLoginUser,crewEventSummaryReqDTO);
        return DataResponse.success();
    }

    /**
     * 乘务中心行车事件总结-编辑
     * @param crewEventSummaryReqDTO 入参:情况说明、版本
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "乘务中心行车事件总结-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody CrewEventSummaryReqDTO crewEventSummaryReqDTO) {
        crewEventService.modify(currentLoginUser,crewEventSummaryReqDTO);
        return DataResponse.success();
    }

    /**
     * 乘务中心行车事件信息-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 乘务中心行车事件信息列表
     */
    @GetMapping("/eventList")
    @ApiOperation(value = "正线/车场事件信息-列表")
    public PageResponse<CrewEventInfoResDTO> eventList(@RequestParam String startDate,
                                                  @RequestParam String endDate,
                                                  @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(crewEventService.eventList(startDate,endDate,pageReqDTO));
    }

    /**
     * 乘务中心行车事件信息-新增
     * @param crewEventInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createEvent")
    @ApiOperation(value = "乘务中心行车事件信息-新增")
    public DataResponse<T> createEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody CrewEventInfoReqDTO crewEventInfoReqDTO) {
        crewEventService.createEvent(currentLoginUser,crewEventInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 乘务中心行车事件信息-编辑
     * @param crewEventInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyEvent")
    @ApiOperation(value = "乘务中心行车事件信息-新增")
    public DataResponse<T> modifyEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody CrewEventInfoReqDTO crewEventInfoReqDTO) {
        crewEventService.modifyEvent(currentLoginUser,crewEventInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 乘务中心行车事件信息-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/deleteEvent")
    @ApiOperation(value = "事件信息-删除)")
    public DataResponse<T> deleteEvent(@RequestBody BaseIdsEntity baseIdsEntity) {
        crewEventService.deleteEvent(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
