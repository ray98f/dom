package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.CrewDrillReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewDrillResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.CrewDrillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-乘务中心演练情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/13
 */
@RestController
@RequestMapping("/vehicle/crew/drill")
@Api(tags = "车辆部-乘务中心演练情况")
@Validated
public class CrewDrillController {

    @Autowired
    private CrewDrillService crewDrillService;

    /**
     * 分页查询乘务中心演练情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 乘务中心演练情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "乘务中心演练情况列表(分页)")
    public PageResponse<CrewDrillResDTO> page(@RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @RequestParam String dataType,
                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(crewDrillService.page(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 获取乘务中心演练情况详情
     * @param id id
     * @return 乘务中心演练情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "乘务中心演练情况详情")
    public DataResponse<CrewDrillResDTO> detail(@RequestParam String id) {
        return DataResponse.of(crewDrillService.detail(id));
    }

    /**
     * 新增乘务中心演练情况
     * @param crewDrillReqDTO 乘务中心演练情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-乘务中心演练情况")
    public DataResponse<T> add(@RequestBody CrewDrillReqDTO crewDrillReqDTO) {
        crewDrillService.add(crewDrillReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑乘务中心演练情况
     * @param crewDrillReqDTO 乘务中心演练情况参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-乘务中心演练情况")
    public DataResponse<T> modify(@RequestBody CrewDrillReqDTO crewDrillReqDTO) {
        crewDrillService.modify(crewDrillReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除乘务中心演练情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-乘务中心演练情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        crewDrillService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
