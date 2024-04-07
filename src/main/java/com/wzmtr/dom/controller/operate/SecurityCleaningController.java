package com.wzmtr.dom.controller.operate;

import com.wzmtr.dom.dto.req.operate.SecurityCleaningReqDTO;
import com.wzmtr.dom.dto.res.operate.SecurityCleaningResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.operate.SecurityCleaningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运营-安检及保洁情况
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@RestController
@RequestMapping("/operate/security/cleaning")
@Api(tags = "运营-安检及保洁情况")
@Validated
public class SecurityCleaningController {

    @Autowired
    private SecurityCleaningService securityCleaningService;

    /**
     * 分页查询安检及保洁情况列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType 数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 安检及保洁情况列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "安检及保洁情况列表(分页)")
    public PageResponse<SecurityCleaningResDTO> page(@RequestParam(required = false) String startDate,
                                                     @RequestParam(required = false) String endDate,
                                                     @RequestParam String dataType,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(securityCleaningService.page(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 获取安检及保洁情况详情
     * @param id id
     * @return 安检及保洁情况详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "安检及保洁情况详情")
    public DataResponse<SecurityCleaningResDTO> detail(@RequestParam String id) {
        return DataResponse.of(securityCleaningService.detail(id));
    }

    /**
     * 新增安检及保洁情况
     * @param securityCleaningReqDTO 安检及保洁情况参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-安检及保洁情况")
    public DataResponse<T> add(@RequestBody SecurityCleaningReqDTO securityCleaningReqDTO) {
        securityCleaningService.add(securityCleaningReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑安检及保洁情况
     * @param securityCleaningReqDTO 安检及保洁情况参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-安检及保洁情况")
    public DataResponse<T> modify(@RequestBody SecurityCleaningReqDTO securityCleaningReqDTO) {
        securityCleaningService.modify(securityCleaningReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除安检及保洁情况
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-安检及保洁情况(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        securityCleaningService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
