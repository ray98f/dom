package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.operate.SpeedLimitInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.SpeedLimitRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitInfoResDTO;
import com.wzmtr.dom.dto.res.operate.SpeedLimitRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.SpeedLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营-线路限速情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/01
 */
@RestController
@RequestMapping("/operate/speed/limit")
@Api(tags = "运营-线路限速情况")
@Validated
public class SpeedLimitController {

    @Autowired
    private SpeedLimitService speedLimitService;

    /**
     * 查询线路限速情况记录列表(分页)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 线路限速情况列表
     */
    @GetMapping("/record/page")
    @ApiOperation(value = "查询线路限速情况记录列表(分页)")
    public PageResponse<SpeedLimitRecordResDTO> recordPage(@RequestParam(required = false) String startDate,
                                                           @RequestParam(required = false) String endDate,
                                                           @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(speedLimitService.recordPage(startDate, endDate, pageReqDTO));
    }

    /**
     * 查询线路限速情况详情列表(分页)
     * @param id id
     * @return 线路限速情况详情列表
     */
    @GetMapping("/info/page")
    @ApiOperation(value = "查询线路限速情况详情列表(分页)")
    public PageResponse<SpeedLimitInfoResDTO> infoPage(@RequestParam(required = false) String id,
                                                       @RequestParam(required = false) String startDate,
                                                       @RequestParam(required = false) String endDate,
                                                       @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(speedLimitService.infoPage(id,startDate,endDate, pageReqDTO));
    }

    /**
     * 新增线路限速情况记录
     * @param speedLimitRecordReqDTO 线路限速情况记录参数
     * @return 成功
     */
    @PostMapping("/record/add")
    @ApiOperation(value = "新增-线路限速情况记录")
    public DataResponse<T> addRecord(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody SpeedLimitRecordReqDTO speedLimitRecordReqDTO) {
        speedLimitService.addRecord(currentLoginUser,speedLimitRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 新增线路限速情况详情
     * @param speedLimitInfoReqDTO 线路限速情况详情参数
     * @return 成功
     */
    @PostMapping("/info/add")
    @ApiOperation(value = "新增-线路限速情况详情")
    public DataResponse<T> addInfo(@RequestBody SpeedLimitInfoReqDTO speedLimitInfoReqDTO) {
        speedLimitService.addInfo(speedLimitInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑线路限速情况记录
     * @param speedLimitRecordReqDTO 线路限速情况记录参数
     * @return 成功
     */
    @PostMapping("/record/modify")
    @ApiOperation(value = "编辑-线路限速情况记录")
    public DataResponse<T> modifyRecord(@RequestBody SpeedLimitRecordReqDTO speedLimitRecordReqDTO) {
        speedLimitService.modifyRecord(speedLimitRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑线路限速情况详情
     * @param speedLimitInfoReqDTO 线路限速情况详情参数
     * @return 成功
     */
    @PostMapping("/info/modify")
    @ApiOperation(value = "编辑-线路限速情况详情")
    public DataResponse<T> modifyInfo(@RequestBody SpeedLimitInfoReqDTO speedLimitInfoReqDTO) {
        speedLimitService.modifyInfo(speedLimitInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除线路限速情况记录
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/record/delete")
    @ApiOperation(value = "删除-线路限速情况记录(单删+批量删除)")
    public DataResponse<T> deleteRecord(@RequestBody BaseIdsEntity baseIdsEntity) {
        speedLimitService.deleteRecord(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 删除线路限速情况详情
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/info/delete")
    @ApiOperation(value = "删除-线路限速情况详情(单删+批量删除)")
    public DataResponse<T> deleteInfo(@RequestBody BaseIdsEntity baseIdsEntity) {
        speedLimitService.deleteInfo(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
