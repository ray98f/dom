package com.wzmtr.dom.controller.vehicle;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:19
 */
@RestController
@RequestMapping("/vehicle/indicator")
@Api(tags = "车辆部-重要指标")
@Validated
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    public static void main(String[] args){
        CurrentLoginUser user = new CurrentLoginUser("admin", "admin", "系统管理员", "A","","", "A02","","","","","","");
        String jwtToken = TokenUtils.createSimpleToken(user);
        System.out.println(jwtToken);
    }

    /**
     * 重要指标-新增
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要指标-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Valid @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.add(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }




}
