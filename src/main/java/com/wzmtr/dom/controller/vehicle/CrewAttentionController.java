package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.vehicle.CrewAttentionReqDTO;
import com.wzmtr.dom.dto.res.vehicle.CrewAttentionResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.CrewAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-乘务中心注意事项
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@RestController
@RequestMapping("/vehicle/crew/attention")
@Api(tags = "车辆部-乘务中心注意事项")
@Validated
public class CrewAttentionController {

    @Autowired
    private CrewAttentionService crewAttentionService;

    /**
     * 分页查询乘务中心注意事项列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 乘务中心注意事项列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "乘务中心注意事项列表(分页)")
    public PageResponse<CrewAttentionResDTO> page(@RequestParam(required = false) String startDate,
                                                  @RequestParam(required = false) String endDate,
                                                  @RequestParam String dataType,
                                                  @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(crewAttentionService.page(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 获取乘务中心注意事项详情
     * @param id id
     * @return 乘务中心注意事项详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "乘务中心注意事项详情")
    public DataResponse<CrewAttentionResDTO> detail(@RequestParam String id) {
        return DataResponse.of(crewAttentionService.detail(id));
    }

    /**
     * 新增乘务中心注意事项
     * @param crewAttentionReqDTO 乘务中心注意事项参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-乘务中心注意事项")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody CrewAttentionReqDTO crewAttentionReqDTO) {
        crewAttentionService.add(currentLoginUser,crewAttentionReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑乘务中心注意事项
     * @param crewAttentionReqDTO 乘务中心注意事项参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-乘务中心注意事项")
    public DataResponse<T> modify(@RequestBody CrewAttentionReqDTO crewAttentionReqDTO) {
        crewAttentionService.modify(crewAttentionReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除乘务中心注意事项
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-乘务中心注意事项(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        crewAttentionService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
