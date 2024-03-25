package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.common.SidReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverListReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineImportantAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.HotLineHandoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 16:41
 */
@RestController
@RequestMapping("/traffic/hotline/handover")
@Api(tags = "客运部-需转交其他部门做处理事项")
@Validated
public class HotLineHandoverController {
    @Autowired
    private HotLineHandoverService hotLineHandoverService;

    /**
     * 重要热线内容-列表
     *
     * @return 重要热线内容
     */
    @PostMapping("/list")
    @ApiOperation(value = "重要热线内容-列表")
    public PageResponse<HotLineHandoverListResDTO> page(@RequestBody HotLineHandoverListReqDTO reqDTO) {
        return PageResponse.of(hotLineHandoverService.list(reqDTO));
    }

    /**
     * 重要热线内容-详情
     *
     * @return 重要热线内容
     */
    @PostMapping("/detail")
    @ApiOperation(value = "重要热线内容")
    public DataResponse<HotLineHandoverDetailResDTO> detail(@RequestBody SidReqDTO reqDTO) {
        return DataResponse.of(hotLineHandoverService.detail(reqDTO));
    }

    @PostMapping("/acc")
    @ApiOperation(value = "重要热线内容")
    public DataResponse<HotLineHandoverDetailResDTO> acc(@RequestBody SidReqDTO reqDTO) {
        return DataResponse.of(hotLineHandoverService.acc(reqDTO));
    }

    /**
     * 重要热线内容-新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要热线内容-新增")
    public DataResponse<T> add(@RequestBody HotLineHandoverAddReqDTO reqDTO) {
        hotLineHandoverService.add(reqDTO);
        return DataResponse.success();
    }

    /**
     * 重要热线内容-编辑
     *
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "重要热线内容-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                                  @Validated({ValidationGroup.modify.class})
                                  @RequestBody HotLineHandoverAddReqDTO req) {
        hotLineHandoverService.modify(currentLoginUser, req);
        return DataResponse.success();
    }
}
