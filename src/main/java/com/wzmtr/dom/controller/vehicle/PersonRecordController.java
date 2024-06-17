package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.vehicle.PersonRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.PersonRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.PersonRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-当日人员情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@RestController
@RequestMapping("/vehicle/person/record")
@Api(tags = "车辆部-当日人员情况")
@Validated
public class PersonRecordController {

    @Autowired
    private PersonRecordService personRecordService;

    /**
     * 分页查询当日人员情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 当日人员情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "当日人员情况列表(分页)")
    public PageResponse<PersonRecordResDTO> page(@RequestParam(required = false) String startDate,
                                                 @RequestParam(required = false) String endDate,
                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(personRecordService.page(startDate, endDate, pageReqDTO));
    }

    /**
     * 获取当日人员情况详情
     * @param id id
     * @return 当日人员情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "当日人员情况详情")
    public DataResponse<PersonRecordResDTO> detail(@RequestParam String id) {
        return DataResponse.of(personRecordService.detail(id));
    }

    /**
     * 提取乘务系统当日人员情况
     * @param date 日期
     * @return 当日人员情况
     */
    @GetMapping("/syncData")
    @ApiOperation(value = "提取乘务系统当日人员情况")
    public DataResponse<PersonRecordResDTO> syncData(@RequestParam String date) {
        return DataResponse.of(personRecordService.syncData(date));
    }

    /**
     * 新增当日人员情况
     * @param personRecordReqDTO 当日人员情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-当日人员情况")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody PersonRecordReqDTO personRecordReqDTO) {
        personRecordService.add(currentLoginUser,personRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑当日人员情况
     * @param personRecordReqDTO 当日人员情况参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-当日人员情况")
    public DataResponse<T> modify(@RequestBody PersonRecordReqDTO personRecordReqDTO) {
        personRecordService.modify(personRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除当日人员情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-当日人员情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        personRecordService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
