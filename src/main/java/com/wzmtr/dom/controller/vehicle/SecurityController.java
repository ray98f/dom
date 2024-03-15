package com.wzmtr.dom.controller.vehicle;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.req.vehicle.SecurityReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.dto.res.vehicle.SecurityResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.service.vehicle.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 车辆部-安全运营
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:19
 */
@RestController
@RequestMapping("/vehicle/security")
@Api(tags = "车辆部-安全运营")
@Validated
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    /**
     * 安全运营-列表
     * @param pageReqDTO 分页参数
     * @return 安全运营列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "安全运营-列表")
    public PageResponse<SecurityResDTO> page(@RequestParam String dataType,
                                             @RequestParam String startDate,
                                             @RequestParam String endDate,
                                             @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(securityService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 安全运营-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "安全运营")
    public DataResponse<SecurityResDTO> add(@RequestParam String id) {
        return DataResponse.of(securityService.detail(id));
    }

    /**
     * 安全运营-新增
     * @param securityReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "安全运营-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody SecurityReqDTO securityReqDTO) {
        securityService.add(currentLoginUser,securityReqDTO);
        return DataResponse.success();
    }

    /**
     * 安全运营-编辑
     * @param securityReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "安全运营-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody SecurityReqDTO securityReqDTO) {
        securityService.modify(currentLoginUser,securityReqDTO);
        return DataResponse.success();
    }


}
