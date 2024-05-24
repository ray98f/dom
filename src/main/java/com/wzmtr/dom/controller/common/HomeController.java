package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.res.common.LastReportResDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.service.common.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *   首页
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 9:00
 */
@Slf4j
@RestController
@RequestMapping("/home")
@Api(tags = "公共分类-首页")
@Validated
public class HomeController {

    @Resource
    private HomeService homeService;
    /**
     * 获取当前最新的已审批报表
     * @return 获取当前最新的已审批报表
     */
    @GetMapping("/getLastReport")
    @ApiOperation(value = "获取当前最新的已审批报表")
    public DataResponse<LastReportResDTO> getLastReport() {
        return DataResponse.of(homeService.getLastReport());
    }

}
