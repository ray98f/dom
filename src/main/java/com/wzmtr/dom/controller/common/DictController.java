package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.req.common.DictReqDTO;
import com.wzmtr.dom.dto.res.common.DictResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.common.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 公共分类-字典值管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "公共分类-字典值管理")
@Validated
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 分页查询字典值列表
     * @param name 名称
     * @param code 编号
     * @param pageReqDTO 分页参数
     * @return 字典值列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "字典值列表(分页)")
    public PageResponse<DictResDTO> page(@RequestParam(required = false) @ApiParam("名称") String name,
                                         @RequestParam(required = false) @ApiParam("编号") String code,
                                         @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(dictService.page(name, code, pageReqDTO));
    }

    /**
     * 根据字典类型获取字典值
     * @param typeCode 类型编码
     * @param code 编码
     * @param status 状态
     * @return 字典值
     */
    @GetMapping("/list")
    @ApiOperation(value = "根据字典类型获取字典值")
    public DataResponse<List<DictResDTO>> list(@RequestParam @ApiParam("类型编码") String typeCode,
                                               @RequestParam(required = false) @ApiParam("编码") String code,
                                               @RequestParam(required = false) @ApiParam("状态") String status) {
        return DataResponse.of(dictService.list(typeCode, code, status));
    }

    /**
     * 获取字典详情
     * @param id id
     * @return 字典详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "字典详情")
    public DataResponse<DictResDTO> detail(@RequestParam String id) {
        return DataResponse.of(dictService.detail(id));
    }

    /**
     * 新增字典值
     * @param dictReqDTO 字典值参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增字典值")
    public DataResponse<T> add(@RequestBody DictReqDTO dictReqDTO) {
        dictService.add(dictReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑字典值
     * @param dictReqDTO 字典值参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑字典值")
    public DataResponse<T> modify(@RequestBody DictReqDTO dictReqDTO) {
        dictService.modify(dictReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除字典值
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除字典值")
    public DataResponse<T> delete(@RequestBody BaseIdsEntity baseIdsEntity) {
        dictService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
