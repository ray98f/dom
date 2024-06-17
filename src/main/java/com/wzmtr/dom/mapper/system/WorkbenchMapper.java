package com.wzmtr.dom.mapper.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.system.TodoReqDTO;
import com.wzmtr.dom.dto.res.system.FlowNodeResDTO;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 获取工作台任务督办详情
     * @param id id
     * @return 工作台任务督办详情
     */
    List<TodoResDTO> queryChildTodo(String id);

    /**
     * 工作台任务督办审批
     * @param todoReqDTO 督办审批参数
     */
    void todoApproval(TodoReqDTO todoReqDTO);

    /**
     * 相同阶段的待办删除
     * @param res 待办信息
     * @param userId 用户id
     */
    void sameStageTodoDelete(TodoResDTO res, String userId);

    /**
     * 相同阶段的待办是否存在
     * @param todoResDTO 督办审批参数
     * @return 是否存在
     */
    Integer sameStageTodoIsExist(TodoResDTO todoResDTO);

    /**
     * 发待办/待阅
     * @param approvalReqDTO 待办/待阅 信息
     */
    void addTodo(ApprovalReqDTO approvalReqDTO);

    /**
     * 查询待办
     * @param processKey 流程名
     * @param reportId 报表id
     * @param nodes 节点列表
     * @return 待办列表
     */
    List<TodoResDTO> queryTodoByNodeForDaily(String processKey, String reportId, List<String> nodes);

    /**
     * 查询待办
     * @param processKey 流程名
     * @param reportId 报表id
     * @param nodes 节点列表
     * @return 待办列表
     */
    List<TodoResDTO> queryTodoByNodeForWeekly(String processKey, String reportId, List<String> nodes);

    /**
     * 查询待办
     * @param processKey 流程名
     * @param reportId 报表id
     * @param nodes 节点列表
     * @return 待办列表
     */
    List<TodoResDTO> queryTodoByNodeForMonthly(String processKey, String reportId, List<String> nodes);

    /**
     * 查询待办
     * @param parentId 父级id
     * @return 待办列表
     */
    List<TodoResDTO> queryTodoByParent(String parentId);

    /**
     * 查询下一个节点
     * @param nodeId 节点id
     * @return 节点名
     */
    String queryNextNode(String nodeId);

    /**
     * 查询节点
     * @param nodeId 节点id
     * @return 节点信息
     */
    FlowNodeResDTO nodeDetail(String nodeId);

    /**
     * 根据流程节点获取审批角色
     * @param nodeId 节点id
     * @return  审批角色
     */
    String queryRoleByNode(String nodeId);

    /**
     * 根据流程节点获取审批角色
     * @param ids
     * @return  审批角色
     */
    List<String> queryUserByRole(List<String> ids);

    /**
     * 获取审批人员
     * @param processKey
     * @param reportId
     * @return  获取审批人员
     */
    List<String> queryAuditorByReport(String processKey,String reportId);
}
