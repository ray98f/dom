package com.wzmtr.dom.mapper.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.common.OrgParentResDTO;
import com.wzmtr.dom.dto.res.common.MemberResDTO;
import com.wzmtr.dom.entity.CompanyStructureTree;
import com.wzmtr.dom.entity.SysOffice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共分类-组织机构管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Mapper
@Repository
public interface OrganizationMapper {

    /**
     * 获取组织结构根目录
     * @return 组织结构根目录
     */
    CompanyStructureTree getRoot();

    /**
     * 获取根目录下一级组织结构
     * @param root 根目录
     * @return 一级组织结构
     */
    List<CompanyStructureTree> listExtraRootList(@Param("root") String root);

    /**
     * 获取根目录下非一级组织结构
     * @param root 根目录
     * @return 非一级组织结构
     */
    List<CompanyStructureTree> listExtraBodyList(@Param("root") String root);

    /**
     * 获取公司列表
     * @return 公司列表
     */
    List<CompanyStructureTree> listCompanyList();

    /**
     * 分页查询组织机构人员列表
     * @param page 分页参数
     * @param id 组织机构id
     * @param name 姓名
     * @return 人员列表
     */
    Page<MemberResDTO> pageMember(Page<MemberResDTO> page, @Param("id") String id, @Param("name") String name);

    /**
     * 根据areaId获取组织机构id
     * @param id areaId
     * @return 组织机构id
     */
    String getIdByAreaId(@Param("id") String id);

    /**
     * 查询组织机构人员列表
     * @param id 组织机构id
     * @param name 姓名
     * @return 人员列表
     */
    List<MemberResDTO> listMember(@Param("id") String id, @Param("name") String name);

    /**
     * 分页查询组织机构下人员
     * @param page 分页参数
     * @param id 组织机构id
     * @return 人员列表
     */
    Page<MemberResDTO> pageUserByOffice(Page<MemberResDTO> page, @Param("id") String id);

    /**
     * 查询组织机构下人员
     * @param id 组织机构id
     * @return 人员列表
     */
    List<MemberResDTO> listUserByOffice(@Param("id") String id);

    /**
     * 根据id查询组织机构名称
     * @param id id
     * @return 组织机构名称
     */
    String selectOfficeNameById(String id);

    /**
     * 清除组织机构
     */
    void cleanOrg();

    /**
     * 新增组织机构
     * @param office 组织机构信息
     */
    void createOrg(SysOffice office);

    /**
     * 清除供应商
     */
    void cleanSupplier();

    /**
     * 清除外部组织
     */
    void cleanExtra();

    /**
     * 查询父级组织机构
     * @return 父级组织机构
     */
    List<OrgParentResDTO> searchParent();

    /**
     * 更新父级路径
     * @param orgParentResDTO 父级路径更新参数
     */
    void updateParent(OrgParentResDTO orgParentResDTO);

    /**
     * 根据组织机构id获取父级路径
     * @param officeId 组织机构id
     * @return 父级路径
     */
    String selectParentIdsByOfficeId(@Param("officeId") String officeId);

    /**
     * 获取中铁通根目录
     * @return 中铁通根目录
     */
    CompanyStructureTree getZttRoot();

    /**
     * 获取组织机构所有下级的id
     * @param id rootId
     * @return 组织机构所有下级的id
     */
    List<String> downRecursionId(@Param("id") String id);

    /**
     * 获取中铁通下级组织机构列表
     * @param ids ids
     * @return 中铁通下级组织机构列表
     */
    List<CompanyStructureTree> listZttExtraBodyList(List<String> ids);

}
