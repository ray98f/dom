package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.traffic.PassengerInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.PassengerRecordReqDTO;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.PassengerResDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-客流总体情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 14:10
 */
@RestController
@RequestMapping("/traffic/passenger")
@Api(tags = "客运部-客流总体情况")
@Validated
public class PassengerController {

    @Autowired
    private PassengerService trafficService;

    /**
     * 客流总体情况-列表
     * @param pageReqDTO 分页参数
     * @return 客流总体情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "客流总体情况-列表")
    public PageResponse<PassengerResDTO> page(@RequestParam String dataType,
                                              @RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(trafficService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 客流总体情况-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "客流总体情况")
    public DataResponse<PassengerDetailResDTO> add(@RequestParam(required = false) String id,@RequestParam(required = false) String startDate,
                                                   @RequestParam(required = false) String endDate) {
        return DataResponse.of(trafficService.detail(id,startDate,endDate));
    }

    /**
     * 客流总体情况-新增
     * @param passengerRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "客流总体情况-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody PassengerRecordReqDTO passengerRecordReqDTO) {
        trafficService.add(currentLoginUser,passengerRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 客流总体情况-编辑
     * @param passengerRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "客流总体情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody PassengerRecordReqDTO passengerRecordReqDTO) {
        trafficService.modify(currentLoginUser,passengerRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 车站客流-编辑
     * @param passengerInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyStationPassenger")
    @ApiOperation(value = "客流总体情况-编辑")
    public DataResponse<T> modifyStationPassenger(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody PassengerInfoReqDTO passengerInfoReqDTO) {
        trafficService.modifyStationPassenger(currentLoginUser,passengerInfoReqDTO);
        return DataResponse.success();
    }

}
