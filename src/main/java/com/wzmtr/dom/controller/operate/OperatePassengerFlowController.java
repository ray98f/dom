package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.operate.passengerflow.PassengerFlowAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowDetailResDTO;
import com.wzmtr.dom.dto.res.operate.passengerflow.PassengerFlowListResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.OperatePassengerFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营日报-客流
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 19:19
 */
@RestController
@RequestMapping("/operate/passenger/flow")
@Api(tags = "运营日报-客流")
@Validated
public class OperatePassengerFlowController {

    @Autowired
    private OperatePassengerFlowService operatePassengerFlowService;

    /**
     * 客流-列表
     *
     * @param dataType   数据类型
     * @param startDate  起始日期
     * @param endDate    终止日期
     * @param pageReqDTO 分页参数
     * @return 客流-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "客流-列表")
    public PageResponse<PassengerFlowListResDTO> list(@RequestParam String dataType,
                                                      @RequestParam(required = false) String startDate,
                                                      @RequestParam(required = false) String endDate,
                                                      @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(operatePassengerFlowService.list(dataType, startDate, endDate, pageReqDTO));
    }

    /**
     * 客流-详情
     *
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "客流记录详情")
    public DataResponse<PassengerFlowDetailResDTO> add(@RequestParam String id) {
        return DataResponse.of(operatePassengerFlowService.detail(id));
    }

    /**
     * 客流-新增
     *
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "客流-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody PassengerFlowAddReqDTO req) {
        operatePassengerFlowService.add(currentLoginUser, req);
        return DataResponse.success();
    }

    /**
     * 车站客流-编辑
     *
     * @return 成功
     */
    @PostMapping("/modifyStationPassenger")
    @ApiOperation(value = "车站客流-编辑")
    public DataResponse<T> modifyStationPassenger(@RequestBody PassengerInfoReqDTO req) {
        operatePassengerFlowService.modifyStationPassenger(req);
        return DataResponse.success();
    }

    /**
     * 客流信息-编辑
     *
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "客流信息-编辑")
    public DataResponse<T> modify(@RequestBody PassengerFlowAddReqDTO req) {
        operatePassengerFlowService.modify(req);
        return DataResponse.success();
    }

    /**
     * 客流周报列表
     * @return 成功
     */
    @PostMapping("/list/week")
    @ApiOperation(value = "客流周报列表")
    public DataResponse<T> listWeek(@RequestBody PassengerFlowAddReqDTO req) {
        operatePassengerFlowService.modify(req);
        return DataResponse.success();
    }



}
