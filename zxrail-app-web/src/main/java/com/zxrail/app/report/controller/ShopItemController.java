package com.zxrail.app.report.controller;

import com.mybatisflex.core.paginate.Page;

import com.zxrail.app.report.convert.ShopItemCtlConvert;
import com.zxrail.app.report.model.ShopItemDTO;
import com.zxrail.app.report.model.cmd.ShopItemCmdDTO;
import com.zxrail.app.report.model.query.ShopItemQueryDTO;
import com.zxrail.app.report.dto.req.ShopItemQueryReqDTO;
import com.zxrail.app.report.dto.req.ShopItemSaveReqDTO;
import com.zxrail.app.report.dto.req.ShopItemUpdateReqDTO;
import com.zxrail.app.report.dto.res.ShopItemGetRespDTO;
import com.zxrail.app.report.dto.res.ShopItemListRespDTO;
import com.zxrail.app.report.dto.res.ShopItemPageRespDTO;
import com.zxrail.app.report.service.ShopItemService;

import com.zxrail.framework.common.base.ApiResponse;
import com.zxrail.framework.log.annotation.Log;
import com.zxrail.framework.log.enums.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xzrail-app
 * @since 1.0.0
 */
@RestController
@RequestMapping("/admin/shopItem")
@RequiredArgsConstructor
public class ShopItemController {

    private final ShopItemService shopItemService;

    /**
     * 添加
     *
     * @param saveReqDTO 添加dto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Log(operationType = OperationType.ADD, value = "创建")
    public ApiResponse<Long> save(@Valid @RequestBody ShopItemSaveReqDTO saveReqDTO) {
        ShopItemCmdDTO cmdDTO = ShopItemCtlConvert.INSTANCE.saveReq2DTO(saveReqDTO);
        Long result = shopItemService.save(cmdDTO);
        return ApiResponse.ok(result);
    }


    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Log(operationType = OperationType.DELETE, value = "删除")
    public ApiResponse<Boolean> remove(@Valid @NotNull @PathVariable Long id) {
        return ApiResponse.ok(shopItemService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param updateReqDTO 更新dto
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Log(operationType = OperationType.UPDATE, value = "更新")
    public ApiResponse<Boolean> update(@Valid @RequestBody ShopItemUpdateReqDTO updateReqDTO) {
        ShopItemCmdDTO cmdDTO = ShopItemCtlConvert.INSTANCE.updateReq2DTO(updateReqDTO);
        boolean result = shopItemService.updateById(cmdDTO);
        return ApiResponse.ok(result);
    }

    /**
     * 查询所有
     *
     * @param queryReqDTO 查询对象
     * @return 所有数据
     */
    @GetMapping("/list")
    public ApiResponse<List<ShopItemListRespDTO>> list(ShopItemQueryReqDTO queryReqDTO) {
        ShopItemQueryDTO queryDTO = ShopItemCtlConvert.INSTANCE.queryReq2DTO(queryReqDTO);
        List<ShopItemDTO> result = shopItemService.listDTO(queryDTO);
        return ApiResponse.ok(ShopItemCtlConvert.INSTANCE.listDTO2Resp(result));
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    public ApiResponse<ShopItemGetRespDTO> getInfo(@Valid @NotNull @PathVariable Long id) {
        ShopItemDTO result = shopItemService.getDTOById(id);
        return ApiResponse.ok(ShopItemCtlConvert.INSTANCE.getDTO2Resp(result));
    }

    /**
     * 分页查询
     *
     * @param queryReqDTO 查询对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public ApiResponse<Page<ShopItemPageRespDTO>> page(ShopItemQueryReqDTO queryReqDTO) {
        ShopItemQueryDTO queryDTO = ShopItemCtlConvert.INSTANCE.queryReq2DTO(queryReqDTO);
        Page<ShopItemDTO> result = shopItemService.page(queryDTO);
        return ApiResponse.ok(ShopItemCtlConvert.INSTANCE.pageDTO2Resp(result));
    }
}