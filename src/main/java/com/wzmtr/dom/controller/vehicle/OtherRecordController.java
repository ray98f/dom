package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.dto.req.vehicle.OtherRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.OtherRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.OtherRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-其他情况说明
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@RestController
@RequestMapping("/vehicle/other/record")
@Api(tags = "车辆部-其他情况说明")
@Validated
public class OtherRecordController {

    @Autowired
    private OtherRecordService otherRecordService;

    /**
     * 分页查询其他情况说明列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 其他情况说明列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "其他情况说明列表(分页)")
    public PageResponse<OtherRecordResDTO> page(@RequestParam(required = false) String startTime,
                                                @RequestParam(required = false) String endTime,
                                                @RequestParam String dataType,
                                                @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(otherRecordService.page(startTime, endTime, dataType, pageReqDTO));
    }

    /**
     * 获取其他情况说明详情
     * @param id id
     * @return 其他情况说明详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "其他情况说明详情")
    public DataResponse<OtherRecordResDTO> detail(@RequestParam("id") String id) {
        return DataResponse.of(otherRecordService.detail(id));
    }

    /**
     * 新增其他情况说明
     * @param otherRecordReqDTO 其他情况说明参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-其他情况说明")
    public DataResponse<T> add(@RequestBody OtherRecordReqDTO otherRecordReqDTO) {
        otherRecordService.add(otherRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑其他情况说明
     * @param otherRecordReqDTO 其他情况说明参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-其他情况说明")
    public DataResponse<T> modify(@RequestBody OtherRecordReqDTO otherRecordReqDTO) {
        otherRecordService.modify(otherRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除其他情况说明
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-其他情况说明(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        otherRecordService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
