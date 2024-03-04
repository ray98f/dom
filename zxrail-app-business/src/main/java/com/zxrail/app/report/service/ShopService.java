package com.zxrail.app.report.service;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

import com.zxrail.app.report.model.ShopDTO;
import com.zxrail.app.report.model.cmd.ShopCmdDTO;
import com.zxrail.app.report.model.query.ShopQueryDTO;
import com.zxrail.app.report.model.po.ShopPo;

import java.util.List;

/**
 * 服务层。
 *
 * @author xzrail-app
 * @since 1.0.0
 */
public interface ShopService extends IService<ShopPo> {
    /**
     * 新增
     *
     * @param cmdDTO cmd实例
     * @return id
     */
    Long save(ShopCmdDTO cmdDTO);

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
    boolean updateById(ShopCmdDTO cmdDTO);

    /**
     * 查询列表
     *
     * @param queryDTO 查询条件
     * @return 查询列表
     */
    List<ShopDTO> listDTO(ShopQueryDTO queryDTO);

    /**
     * 查询详情
     *
     * @param id id
     * @return 查询详情
     */
    ShopDTO getDTOById(Long id);

    /**
     * 分页查询
     *
     * @param queryDTO 查询条件
     * @return 分页查询
     */
    Page<ShopDTO> page(ShopQueryDTO queryDTO);
}
