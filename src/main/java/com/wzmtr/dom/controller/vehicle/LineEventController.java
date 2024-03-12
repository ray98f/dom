package com.wzmtr.dom.controller.vehicle;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.req.vehicle.LineEventRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.service.vehicle.LineEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 车辆部-正线/车场事件
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:19
 */
@RestController
@RequestMapping("/vehicle/lineEvent")
@Api(tags = "车辆部-正线/车场事件 ")
@Validated
public class LineEventController {

    @Autowired
    private LineEventService lineEventService;


    /**
     * 重要指标-列表
     * @param pageReqDTO 分页参数
     * @return 重要指标列表
     */
/*    @GetMapping("/list")
    @ApiOperation(value = "重要指标-列表")
    public PageResponse<IndicatorResDTO> page(@RequestParam String startDate,
                                              @RequestParam String endDate,
                                                    @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(indicatorService.list(startDate,endDate,pageReqDTO));
    }*/

    /**
     * 正线/车场事件-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "正线/车场事件记录详情")
    public DataResponse<IndicatorResDTO> add(@RequestParam String id) {
        return DataResponse.of(lineEventService.detail(id));
    }

    /**
     * 正线/车场事件-新增
     * @param lineEventRecordReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要指标-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody LineEventRecordReqDTO lineEventRecordReqDTO) {
        lineEventService.add(currentLoginUser,lineEventRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-编辑
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
/*    @PostMapping("/modify")
    @ApiOperation(value = "重要指标-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.modify(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }*/

    /**
     * 重要指标-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
/*    @PostMapping("/delete")
    @ApiOperation(value = "重要指标-删除)")
    public DataResponse<T> delete(@RequestBody  BaseIdsEntity baseIdsEntity) {
        indicatorService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }*/

}
