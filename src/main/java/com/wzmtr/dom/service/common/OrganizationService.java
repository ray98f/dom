package com.wzmtr.dom.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.common.MemberResDTO;
import com.wzmtr.dom.entity.CompanyStructureTree;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

public interface OrganizationService {

    List<CompanyStructureTree> listCompanyStructure();

    List<CompanyStructureTree> listCompanyList();

    Page<MemberResDTO> pageMember(String id, String name, PageReqDTO pageReqDTO);

    List<MemberResDTO> listMember(String id);

    /**
     * 获取中铁通组织层级结构
     * @return 中铁通组织层级结构
     */
    List<CompanyStructureTree> listZttCompanyStructure();
}
