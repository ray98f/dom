package com.wzmtr.dom.controller.system;

import com.wzmtr.dom.dto.req.system.StationRoleReqDTO;
import com.wzmtr.dom.dto.res.system.StationRoleResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.system.StationRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统参数-审核站权限管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/07
 */
@RestController
@RequestMapping("/sys/station/role")
@Api(tags = "系统参数-审核站权限管理")
@Validated
public class StationRoleController {

    @Autowired
    private StationRoleService stationRoleService;

    /**
     * 分页查询审核站权限列表
     * @param pageReqDTO 分页参数
     * @return 审核站权限列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "审核站权限列表(分页)")
    public PageResponse<StationRoleResDTO> page(@Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(stationRoleService.page(pageReqDTO));
    }

    /**
     * 获取审核站权限详情
     * @param id id
     * @return 审核站权限详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "审核站权限详情")
    public DataResponse<StationRoleResDTO> detail(@RequestParam String id) {
        return DataResponse.of(stationRoleService.detail(id));
    }

    /**
     * 新增审核站权限
     * @param stationRoleReqDTO 审核站权限参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-审核站权限")
    public DataResponse<T> add(@RequestBody StationRoleReqDTO stationRoleReqDTO) {
        stationRoleService.add(stationRoleReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑审核站权限
     * @param stationRoleReqDTO 审核站权限参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-审核站权限")
    public DataResponse<T> modify(@RequestBody StationRoleReqDTO stationRoleReqDTO) {
        stationRoleService.modify(stationRoleReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除审核站权限
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-审核站权限(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        stationRoleService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
