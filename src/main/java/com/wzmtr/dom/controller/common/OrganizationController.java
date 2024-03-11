package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.res.common.MemberResDTO;
import com.wzmtr.dom.entity.CompanyStructureTree;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.common.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 公共分类-组织机构管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
@RestController
@RequestMapping("/org")
@Api(tags = "公共分类-组织机构管理")
@Validated
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;

    /**
     * 获取公司层级结构
     * @return 公司层级结构
     */
    @GetMapping("/listTree")
    @ApiOperation(value = "公司层级结构获取")
    public DataResponse<List<CompanyStructureTree>> listCompanyStructure() {
        return DataResponse.of(organizationService.listCompanyStructure());
    }

    /**
     * 获取分公司列表
     * @return 分公司列表
     */
    @GetMapping("/companyList")
    @ApiOperation(value = "获取分公司列表")
    public DataResponse<List<CompanyStructureTree>> listCompanyList() {
        return DataResponse.of(organizationService.listCompanyList());
    }

    /**
     * 分页获取组织成员信息
     * @param id 组织id
     * @param name 姓名
     * @param pageReqDTO 分页参数
     * @return 组织成员信息
     */
    @GetMapping("/pageMember")
    @ApiOperation(value = "分页获取组织成员信息")
    public PageResponse<MemberResDTO> pageMember(@RequestParam @ApiParam("组织id") String id,
                                                 @RequestParam(required = false) @ApiParam("名称模糊查询") String name,
                                                 @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(organizationService.pageMember(id, name, pageReqDTO));
    }

    /**
     * 获取所有组织成员信息
     * @param id 组织id
     * @param name 姓名
     * @return 组织成员信息
     */
    @GetMapping("/listMember")
    @ApiOperation(value = "获取所有组织成员信息")
    public DataResponse<List<MemberResDTO>> listMember(@RequestParam @ApiParam("组织id") String id,
                                                       @RequestParam(required = false) @ApiParam("姓名") String name) {
        return DataResponse.of(organizationService.listMember(id, name));
    }

    /**
     * 获取中铁通组织层级结构
     * @return 中铁通组织层级结构
     */
    @GetMapping("/ztt/listTree")
    @ApiOperation(value = "获取中铁通组织层级结构")
    public DataResponse<List<CompanyStructureTree>> listZttCompanyStructure() {
        return DataResponse.of(organizationService.listZttCompanyStructure());
    }

}
