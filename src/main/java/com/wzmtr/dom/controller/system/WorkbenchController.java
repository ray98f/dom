package com.wzmtr.dom.controller.system;

import com.wzmtr.dom.dto.req.system.TodoReqDTO;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.system.WorkbenchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统-工作台
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@RestController
@RequestMapping("/sys/workbench")
@Api(tags = "系统-工作台")
@Validated
public class WorkbenchController {

    @Autowired
    private WorkbenchService workbenchService;

    /**
     * 分页查询工作台任务督办列表
     * @param type 类型 1待办,2待阅,3已办,4已阅
     * @param pageReqDTO 分页参数
     * @return 工作台任务督办列表
     */
    @GetMapping("/todo/page")
    @ApiOperation(value = "工作台任务督办列表(分页)")
    public PageResponse<TodoResDTO> todoPage(@RequestParam String type, @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(workbenchService.todoPage(type, pageReqDTO));
    }

    /**
     * 获取工作台任务督办详情
     * @param id id
     * @return 工作台任务督办详情
     */
    @GetMapping("/todo/detail")
    @ApiOperation(value = "工作台任务督办详情")
    public DataResponse<TodoResDTO> todoDetail(@RequestParam String id) {
        return DataResponse.of(workbenchService.todoDetail(id));
    }

    /**
     * 工作台任务督办审批
     * @param todoReqDTO 督办审批参数
     * @return 工作台任务督办审批
     */
    @PostMapping("/todo/approval")
    @ApiOperation(value = "工作台任务督办审批")
    public DataResponse<T> todoApproval(@RequestBody TodoReqDTO todoReqDTO) {
        workbenchService.todoApproval(todoReqDTO);
        return DataResponse.success();
    }
}
