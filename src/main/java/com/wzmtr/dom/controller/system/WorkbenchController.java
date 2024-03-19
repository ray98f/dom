package com.wzmtr.dom.controller.system;

import com.wzmtr.dom.dto.res.system.TodoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.system.WorkbenchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param pageReqDTO 分页参数
     * @return 工作台任务督办列表
     */
    @GetMapping("/todo/page")
    @ApiOperation(value = "工作台任务督办列表(分页)")
    public PageResponse<TodoResDTO> todoPage(@Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(workbenchService.todoPage(pageReqDTO));
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
}
