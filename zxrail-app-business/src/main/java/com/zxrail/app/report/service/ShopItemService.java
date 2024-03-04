package com.zxrail.app.report.service;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

import com.zxrail.app.report.model.query.ShopItemQueryDTO;
import com.zxrail.app.report.model.cmd.ShopItemCmdDTO;
import com.zxrail.app.report.model.ShopItemDTO;
import com.zxrail.app.report.model.po.ShopItemPo;

import java.util.List;

/**
 * 服务层。
 *
 * @author xzrail-app
 * @since 1.0.0
 */
public interface ShopItemService extends IService<ShopItemPo> {
    /**
     * 新增
     *
     * @param cmdDTO cmd实例
     * @return id
     */
    Long save(ShopItemCmdDTO cmdDTO);

    /**
     * 删除
     *
     * @param id id
     * @return 是否删除成功
     */
    boolean removeById(Long id);

    /**
     * 修改
     *
     * @param cmdDTO 修改实例
     * @return 是否修改成功
     */
    boolean updateById(ShopItemCmdDTO cmdDTO);

    /**
     * 查询列表
     *
     * @param queryDTO 查询条件
     * @return 查询列表
     */
    List<ShopItemDTO> listDTO(ShopItemQueryDTO queryDTO);

    /**
     * 查询详情
     *
     * @param id id
     * @return 查询详情
     */
    ShopItemDTO getDTOById(Long id);

    /**
     * 分页查询
     *
     * @param queryDTO 查询条件
     * @return 分页查询
     */
    Page<ShopItemDTO> page(ShopItemQueryDTO queryDTO);
}
