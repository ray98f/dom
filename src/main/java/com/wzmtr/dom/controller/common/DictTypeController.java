package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.req.common.DictTypeReqDTO;
import com.wzmtr.dom.dto.res.common.DictTypeResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.common.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 公共分类-字典类型管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@RestController
@RequestMapping("/dict/type")
@Api(tags = "公共分类-字典类型管理")
@Validated
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 分页查询字典类型列表
     * @param name 名称
     * @param pageReqDTO 分页参数
     * @return 字典类型列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "字典类型列表(分页)")
    public PageResponse<DictTypeResDTO> page(@RequestParam(required = false) @ApiParam("名称") String name,
                                             @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dictTypeService.page(name, pageReqDTO));
    }

    /**
     * 获取字典类型详情
     * @param id id
     * @return 字典类型详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "字典类型详情")
    public DataResponse<DictTypeResDTO> detail(@RequestParam("id") Integer id) {
        return DataResponse.of(dictTypeService.detail(id));
    }

    /**
     * 新增字典类型
     * @param dictTypeReqDTO 字典类型参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增-字典类型")
    public DataResponse<T> add(@RequestBody DictTypeReqDTO dictTypeReqDTO) {
        dictTypeService.add(dictTypeReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑字典类型
     * @param dictTypeReqDTO 字典类型参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑-字典类型")
    public DataResponse<T> modify(@RequestBody DictTypeReqDTO dictTypeReqDTO) {
        dictTypeService.modify(dictTypeReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除字典类型
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除-字典类型(单删+批量删除)")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        dictTypeService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
