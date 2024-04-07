package com.wzmtr.dom.controller.system;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.res.system.AllStationResDTO;
import com.wzmtr.dom.dto.res.system.StationResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionRecordResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.system.StationService;
import com.wzmtr.dom.service.traffic.ProductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 车站管理
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/21 10:34
 */
@RestController
@RequestMapping("/sys/station")
@Api(tags = "系统参数-车站管理")
@Validated
public class StationController {

    @Autowired
    private StationService stationService;

    /**
     * 车站-列表
     * @param lineCode 线路 01 、 02
     * @return 安全生产情况记录-列表
     */
    @GetMapping("/allList")
    @ApiOperation(value = "车站-列表")
    public DataResponse<AllStationResDTO> allList(@RequestParam(required = false) String lineCode) {
        return DataResponse.of(stationService.allList(lineCode));
    }

}