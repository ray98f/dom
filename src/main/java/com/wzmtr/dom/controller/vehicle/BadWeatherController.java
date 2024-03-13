package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.BadWeatherReqDTO;
import com.wzmtr.dom.dto.res.vehicle.BadWeatherResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.BadWeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-恶劣天气组织
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@RestController
@RequestMapping("/vehicle/bad/weather")
@Api(tags = "车辆部-恶劣天气组织")
@Validated
public class BadWeatherController {

    @Autowired
    private BadWeatherService badWeatherService;

    /**
     * 分页查询恶劣天气组织列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 恶劣天气组织列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "恶劣天气组织列表(分页)")
    public PageResponse<BadWeatherResDTO> page(@RequestParam(required = false) String startDate,
                                               @RequestParam(required = false) String endDate,
                                               @RequestParam String dataType,
                                               @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(badWeatherService.page(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 获取恶劣天气组织详情
     * @param id id
     * @return 恶劣天气组织详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "恶劣天气组织详情")
    public DataResponse<BadWeatherResDTO> detail(@RequestParam String id) {
        return DataResponse.of(badWeatherService.detail(id));
    }

    /**
     * 新增恶劣天气组织
     * @param badWeatherReqDTO 恶劣天气组织参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-恶劣天气组织")
    public DataResponse<T> add(@RequestBody BadWeatherReqDTO badWeatherReqDTO) {
        badWeatherService.add(badWeatherReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑恶劣天气组织
     * @param badWeatherReqDTO 恶劣天气组织参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-恶劣天气组织")
    public DataResponse<T> modify(@RequestBody BadWeatherReqDTO badWeatherReqDTO) {
        badWeatherService.modify(badWeatherReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除恶劣天气组织
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-恶劣天气组织(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        badWeatherService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
