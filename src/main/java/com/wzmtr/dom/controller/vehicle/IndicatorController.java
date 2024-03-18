package com.wzmtr.dom.controller.vehicle;

import cn.hutool.core.date.DateUtil;
import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
        int a=101;
        int b=10000;
        Double c = new Double(Math.round(a*10000/b)/10000.0);
        System.out.println(c);
        /*CurrentLoginUser user = new CurrentLoginUser("admin", "admin", "系统管理员", "A","","", "A02","","","","","","");
        String jwtToken = TokenUtils.createSimpleToken(user);
        System.out.println(jwtToken);

        // 获取前一天的日期
        java.util.Date yesterday = DateUtil.yesterday();
        // 打印结果
        System.out.println("昨天的日期是：" + DateUtil.formatDate( DateUtil.yesterday()));

        System.out.println(DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parseDate("2024-03-11"), 1)));*/

    }


    /**
     * 重要指标-列表
     * @param pageReqDTO 分页参数
     * @return 重要指标列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "重要指标-列表")
    public PageResponse<IndicatorResDTO> page(@RequestParam String dataType,
                                              @RequestParam String startDate,
                                              @RequestParam String endDate,
                                                    @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(indicatorService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 重要指标-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "重要指标")
    public DataResponse<IndicatorResDTO> add(@RequestParam String id) {
        return DataResponse.of(indicatorService.detail(id));
    }

    /**
     * 重要指标-新增
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要指标-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.add(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-编辑
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "重要指标-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.modify(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "重要指标-删除)")
    public DataResponse<T> delete(@RequestBody  BaseIdsEntity baseIdsEntity) {
        indicatorService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }

}
