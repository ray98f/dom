package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.operate.OperateEventInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.OperateEventReqDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.OperateEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营日报-运营事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 19:19
 */
@RestController
@RequestMapping("/operate/event")
@Api(tags = "运营日报-运营事件 ")
@Validated
public class OperateEventController {

    @Autowired
    private OperateEventService operateEventService;

    /**
     * 运营事件-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 运营事件-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "运营事件-列表")
    public PageResponse<OperateEventResDTO> list(@RequestParam  String dataType,
                                                 @RequestParam(required = false) String startDate,
                                                 @RequestParam(required = false) String endDate,
                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateEventService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 运营事件-详情
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "运营事件记录详情")
    public DataResponse<OperateEventResDTO> add(@RequestParam String id) {
        return DataResponse.of(operateEventService.detail(id));
    }

    /**
     * 运营事件-新增
     * @param operateEventReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "运营事件-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody OperateEventReqDTO operateEventReqDTO) {
        operateEventService.add(currentLoginUser,operateEventReqDTO);
        return DataResponse.success();
    }

    /**
     * 运营事件信息-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 运营事件列表
     */
    @GetMapping("/eventList")
    @ApiOperation(value = "运营事件信息-列表")
    public PageResponse<OperateEventInfoResDTO> eventList(@RequestParam String startDate,
                                                          @RequestParam String endDate,
                                                          @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operateEventService.eventList(startDate,endDate,pageReqDTO));
    }

    /**
     * 事件信息-新增
     * @param operateEventInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> createEvent(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.class})
                               @RequestBody OperateEventInfoReqDTO operateEventInfoReqDTO) {
        operateEventService.createEvent(currentLoginUser,operateEventInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 事件信息-编辑
     * @param operateEventInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> modifyEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody OperateEventInfoReqDTO operateEventInfoReqDTO) {
        operateEventService.modifyEvent(currentLoginUser,operateEventInfoReqDTO);
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
        operateEventService.deleteEvent(baseIdsEntity.getIds());
        return DataResponse.success();
    }

}
