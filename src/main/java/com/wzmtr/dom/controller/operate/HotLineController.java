package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.dto.req.operate.HotLineReqDTO;
import com.wzmtr.dom.dto.res.operate.HotLineResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.HotLineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营-热线服务情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@RestController
@RequestMapping("/operate/hotline")
@Api(tags = "运营-服务热线情况")
@Validated
public class HotLineController {
    @Autowired
    private HotLineService hotLineService;

    /**
     * 分页查询服务热线情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 服务热线情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "服务热线情况列表(分页)")
    public PageResponse<HotLineSummaryDetailResDTO> page(@RequestParam(required = false) String startDate,
                                                         @RequestParam(required = false) String endDate,
                                                         @RequestParam String dataType,
                                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(hotLineService.page(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 获取服务热线情况详情
     * @param id id
     * @return 服务热线情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "服务热线情况详情")
    public DataResponse<HotLineResDTO> detail(@RequestParam String id,@RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate) {
        return DataResponse.of(hotLineService.detail(id,startDate,endDate));
    }

    /**
     * 新增服务热线情况
     * @param hotLineReqDTO 服务热线情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-服务热线情况")
    public DataResponse<T> add(@RequestBody HotLineReqDTO hotLineReqDTO) {
        hotLineService.add(hotLineReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除服务热线情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-服务热线情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        hotLineService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
