package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.traffic.ProductionApprovalReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionInfoReqDTO;
import com.wzmtr.dom.dto.req.traffic.ProductionRecordReqDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionApprovalResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionInfoResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.ProductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-安全生产情况
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/19 17:13
 */
@RestController
@RequestMapping("/traffic/production")
@Api(tags = "客运部-安全生产情况汇总")
@Validated
public class ProductionController {

    @Autowired
    private ProductionService productionService;

    /**
     * 安全生产情况记录-列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 安全生产情况记录-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "安全生产情况记录-列表")
    public PageResponse<ProductionRecordResDTO> list(@CurrUser CurrentLoginUser currentLoginUser,
                                                     @RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @RequestParam String dataType,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(productionService.list(dataType,currentLoginUser.getStationCode(),startDate, endDate, pageReqDTO));
    }

    /**
     * 安全生产情况记录-列表2
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 安全生产情况记录-列表2
     */
    @GetMapping("/listByStation")
    @ApiOperation(value = "安全生产情况记录-列表2")
    public PageResponse<ProductionRecordResDTO> listByStation(@RequestParam String stationCode,
                                                     @RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @RequestParam String dataType,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(productionService.list(dataType,stationCode,startDate, endDate, pageReqDTO));
    }

    /**
     * 安全生产情况-详情
     * @param id id
     * @return 安全生产情况-详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "安全生产情况-详情")
    public DataResponse<ProductionDetailResDTO> detail(@RequestParam(required = false) String id,@RequestParam(required = false) String startDate,
                                                       @RequestParam(required = false) String endDate) {
        return DataResponse.of(productionService.detail(id,startDate,endDate));
    }

    /**
     * 安全生产情况-详情2
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @return 安全生产情况-详情2
     */
    @GetMapping("/queryInfo")
    @ApiOperation(value = "安全生产情况-详情")
    public DataResponse<ProductionDetailResDTO> queryInfo(@RequestParam String dataType,@RequestParam String stationCode,@RequestParam String startDate,
                                                          @RequestParam String endDate) {
        return DataResponse.of(productionService.queryInfo(dataType,stationCode,startDate,endDate));
    }

    /**
     * 安全生产情况-新增
     * @param productionRecordReqDTO 安全生产情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "安全生产情况记录-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody ProductionRecordReqDTO productionRecordReqDTO) {
        productionService.add(currentLoginUser,productionRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 安全生产事件-列表
     * @param stationCode 车站编码
     * @param productionType 事件类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 安全生产事件-列表
     */
    @GetMapping("/eventList")
    @ApiOperation(value = "正线/车场事件信息-列表")
    public PageResponse<ProductionInfoResDTO> eventList(@RequestParam String stationCode,
                                                        @RequestParam(required = false) String productionType,
                                                        @RequestParam String startDate,
                                                        @RequestParam String endDate,
                                                        @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(productionService.eventList(stationCode,productionType,startDate,endDate,pageReqDTO));
    }

    /**
     * 安全生产事件-新增
     * @param productionInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/createEvent")
    @ApiOperation(value = "安全生产事件-新增")
    public DataResponse<T> createEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody ProductionInfoReqDTO productionInfoReqDTO) {
        productionService.createEvent(currentLoginUser,productionInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 事件信息-编辑
     * @param productionInfoReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modifyEvent")
    @ApiOperation(value = "事件信息-新增")
    public DataResponse<T> modifyEvent(@CurrUser CurrentLoginUser currentLoginUser,
                                       @Validated({ValidationGroup.create.class})
                                       @RequestBody ProductionInfoReqDTO productionInfoReqDTO) {
        productionService.modifyEvent(currentLoginUser,productionInfoReqDTO);
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
        productionService.deleteEvent(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 安全生产情况待审列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 成功
     */
    @GetMapping("/queryApproval")
    @ApiOperation(value = "安全生产情况待审列表")
    public PageResponse<ProductionApprovalResDTO> queryApproval(@CurrUser CurrentLoginUser currentLoginUser,
                                                                @RequestParam(required = false) String startDate,
                                                                @RequestParam(required = false) String endDate,
                                                                @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(productionService.queryApproval(currentLoginUser,startDate, endDate, pageReqDTO));
    }

    /**
     * 安全生产情况提交审核-新增
     * @param productionRecordReqDTO 安全生产情况参数
     * @return 成功
     */
    @PostMapping("/submitApproval")
    @ApiOperation(value = "安全生产情况提交审核-新增")
    public DataResponse<T> submitApproval(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody ProductionRecordReqDTO productionRecordReqDTO) {
        productionService.submitApproval(currentLoginUser,productionRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 安全生产情况审核
     * @param productionApprovalReqDTO 安全生产情况参数
     * @return 成功
     */
    @PostMapping("/productionApproval")
    @ApiOperation(value = "安全生产情况审核")
    public DataResponse<T> productionApproval(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody ProductionApprovalReqDTO productionApprovalReqDTO) {
        productionService.productionApproval(currentLoginUser,productionApprovalReqDTO);
        return DataResponse.success();
    }
}
