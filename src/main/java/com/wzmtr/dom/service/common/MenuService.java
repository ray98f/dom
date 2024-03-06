package com.wzmtr.dom.service.common;

import com.wzmtr.dom.dto.req.common.MenuAddReqDTO;
import com.wzmtr.dom.dto.req.common.MenuModifyReqDTO;
import com.wzmtr.dom.dto.res.common.MenuDetailResDTO;
import com.wzmtr.dom.dto.res.common.MenuResDTO;

import java.util.List;

/**
 * 公共分类-菜单管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface MenuService {

    /**
     * 获取登录用户的菜单列表
     * @return 登录用户的菜单列表
     */
    List<MenuResDTO> listLoginMenu();

    /**
     * 获取使用中的菜单列表
     * @return 使用中的菜单列表
     */
    List<MenuResDTO> listUseMenu();

    /**
     * 获取菜单列表（包含停用）
     * @return 菜单列表（包含停用）
     */
    List<MenuResDTO> listMenu();

    /**
     * 获取菜单详情
     * @param id 菜单id
     * @return 菜单详情
     */
    MenuDetailResDTO getMenuDetail(String id);

    /**
     * 新增菜单
     * @param menuAddReqDTO 新增参数
     */
    void addMenu(MenuAddReqDTO menuAddReqDTO);

    /**
     * 编辑菜单
     * @param menuModifyReqDTO 编辑参数
     */
    void modifyMenu(MenuModifyReqDTO menuModifyReqDTO);

    /**
     * 删除菜单
     * @param id id
     */
    void deleteMenu(String id);
}
