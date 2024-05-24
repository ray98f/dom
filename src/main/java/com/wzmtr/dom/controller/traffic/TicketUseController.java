package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.dto.req.traffic.TicketUseReqDTO;
import com.wzmtr.dom.dto.res.traffic.TicketUseResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.TicketUseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-线网车票过闸使用情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@RestController
@RequestMapping("/traffic/ticket/use")
@Api(tags = "客运部-线网车票过闸使用情况")
@Validated
public class TicketUseController {

    @Autowired
    private TicketUseService ticketUseService;

    /**
     * 分页查询线网车票过闸使用情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 线网车票过闸使用情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "线网车票过闸使用情况列表(分页)")
    public PageResponse<TicketUseResDTO> page(@RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @RequestParam String dataType,
                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(ticketUseService.page(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 获取线网车票过闸使用情况详情
     * @param id id
     * @return 线网车票过闸使用情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "线网车票过闸使用情况详情")
    public DataResponse<TicketUseResDTO> detail(@RequestParam(required = false) String id,@RequestParam(required = false) String startDate,
                                                @RequestParam(required = false) String endDate) {
        return DataResponse.of(ticketUseService.detail(id,startDate,endDate));
    }

    /**
     * 获取ACC系统线网车票过闸使用情况
     * @param dataType 数据类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 线网车票过闸使用情况
     */
    @GetMapping("/acc")
    @ApiOperation(value = "获取ACC系统线网车票过闸使用情况")
    public DataResponse<TicketUseResDTO> acc(@RequestParam String dataType,
                                             @RequestParam String startDate,
                                             @RequestParam String endDate) {
        return DataResponse.of(ticketUseService.acc(dataType, startDate, endDate));
    }

    /**
     * 新增线网车票过闸使用情况
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-线网车票过闸使用情况")
    public DataResponse<T> add(@RequestBody TicketUseReqDTO ticketUseReqDTO) {
        ticketUseService.add(ticketUseReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑线网车票过闸使用情况
     * @param ticketUseReqDTO 线网车票过闸使用情况参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-线网车票过闸使用情况")
    public DataResponse<T> modify(@RequestBody TicketUseReqDTO ticketUseReqDTO) {
        ticketUseService.modify(ticketUseReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除线网车票过闸使用情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-线网车票过闸使用情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        ticketUseService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
