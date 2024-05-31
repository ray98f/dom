package com.wzmtr.dom.controller.traffic;

import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.dto.req.traffic.hotline.HandoverAddDataReqDTO;
import com.wzmtr.dom.dto.req.traffic.hotline.HotLineHandoverAddReqDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineHandoverListResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.traffic.HotLineHandoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客运部-需转交其他部门做处理事项
 * @author  Ray
 * @version 1.0
 * @date 2024/05/06
 */
@RestController
@RequestMapping("/traffic/hotline/handover")
@Api(tags = "客运部-需转交其他部门做处理事项")
@Validated
public class HotLineHandoverController {
    @Autowired
    private HotLineHandoverService hotLineHandoverService;

    /**
     * 分页查询需转交其他部门做处理事项记录列表
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param dataType   数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 需转交其他部门做处理事项记录列表
     */
    @GetMapping("/record/page")
    @ApiOperation(value = "分页查询需转交其他部门做处理事项记录列表")
    public PageResponse<HotLineHandoverListResDTO> pageRecord(@RequestParam(required = false) String startDate,
                                                              @RequestParam(required = false) String endDate,
                                                              @RequestParam String dataType,
                                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(hotLineHandoverService.pageRecord(startDate, endDate, dataType, pageReqDTO));
    }

    /**
     * 分页查询需转交其他部门做处理事项详情列表
     * @param id         id
     * @param date       日期
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param dataType   数据类型 1:日报 2:周报 3:月报
     * @param pageReqDTO 分页参数
     * @return 需转交其他部门做处理事项详情列表
     */
    @GetMapping("/info/page")
    @ApiOperation(value = "分页查询需转交其他部门做处理事项详情列表")
    public PageResponse<HotLineHandoverDetailResDTO> pageInfo(@RequestParam(required = false) String id,
                                                              @RequestParam(required = false) String date,
                                                              @RequestParam(required = false) String startDate,
                                                              @RequestParam(required = false) String endDate,
                                                              @RequestParam String dataType,
                                                              @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(hotLineHandoverService.pageInfo(id, date, dataType, startDate, endDate, pageReqDTO));
    }

    /**
     * 新增需转交其他部门做处理事项记录
     * @param handoverAddReqDTO 需转交其他部门做处理事项记录
     * @return 成功
     */
    @PostMapping("/record/add")
    @ApiOperation(value = "新增需转交其他部门做处理事项记录")
    public DataResponse<T> addRecord(@CurrUser CurrentLoginUser currentLoginUser, @RequestBody HotLineHandoverAddReqDTO handoverAddReqDTO) {
        hotLineHandoverService.addRecord(currentLoginUser,handoverAddReqDTO);
        return DataResponse.success();
    }

    /**
     * 新增需转交其他部门做处理事项详情
     * @param handoverAddDataReqDTO 需转交其他部门做处理事项详情
     * @return 成功
     */
    @PostMapping("/info/add")
    @ApiOperation(value = "新增需转交其他部门做处理事项记录")
    public DataResponse<T> addInfo(@RequestBody HandoverAddDataReqDTO handoverAddDataReqDTO) {
        hotLineHandoverService.addInfo(handoverAddDataReqDTO);
        return DataResponse.success();
    }

    /**
     * 编辑需转交其他部门做处理事项详情
     * @param handoverAddDataReqDTO 需转交其他部门做处理事项详情参数
     * @return 成功
     */
    @PostMapping("/info/modify")
    @ApiOperation(value = "编辑需转交其他部门做处理事项详情")
    public DataResponse<T> modifyInfo(@RequestBody HandoverAddDataReqDTO handoverAddDataReqDTO) {
        hotLineHandoverService.modifyInfo(handoverAddDataReqDTO);
        return DataResponse.success();
    }

    /**
     * 删除需转交其他部门做处理事项记录
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/record/delete")
    @ApiOperation(value = "删除调度命令记录")
    public DataResponse<T> deleteRecord(@RequestBody BaseIdsEntity baseIdsEntity) {
        hotLineHandoverService.deleteRecord(baseIdsEntity.getIds());
        return DataResponse.success();
    }

    /**
     * 删除需转交其他部门做处理事项详情
     * @param baseIdsEntity ids
     * @return 成功
     */
    @PostMapping("/order/delete")
    @ApiOperation(value = "删除需转交其他部门做处理事项详情")
    public DataResponse<T> deleteOrder(@RequestBody BaseIdsEntity baseIdsEntity) {
        hotLineHandoverService.deleteOrder(baseIdsEntity.getIds());
        return DataResponse.success();
    }
}
