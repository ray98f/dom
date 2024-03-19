package com.wzmtr.dom.mapper.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.TodoReqDTO;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统-工作台
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Mapper
@Repository
public interface WorkbenchMapper {

    /**
     * 分页查询工作台任务督办列表
     * @param page 分页参数
     * @param type 类型 1待办,2待阅,3已办,4已阅
     * @param userId 用户id
     * @return 工作台任务督办列表
     */
    Page<TodoResDTO> todoPage(Page<TodoResDTO> page, String type, String userId);

    /**
     * 获取工作台任务督办详情
     * @param id id
     * @return 工作台任务督办详情
     */
    TodoResDTO todoDetail(String id);

    /**
     * 工作台任务督办审批
     * @param todoReqDTO 督办审批参数
     */
    void todoApproval(TodoReqDTO todoReqDTO);
}
