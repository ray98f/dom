package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.vehicle.TrainRecordReqDTO;
import com.wzmtr.dom.dto.res.vehicle.TrainRecordResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.TrainRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆部-班组培训情况
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@RestController
@RequestMapping("/vehicle/train/record")
@Api(tags = "车辆部-班组培训情况")
@Validated
public class TrainRecordController {

    @Autowired
    private TrainRecordService trainRecordService;

    /**
     * 分页查询班组培训情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageReqDTO 分页参数
     * @return 班组培训情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "班组培训情况列表(分页)")
    public PageResponse<TrainRecordResDTO> page(@RequestParam(required = false) String startDate,
                                                @RequestParam(required = false) String endDate,
                                                @RequestParam String dataType,
                                                @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(trainRecordService.page(dataType,startDate, endDate, pageReqDTO));
    }

    /**
     * 获取班组培训情况详情
     * @param id id
     * @return 班组培训情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "班组培训情况详情")
    public DataResponse<TrainRecordResDTO> detail(@RequestParam String id) {
        return DataResponse.of(trainRecordService.detail(id));
    }

    /**
     * 新增班组培训情况
     * @param trainRecordReqDTO 班组培训情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-班组培训情况")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody TrainRecordReqDTO trainRecordReqDTO) {
        trainRecordService.add(currentLoginUser,trainRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑班组培训情况
     * @param trainRecordReqDTO 班组培训情况参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-班组培训情况")
    public DataResponse<T> modify(@RequestBody TrainRecordReqDTO trainRecordReqDTO) {
        trainRecordService.modify(trainRecordReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除班组培训情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-班组培训情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        trainRecordService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
