package com.zxrail.app.report.controller;

import com.mybatisflex.core.paginate.Page;

import com.zxrail.app.report.dto.req.ShopSaveReqDTO;
import com.zxrail.app.report.dto.req.ShopUpdateReqDTO;
import com.zxrail.app.report.dto.req.ShopQueryReqDTO;
import com.zxrail.app.report.dto.res.ShopGetRespDTO;
import com.zxrail.app.report.dto.res.ShopPageRespDTO;
import com.zxrail.app.report.dto.res.ShopListRespDTO;
import com.zxrail.app.report.model.ShopDTO;
import com.zxrail.app.report.model.cmd.ShopCmdDTO;
import com.zxrail.app.report.model.query.ShopQueryDTO;
import com.zxrail.app.report.convert.ShopCtlConvert;

import com.zxrail.app.report.service.ShopService;
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
@RequestMapping("/admin/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    /**
     * 添加
     *
     * @param saveReqDTO 添加dto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Log(operationType = OperationType.ADD, value = "创建")
    public ApiResponse<Long> save(@Valid @RequestBody ShopSaveReqDTO saveReqDTO) {
        ShopCmdDTO cmdDTO = ShopCtlConvert.INSTANCE.saveReq2DTO(saveReqDTO);
        Long result = shopService.save(cmdDTO);
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
        return ApiResponse.ok(shopService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param updateReqDTO 更新dto
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Log(operationType = OperationType.UPDATE, value = "更新")
    public ApiResponse<Boolean> update(@Valid @RequestBody ShopUpdateReqDTO updateReqDTO) {
        ShopCmdDTO cmdDTO = ShopCtlConvert.INSTANCE.updateReq2DTO(updateReqDTO);
        boolean result = shopService.updateById(cmdDTO);
        return ApiResponse.ok(result);
    }

    /**
     * 查询所有
     *
     * @param queryReqDTO 查询对象
     * @return 所有数据
     */
    @GetMapping("/list")
    public ApiResponse<List<ShopListRespDTO>> list(ShopQueryReqDTO queryReqDTO) {
        ShopQueryDTO queryDTO = ShopCtlConvert.INSTANCE.queryReq2DTO(queryReqDTO);
        List<ShopDTO> result = shopService.listDTO(queryDTO);
        return ApiResponse.ok(ShopCtlConvert.INSTANCE.listDTO2Resp(result));
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    public ApiResponse<ShopGetRespDTO> getInfo(@Valid @NotNull @PathVariable Long id) {
        ShopDTO result = shopService.getDTOById(id);
        return ApiResponse.ok(ShopCtlConvert.INSTANCE.getDTO2Resp(result));
    }

    /**
     * 分页查询
     *
     * @param queryReqDTO 查询对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public ApiResponse<Page<ShopPageRespDTO>> page(ShopQueryReqDTO queryReqDTO) {
        ShopQueryDTO queryDTO = ShopCtlConvert.INSTANCE.queryReq2DTO(queryReqDTO);
        Page<ShopDTO> result = shopService.page(queryDTO);
        return ApiResponse.ok(ShopCtlConvert.INSTANCE.pageDTO2Resp(result));
    }
}