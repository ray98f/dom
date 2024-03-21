package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.DrivingDepotReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingInfoReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DrivingRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingRecordDetailResDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.DrivingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-行车情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 11:12
 */
@RestController
@RequestMapping("/vehicle/driving")
@Api(tags = "车辆部-行车情况")
@Validated
public class DrivingController {

    @Autowired
    private DrivingService drivingService;

    /**
     * 行车情况-列表
     * @param pageReqDTO 分页参数
     * @return 行车情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "行车情况-列表")
    public PageResponse<DrivingRecordResDTO> page(@RequestParam String dataType,
                                                  @RequestParam(required = false) String startDate,
                                                  @RequestParam(required = false) String endDate,
                                                  @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(drivingService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 行车情况-详情
     * @param recordId 记录ID
     * @return 行车情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "行车情况-详情")
    public DataResponse<DrivingRecordDetailResDTO> detail(@RequestParam String recordId) {
        return DataResponse.of(drivingService.detail(recordId));
    }

    /**
     * 行车情况-新增
     * @param drivingRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "行车情况-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody DrivingRecordReqDTO drivingRecordReqDTO) {
        drivingService.add(currentLoginUser,drivingRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 行车情况-编辑
     * @param drivingRecordReqDTO 入参:情况说明、版本
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "行车情况-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody DrivingRecordReqDTO drivingRecordReqDTO) {
        drivingService.modify(currentLoginUser,drivingRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 行车情况-数据提取
     * @param recordId 记录ID
     * @return 成功
     */
    @GetMapping("/syncData")
    @ApiOperation(value = "行车情况-数据提取")
    public DataResponse<T> syncData(@CurrUser CurrentLoginUser currentLoginUser,
            @RequestParam String recordId) {
        drivingService.syncData(currentLoginUser,recordId);
        return DataResponse.success();
    }

    /**
     * 行车情况-车场数据编辑
     * @param drivingDepotReqDTO 入参
     */
    @PostMapping("/depotModify")
    @ApiOperation(value = "行车情况-车场数据编辑")
    public DataResponse<T> depotModify(@CurrUser CurrentLoginUser currentLoginUser,
            @RequestBody DrivingDepotReqDTO drivingDepotReqDTO) {
        drivingService.depotModify(currentLoginUser,drivingDepotReqDTO);
        return DataResponse.success();
    }

    /**
     * 行车情况-司机数据编辑
     * @param drivingInfoResDTO 入参
     */
    @PostMapping("/driverModify")
    @ApiOperation(value = "行车情况-司机数据编辑")
    public DataResponse<T> driverModify(@CurrUser CurrentLoginUser currentLoginUser,
                                      @RequestBody DrivingInfoReqDTO drivingInfoResDTO) {
        drivingService.driverModify(currentLoginUser,drivingInfoResDTO);
        return DataResponse.success();
    }
}
