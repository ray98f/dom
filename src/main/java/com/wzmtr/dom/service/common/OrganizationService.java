package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.common.MemberResDTO;
import com.wzmtr.dom.entity.CompanyStructureTree;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 公共分类-组织机构管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface OrganizationService {

    /**
     * 获取公司层级结构
     * @return 公司层级结构
     */
    List<CompanyStructureTree> listCompanyStructure();

    /**
     * 获取分公司列表
     * @return 分公司列表
     */
    List<CompanyStructureTree> listCompanyList();

    /**
     * 分页获取组织成员信息
     * @param id 组织id
     * @param name 姓名
     * @param pageReqDTO 分页参数
     * @return 组织成员信息
     */
    Page<MemberResDTO> pageMember(String id, String name, PageReqDTO pageReqDTO);

    /**
     * 获取所有组织成员信息
     * @param id 组织id
     * @param name 姓名
     * @return 组织成员信息
     */
    List<MemberResDTO> listMember(String id, String name);

    /**
     * 获取中铁通组织层级结构
     * @return 中铁通组织层级结构
     */
    List<CompanyStructureTree> listZttCompanyStructure();
}
