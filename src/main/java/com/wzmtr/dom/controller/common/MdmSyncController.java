package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.service.common.MdmSyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共分类-主数据同步
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
@RestController
@RequestMapping("/mdm/sync")
@Api(tags = "公共分类-主数据同步")
@Validated
public class MdmSyncController {
    @Autowired
    private MdmSyncService mdmSyncService;

    /**
     * 全量获取主数据人员数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取主数据人员数据")
    @GetMapping("/person")
    public DataResponse<T> syncAllPerson() {
        mdmSyncService.syncAllPerson();
        return DataResponse.success();
    }

    /**
     * 全量获取外部单位人员数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取外部单位人员数据")
    @GetMapping("/contact/supp")
    public DataResponse<T> syncAllSuppContact() {
        mdmSyncService.syncAllSuppContact();
        return DataResponse.success();
    }

    /**
     * 全量获取主数据人员扩展数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取主数据人员扩展数据")
    @GetMapping("/person/plus")
    public DataResponse<T> syncPersonPlus() {
        mdmSyncService.syncPersonPlus();
        return DataResponse.success();
    }

    /**
     * 全量获取主数据组织机构数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取主数据组织机构数据")
    @GetMapping("/org")
    public DataResponse<T> syncAllOrg() {
        mdmSyncService.syncAllOrg();
        return DataResponse.success();
    }

    /**
     * 全量获取主数据供应商数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取主数据供应商数据")
    @GetMapping("/org/supp")
    public DataResponse<T> syncSuppOrg() {
        mdmSyncService.syncSuppOrg();
        return DataResponse.success();
    }

    /**
     * 全量获取主数据外部组织机构数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取主数据外部组织机构数据")
    @GetMapping("/org/extra")
    public DataResponse<T> syncAllExtraOrg() {
        mdmSyncService.syncAllExtraOrg();
        return DataResponse.success();
    }

    /**
     * 全量获取主数据员工任职信息数据
     * @return 成功
     */
    @ApiOperation(value = "全量获取主数据员工任职信息数据")
    @GetMapping("/job/emp")
    public DataResponse<T> syncAllEmpJob() {
        mdmSyncService.syncAllEmpJob();
        return DataResponse.success();
    }

    @ApiOperation(value = "全量获取车站人员数据")
    @GetMapping("/syncStationUser")
    public DataResponse<T> syncStationUser() {
        mdmSyncService.syncStationUser();
        return DataResponse.success();
    }
}
