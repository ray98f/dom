package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.req.common.MenuAddReqDTO;
import com.wzmtr.dom.dto.req.common.MenuModifyReqDTO;
import com.wzmtr.dom.dto.res.common.MenuDetailResDTO;
import com.wzmtr.dom.dto.res.common.MenuResDTO;
import com.wzmtr.dom.entity.BaseIdEntity;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.service.common.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 公共分类-菜单管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(tags = "公共分类-菜单管理")
@Validated
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取登录用户的菜单列表
     * @return 登录用户的菜单列表
     */
    @GetMapping("/login")
    @ApiOperation(value = "获取登录用户的菜单列表")
    public DataResponse<List<MenuResDTO>> listLoginMenu() {
        return DataResponse.of(menuService.listLoginMenu());
    }

    /**
     * 获取使用中的菜单列表
     * @return 使用中的菜单列表
     */
    @GetMapping("/use")
    @ApiOperation(value = "获取使用中的菜单列表")
    public DataResponse<List<MenuResDTO>> listUseMenu() {
        return DataResponse.of(menuService.listUseMenu());
    }

    /**
     * 获取菜单列表（包含停用）
     * @return 菜单列表（包含停用）
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取菜单列表")
    public DataResponse<List<MenuResDTO>> listMenu() {
        return DataResponse.of(menuService.listMenu());
    }

    /**
     * 获取菜单详情
     * @param id 菜单id
     * @return 菜单详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "获取菜单详情")
    public DataResponse<MenuDetailResDTO> getMenuDetail(@RequestParam @ApiParam(value = "菜单id") String id) {
        return DataResponse.of(menuService.getMenuDetail(id));
    }

    /**
     * 新增菜单
     * @param menuAddReqDTO 新增参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增菜单")
    public DataResponse<T> addMenu(@Valid @RequestBody MenuAddReqDTO menuAddReqDTO) {
        menuService.addMenu(menuAddReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑菜单
     * @param menuModifyReqDTO 编辑参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "编辑菜单")
    public DataResponse<T> modifyMenu(@Valid @RequestBody MenuModifyReqDTO menuModifyReqDTO) {
        menuService.modifyMenu(menuModifyReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除菜单
     * @param baseIdEntity id
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除菜单")
    public DataResponse<T> deleteMenu(@RequestBody BaseIdEntity baseIdEntity) {
        menuService.deleteMenu(baseIdEntity.getId());
        return DataResponse.success();
    }

}
