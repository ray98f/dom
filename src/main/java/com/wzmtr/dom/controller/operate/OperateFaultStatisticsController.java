package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.res.operate.fault.FaultStatisticsResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.OperateFaultStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * 运营-故障统计
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/25 19:19
 */
@RestController
@RequestMapping("/fault/statistics")
@Api(tags = "运营-故障统计")
@Validated
public class OperateFaultStatisticsController {

    @Autowired
    private OperateFaultStatisticsService faultStatisticsService;
    /**
     * 故障统计-列表
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 故障统计-列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "故障统计-列表")
    public PageResponse<FaultStatisticsResDTO> list(@RequestParam  String dataType,
                                                    @RequestParam(required = false) String startDate,
                                                    @RequestParam(required = false) String endDate,
                                                    @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(faultStatisticsService.list(dataType, startDate, endDate, pageReqDTO));
    }

    /**
     * 故障统计-报表详情
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @return 报表详情
     */
    @GetMapping("/report")
    @ApiOperation(value = "故障统计-报表详情")
    public DataResponse<FaultStatisticsResDTO> report(@RequestParam String dataType,
                                                      @RequestParam(required = false) String startDate,
                                                      @RequestParam(required = false) String endDate) throws ParseException {
        return DataResponse.of(faultStatisticsService.report(dataType, startDate, endDate));
    }


    /**
     * 故障统计-新增
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "故障统计-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody OperateFaultStatisticsReqDTO req) {
        faultStatisticsService.add(currentLoginUser,req);
        return DataResponse.success();
    }

    /**
     * 故障统计-编辑
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "故障统计-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody OperateFaultStatisticsReqDTO req) {
        faultStatisticsService.modify(currentLoginUser,req);
        return DataResponse.success();
    }

    /**
     * 故障统计-删除
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "故障统计删除")
    public DataResponse<T> modify(@RequestBody BaseIdsEntity baseIdsEntity) {
        faultStatisticsService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    @GetMapping("/syncData")
    @ApiOperation(value = "同步故障统计")
    public DataResponse<T> syncData(@RequestParam String dataType,
                                                      @RequestParam(required = false) String startDate,
                                                      @RequestParam(required = false) String endDate) throws ParseException {
        faultStatisticsService.syncData(dataType, startDate, endDate);
        return DataResponse.success();
    }


}
