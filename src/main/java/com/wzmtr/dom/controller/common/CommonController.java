package com.wzmtr.dom.controller.common;

import com.wzmtr.dom.dto.req.common.OpenConstructPlanReqDTO;
import com.wzmtr.dom.dto.res.OpenDriverInfoRes;
import com.wzmtr.dom.dto.res.operate.PlanStatisticsResDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.service.common.ThirdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公共分类-单点登录
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@CrossOrigin
@Slf4j
@RestController
@Validated
@Api(tags = "公共分类-单点登录")
public class CommonController {

    @Value("${cas.serverUrlPrefix}")
    public String casServerUrlPrefix;

    @Value("${cas.serviceUrlPrefix}")
    public String casServiceUrlPrefix;

    @Value("${cas.casFilterUrlPattern}")
    public String casFilterUrlPattern;
    @Value("${cas.loginUrlPattern}")
    public String loginUrlPattern;

    @Value("${cas.logoutUrlPattern}")
    public String logoutUrlPattern;

    @Value("${cas.serviceFront}")
    private String homeUrl;

    @Value("${sso.home}")
    private String home;

    @Autowired
    private ThirdService thirdService;

    private final String SERVICE = "?service=";

    @ApiOperation(value = "测试")
    @GetMapping(value = "/test1")
    public DataResponse<OpenDriverInfoRes> test1(@RequestParam String date) {

        return DataResponse.of(thirdService.getDriverInfo(date));
    }

    /**
     * 单点登出
     * @return 单点登录页面链接
     */
    @ApiOperation(value = "单点登出")
    @GetMapping(value = "/logout")
    public DataResponse<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return DataResponse.of(String.join("", casServerUrlPrefix, logoutUrlPattern, SERVICE, home));
    }

    /**
     * 单点登录
     * @return 系统首页链接
     */
    @ApiOperation(value = "单点登录")
    @GetMapping(value = "/login")
    public DataResponse<String> login() {
        return DataResponse.of(String.join("", casServerUrlPrefix, loginUrlPattern, SERVICE, casServiceUrlPrefix, casFilterUrlPattern));
    }

    /**
     * index界面跳转
     * @param request request
     * @param response response
     * @throws IOException io流异常
     */
    @GetMapping(value = "/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(homeUrl + "?token=" + request.getSession().getAttribute("jwtToken"));
    }

    /**
     * 异常抛出界面
     * @param request request
     * @throws Throwable 异常
     */
    @RequestMapping("/error/exthrow")
    public void rethrow(HttpServletRequest request) throws Throwable {
        throw (Throwable) request.getAttribute("filter.error");
    }
}
