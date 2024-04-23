package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.operate.*;
import com.wzmtr.dom.dto.res.operate.IndicatorDetailResDTO;
import com.wzmtr.dom.dto.res.operate.IndicatorRecordResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventInfoResDTO;
import com.wzmtr.dom.dto.res.operate.OperateEventResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.OperateEventService;
import com.wzmtr.dom.service.operate.OperateIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营日报-初期运营指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 19:19
 */
@RestController
@RequestMapping("/operate/indicator")
@Api(tags = "运营日报-初期运营指标 ")
@Validated
public class OperateIndicatorController {

    @Autowired
    private OperateIndicatorService indicatorService;

    /**
     * 初期运营指标-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 初期运营指标-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "初期运营指标-列表")
    public PageResponse<IndicatorRecordResDTO> list(@RequestParam  String dataType,
                                                    @RequestParam(required = false) String startDate,
                                                    @RequestParam(required = false) String endDate,
                                                    @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(indicatorService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 初期运营指标-详情
     * @param id 记录ID
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "初期运营指标详情")
    public DataResponse<IndicatorDetailResDTO> add(@RequestParam String id,
                                                   @RequestParam(required = false) String startDate,
                                                   @RequestParam(required = false) String endDate) {
        return DataResponse.of(indicatorService.detail(id,startDate,endDate));
    }

    /**
     * 初期运营指标-新增
     * @param indicatorRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "初期运营指标-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody IndicatorRecordReqDTO indicatorRecordReqDTO) {
        indicatorService.add(currentLoginUser,indicatorRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 初期运营指标-编辑
     * @param indicatorRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "初期运营指标-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody IndicatorRecordReqDTO indicatorRecordReqDTO) {
        indicatorService.modify(currentLoginUser,indicatorRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 初期运营指标-编辑八项指标
     * @param indicatorInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyInfo")
    @ApiOperation(value = "初期运营指标-编辑八项指标")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.create.class})
                                  @RequestBody IndicatorInfoReqDTO indicatorInfoReqDTO) {
        indicatorService.modifyInfo(currentLoginUser,indicatorInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 初期运营指标-编辑能耗
     * @param indicatorPowerReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyPower")
    @ApiOperation(value = "初期运营指标-编辑能耗")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.create.class})
                                  @RequestBody IndicatorPowerReqDTO indicatorPowerReqDTO) {
        indicatorService.modifyPower(currentLoginUser,indicatorPowerReqDTO);
        return DataResponse.success();
    }

}
