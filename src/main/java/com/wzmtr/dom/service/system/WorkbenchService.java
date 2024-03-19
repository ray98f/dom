package com.wzmtr.dom.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;

/**
 * 系统-工作台
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
public interface WorkbenchService {

    /**
     * 分页查询工作台任务督办列表
     * @param pageReqDTO 分页参数
     * @return 工作台任务督办列表
     */
    Page<TodoResDTO> todoPage(PageReqDTO pageReqDTO);

    /**
     * 获取工作台任务督办详情
     * @param id id
     * @return 工作台任务督办详情
     */
    TodoResDTO todoDetail(String id);
}
