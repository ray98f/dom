package com.wzmtr.dom.impl.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.res.common.MemberResDTO;
import com.wzmtr.dom.entity.CompanyStructureTree;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.common.OrganizationMapper;
import com.wzmtr.dom.service.common.OrganizationService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.tree.CompanyTreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 公共分类-组织机构管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<CompanyStructureTree> listCompanyStructure() {
        CompanyStructureTree companyStructureTree = organizationMapper.getRoot();
        if (Objects.isNull(companyStructureTree)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        List<CompanyStructureTree> extraRootList = organizationMapper.listExtraRootList(companyStructureTree.getId());
        List<CompanyStructureTree> extraBodyList = organizationMapper.listExtraBodyList(companyStructureTree.getId());
        CompanyTreeUtils extraTree = new CompanyTreeUtils(extraRootList, extraBodyList);
        companyStructureTree.setChildren(extraTree.getTree());
        List<CompanyStructureTree> list = new ArrayList<>();
        list.add(companyStructureTree);
        return list;
    }

    @Override
    public List<CompanyStructureTree> listCompanyList() {
        return organizationMapper.listCompanyList();
    }

    @Override
    public Page<MemberResDTO> pageMember(String id, String name, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        Page<MemberResDTO> page;
        String newId = organizationMapper.getIdByAreaId(id);
        if (!Objects.isNull(newId)) {
            return organizationMapper.pageUserByOffice(pageReqDTO.of(), newId);
        }
        if (CommonConstants.ROOT.equals(id)) {
            page = organizationMapper.pageMember(pageReqDTO.of(), id, name);
        } else {
            page = organizationMapper.pageMember(pageReqDTO.of(), "," + id, name);
        }
        List<MemberResDTO> list = page.getRecords();
        if (StringUtils.isNotEmpty(list)) {
            for (MemberResDTO memberResDTO : list) {
                if (CommonConstants.ROOT.equals(memberResDTO.getParentId())) {
                    continue;
                }
                memberResDTO.setOrgPath(organizationMapper.selectOfficeNameById(memberResDTO.getParentId()));
            }
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public List<MemberResDTO> listMember(String id, String name) {
        List<MemberResDTO> list;
        String newId = organizationMapper.getIdByAreaId(id);
        if (!Objects.isNull(newId) && !Objects.isNull(name)) {
            return organizationMapper.listUserByOfficeAndName(newId,name);
        }
        if (!Objects.isNull(newId) && Objects.isNull(name)) {
            return organizationMapper.listUserByOffice(newId);
        }
        if (CommonConstants.ROOT.equals(id)) {
            list = organizationMapper.listMember(id, name);
        } else {
            list = organizationMapper.listMember("," + id, name);
        }
        if (StringUtils.isNotEmpty(list)) {
            for (MemberResDTO memberResDTO : list) {
                if (CommonConstants.ROOT.equals(memberResDTO.getParentId())) {
                    continue;
                }
                memberResDTO.setOrgPath(organizationMapper.selectOfficeNameById(memberResDTO.getParentId()));
            }
        }
        return list;
    }

    @Override
    public List<CompanyStructureTree> listZttCompanyStructure() {
        CompanyStructureTree companyStructureTree = organizationMapper.getZttRoot();
        if (Objects.isNull(companyStructureTree)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        List<CompanyStructureTree> extraRootList = new ArrayList<>();
        extraRootList.add(companyStructureTree);
        List<String> ids = organizationMapper.downRecursionId(companyStructureTree.getId());
        ids.remove(companyStructureTree.getId());
        List<CompanyStructureTree> extraBodyList = organizationMapper.listZttExtraBodyList(ids);
        CompanyTreeUtils extraTree = new CompanyTreeUtils(extraRootList, extraBodyList);
        return extraTree.getTree();
    }

}
