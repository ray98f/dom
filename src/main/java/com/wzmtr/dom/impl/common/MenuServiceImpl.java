package com.wzmtr.dom.impl.common;

import com.wzmtr.dom.dto.req.common.MenuAddReqDTO;
import com.wzmtr.dom.dto.req.common.MenuModifyReqDTO;
import com.wzmtr.dom.dto.res.common.MenuDetailResDTO;
import com.wzmtr.dom.dto.res.common.MenuResDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.common.MenuMapper;
import com.wzmtr.dom.service.common.MenuService;
import com.wzmtr.dom.utils.TokenUtils;
import com.wzmtr.dom.utils.tree.MenuTreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 公共分类-菜单管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuResDTO> listLoginMenu() {
        List<MenuResDTO> extraRootList = menuMapper.listLoginMenuRootList(TokenUtils.getCurrentPersonId());
        List<MenuResDTO> extraBodyList = menuMapper.listLoginMenuBodyList(TokenUtils.getCurrentPersonId());
        MenuTreeUtils extraTree = new MenuTreeUtils(extraRootList, extraBodyList);
        return extraTree.getTree();
    }

    @Override
    public List<MenuResDTO> listUseMenu() {
        List<MenuResDTO> extraRootList = menuMapper.listUseMenuRootList();
        List<MenuResDTO> extraBodyList = menuMapper.listUseMenuBodyList();
        MenuTreeUtils extraTree = new MenuTreeUtils(extraRootList, extraBodyList);
        return extraTree.getTree();
    }

    @Override
    public List<MenuResDTO> listMenu() {
        List<MenuResDTO> extraRootList = menuMapper.listMenuRootList();
        List<MenuResDTO> extraBodyList = menuMapper.listMenuBodyList();
        MenuTreeUtils extraTree = new MenuTreeUtils(extraRootList, extraBodyList);
        return extraTree.getTree();
    }

    @Override
    public MenuDetailResDTO getMenuDetail(String id) {
        if (Objects.isNull(id)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        return menuMapper.getMenuDetail(id);
    }

    @Override
    public void addMenu(MenuAddReqDTO menuAddReqDTO) {
        if (Objects.isNull(menuAddReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        menuAddReqDTO.setId(TokenUtils.getUuId());
        menuAddReqDTO.setUserId(TokenUtils.getCurrentPersonId());
        menuMapper.insertMenu(menuAddReqDTO);
    }

    @Override
    public void modifyMenu(MenuModifyReqDTO menuModifyReqDTO) {
        if (Objects.isNull(menuModifyReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        menuModifyReqDTO.setUserId(TokenUtils.getCurrentPersonId());
        menuMapper.modifyMenu(menuModifyReqDTO);
    }

    @Override
    public void deleteMenu(String id) {
        if (Objects.isNull(id)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Integer result = menuMapper.selectMenuHadChildren(id);
        if (result > 0) {
            throw new CommonException(ErrorCode.RESOURCE_USE);
        }
        menuMapper.deleteMenu(TokenUtils.getCurrentPersonId(), id);
    }
}
