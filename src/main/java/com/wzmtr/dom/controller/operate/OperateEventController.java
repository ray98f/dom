package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.LineEventInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventInfoResDTO;
import com.wzmtr.dom.dto.res.vehicle.LineEventResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.LineEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营-事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 19:19
 */
@RestController
@RequestMapping("/vehicle/lineEvent")
@Api(tags = "车辆部-正线/车场事件 ")
@Validated
public class OperateEventController {

    @Autowired
    private LineEventService lineEventService;

    /**
     * 正线/车场事件-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "正线/车场事件-列表")
    public PageResponse<LineEventResDTO> page(@RequestParam  String dataType,
                                              @RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(lineEventService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 正线/车场事件-详情
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "正线/车场事件记录详情")
    public DataResponse<LineEventDetailResDTO> add(@RequestParam String id) {
        return DataResponse.of(lineEventService.detail(id));
    }

    /**
     * 正线/车场事件-新增
     * @param lineEventRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "正线/车场事件-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody LineEventRecordReqDTO lineEventRecordReqDTO) {
        lineEventService.add(currentLoginUser,lineEventRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 正线/车场事件信息-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 正线/车场事件列表
     */
    @GetMapping("/eventList")
    @ApiOperation(value = "正线/车场事件信息-列表")
    public PageResponse<LineEventInfoResDTO> eventList(@RequestParam String startDate,
                                                  @RequestParam String endDate,
                                                  @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(lineEventService.eventList(startDate,endDate,pageReqDTO));
    }

    /**
     * 事件信息-新增
     * @param lineEventInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> createEvent(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody LineEventInfoReqDTO lineEventInfoReqDTO) {
        lineEventService.createEvent(currentLoginUser,lineEventInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 事件信息-编辑
     * @param lineEventInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> modifyEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody LineEventInfoReqDTO lineEventInfoReqDTO) {
        lineEventService.modifyEvent(currentLoginUser,lineEventInfoReqDTO);
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
        lineEventService.deleteEvent(baseIdsEntity.getIds());
        return DataResponse.success();
    }

}
