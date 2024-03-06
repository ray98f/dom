package com.wzmtr.dom.mapper.common;

import com.wzmtr.dom.dto.req.common.MenuAddReqDTO;
import com.wzmtr.dom.dto.req.common.MenuModifyReqDTO;
import com.wzmtr.dom.dto.res.common.MenuDetailResDTO;
import com.wzmtr.dom.dto.res.common.MenuResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共分类-菜单管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Mapper
@Repository
public interface MenuMapper {

    /**
     * 获取登录用户的菜单根目录
     * @param personId 用户id
     * @return 登录用户的菜单根目录
     */
    List<MenuResDTO> listLoginMenuRootList(@Param("personId") String personId);

    /**
     * 获取登录用户的菜单子目录
     * @param personId 用户id
     * @return 登录用户的菜单子目录
     */
    List<MenuResDTO> listLoginMenuBodyList(@Param("personId") String personId);

    /**
     * 获取使用中的菜单根目录
     * @return 使用中的菜单根目录
     */
    List<MenuResDTO> listUseMenuRootList();

    /**
     * 获取使用中的菜单子目录
     * @return 使用中的菜单子目录
     */
    List<MenuResDTO> listUseMenuBodyList();

    /**
     * 获取菜单根目录
     * @return 菜单根目录
     */
    List<MenuResDTO> listMenuRootList();

    /**
     * 获取菜单子目录
     * @return 菜单子目录
     */
    List<MenuResDTO> listMenuBodyList();

    /**
     * 获取菜单详情
     * @param id id
     * @return 菜单详情
     */
    MenuDetailResDTO getMenuDetail(@Param("id") String id);

    /**
     * 新增菜单
     * @param menuAddReqDTO 新增参数
     */
    void insertMenu(MenuAddReqDTO menuAddReqDTO);

    /**
     * 编辑菜单
     * @param menuModifyReqDTO 编辑参数
     */
    void modifyMenu(MenuModifyReqDTO menuModifyReqDTO);

    /**
     * 获取菜单下是否有子菜单
     * @param id id
     * @return 1:是
     */
    Integer selectMenuHadChildren(@Param("id") String id);

    /**
     * 删除菜单
     * @param userId 用户id
     * @param id 菜单id
     */
    void deleteMenu(@Param("userId") String userId, @Param("id") String id);

}
