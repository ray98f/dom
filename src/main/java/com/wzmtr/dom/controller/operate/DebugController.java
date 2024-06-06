package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.operate.DebugInfoReqDTO;
import com.wzmtr.dom.dto.req.operate.DebugRecordReqDTO;
import com.wzmtr.dom.dto.res.operate.ConstructPlanResDTO;
import com.wzmtr.dom.dto.res.operate.DebugInfoResDTO;
import com.wzmtr.dom.dto.res.operate.DebugRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.DebugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营-调试情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@RestController
@RequestMapping("/operate/debug")
@Api(tags = "运营-调试情况")
@Validated
public class DebugController {

    @Autowired
    private DebugService debugService;

    /**
     * 查询调试情况记录列表(分页)
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 调试情况列表
     */
    @GetMapping("/record/page")
    @ApiOperation(value = "查询调试情况记录列表(分页)")
    public PageResponse<DebugRecordResDTO> recordPage(@RequestParam(required = false) String startDate,
                                                      @RequestParam(required = false) String endDate,
                                                      @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(debugService.recordPage(startDate, endDate, pageReqDTO));
    }

    /**
     * 查询调试情况详情列表(分页)
     * @param id id
     * @param dataType 数据类型 1 信号调试 2 车辆调试
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 调试情况详情列表
     */
    @GetMapping("/info/page")
    @ApiOperation(value = "查询调试情况详情列表(分页)")
    public PageResponse<DebugInfoResDTO> infoPage(@RequestParam(required = false) String id,
                                                  @RequestParam String dataType,
                                                  @RequestParam(required = false) String startDate,
                                                  @RequestParam(required = false) String endDate,
                                                  @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(debugService.infoPage(id, dataType, startDate, endDate, pageReqDTO));
    }

    /**
     * 新增调试情况记录
     * @param debugRecordReqDTO 调试情况记录参数
     * @return 成功
     */
    @PostMapping("/record/add")
    @ApiOperation(value = "新增-调试情况记录")
    public DataResponse<T> addRecord(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody DebugRecordReqDTO debugRecordReqDTO) {
        debugService.addRecord(currentLoginUser,debugRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 新增调试情况详情
     * @param debugInfoReqDTO 调试情况详情参数
     * @return 成功
     */
    @PostMapping("/info/add")
    @ApiOperation(value = "新增-调试情况详情")
    public DataResponse<T> addInfo(@RequestBody DebugInfoReqDTO debugInfoReqDTO) {
        debugService.addInfo(debugInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑调试情况记录
     * @param debugRecordReqDTO 调试情况记录参数
     * @return 成功
     */
    @PostMapping("/record/modify")
    @ApiOperation(value = "编辑-调试情况记录")
    public DataResponse<T> modifyRecord(@RequestBody DebugRecordReqDTO debugRecordReqDTO) {
        debugService.modifyRecord(debugRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑调试情况详情
     * @param debugInfoReqDTO 调试情况详情参数
     * @return 成功
     */
    @PostMapping("/info/modify")
    @ApiOperation(value = "编辑-调试情况详情")
    public DataResponse<T> modifyInfo(@RequestBody DebugInfoReqDTO debugInfoReqDTO) {
        debugService.modifyInfo(debugInfoReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除调试情况记录
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/record/delete")
    @ApiOperation(value = "删除-调试情况记录(单删+批量删除)")
    public DataResponse<T> deleteRecord(@RequestBody BaseIdsEntity baseIdsEntity) {
        debugService.deleteRecord(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 删除调试情况详情
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/info/delete")
    @ApiOperation(value = "删除-调试情况详情(单删+批量删除)")
    public DataResponse<T> deleteInfo(@RequestBody BaseIdsEntity baseIdsEntity) {
        debugService.deleteInfo(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 选择施工计划(查询施工调度系统)-列表
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @param pageReqDTO 分页参数
     * @return 施工计划列表
     */
    @GetMapping("/csm")
    @ApiOperation(value = "选择施工计划-列表")
    public PageResponse<ConstructPlanResDTO> getCsmConstructPlan(@RequestParam String startDate,
                                                                 @RequestParam String endDate,
                                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(debugService.getCsmConstructPlan(startDate,endDate,pageReqDTO));
    }
}
