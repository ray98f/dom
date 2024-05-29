package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.vehicle.DrivingAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DrivingAttentionResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.DrivingAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-行车注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@RestController
@RequestMapping("/vehicle/driving/attention")
@Api(tags = "车辆部-行车注意事项")
@Validated
public class DrivingAttentionController {

    @Autowired
    private DrivingAttentionService drivingAttentionService;

    /**
     * 分页查询行车注意事项列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 行车注意事项列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "行车注意事项列表(分页)")
    public PageResponse<DrivingAttentionResDTO> page(@RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(drivingAttentionService.page(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取行车注意事项详情
     * @param id id
     * @return 行车注意事项详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "行车注意事项详情")
    public DataResponse<DrivingAttentionResDTO> detail(@RequestParam String id) {
        return DataResponse.of(drivingAttentionService.detail(id));
    }

    /**
     * 新增行车注意事项
     * @param drivingAttentionReqDTO 行车注意事项参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-行车注意事项")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody DrivingAttentionReqDTO drivingAttentionReqDTO) {
        drivingAttentionService.add(currentLoginUser,drivingAttentionReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑行车注意事项
     * @param drivingAttentionReqDTO 行车注意事项参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-行车注意事项")
    public DataResponse<T> modify(@RequestBody DrivingAttentionReqDTO drivingAttentionReqDTO) {
        drivingAttentionService.modify(drivingAttentionReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除行车注意事项
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-行车注意事项(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        drivingAttentionService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
