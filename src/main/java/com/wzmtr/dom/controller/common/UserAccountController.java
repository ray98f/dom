package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.res.common.UserAccountResDTO;
import com.wzmtr.dom.dto.res.common.UserCenterInfoResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.common.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 公共分类-用户管理
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "公共分类-用户管理")
@Validated
@CrossOrigin
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    /**
     * 获取用户信息列表
     * @param searchKey 关键字
     * @param pageReqDTO 分页参数
     * @return 用户信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取用户信息列表")
    public PageResponse<UserAccountResDTO> listUserAccount(@RequestParam(required = false) @ApiParam("检索关键词") String searchKey,
                                                           @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(userAccountService.listUserAccount(searchKey, pageReqDTO));
    }

    /**
     * 根据ids获取用户信息列表
     * @param baseIdsEntity ids
     * @return 用户信息列表
     */
    @PostMapping("/ids")
    @ApiOperation(value = "根据ids获取用户信息列表")
    public DataResponse<List<UserAccountResDTO>> selectUserAccountById(@RequestBody BaseIdsEntity baseIdsEntity) {
        return DataResponse.of(userAccountService.selectUserAccountById(baseIdsEntity.getIds()));
    }

    /**
     * 获取登录用户token
     * @param no 用户登录账号
     * @return token
     */
    @GetMapping("/getToken")
    @ApiOperation(value = "获取登录用户token")
    public DataResponse<String> getToken(@RequestParam String no) {
        return DataResponse.of(userAccountService.getToken(no));
    }

    /**
     * 获取登录用户详情
     * @return 用户详情
     */
    @GetMapping("/userInfo")
    @ApiOperation(value = "获取登录用户详情")
    public DataResponse<UserCenterInfoResDTO> getUserDetail() {
        return DataResponse.of(userAccountService.getUserDetail());
    }

}
