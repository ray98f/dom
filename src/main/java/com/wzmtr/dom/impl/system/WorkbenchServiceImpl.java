package com.wzmtr.dom.impl.system;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.system.ReportUpdateReqDTO;
import com.wzmtr.dom.dto.req.system.TodoReqDTO;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.system.FlowNodeResDTO;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.WeeklyReportResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.BpmnFlowEnum;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateReportMapper;
import com.wzmtr.dom.mapper.system.WorkbenchMapper;
import com.wzmtr.dom.mapper.traffic.TrafficReportMapper;
import com.wzmtr.dom.mapper.vehicle.VehicleReportMapper;
import com.wzmtr.dom.service.system.WorkbenchService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统-工作台
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Service
public class WorkbenchServiceImpl implements WorkbenchService {

    @Autowired
    private VehicleReportMapper vehicleReportMapper;

    @Autowired
    private TrafficReportMapper trafficReportMapper;

    @Autowired
    private OperateReportMapper operateReportMapper;

    @Autowired
    private WorkbenchMapper workbenchMapper;

    @Override
    public Page<TodoResDTO> todoPage(String type, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return workbenchMapper.todoPage(pageReqDTO.of(), type, TokenUtils.getCurrentPersonId());
    }

    @Override
    public TodoResDTO todoDetail(String id) {
        TodoResDTO detail = workbenchMapper.todoDetail(id);

        //查询子代办
        List<TodoResDTO> childTodo = workbenchMapper.queryChildTodo(id);
        detail.setChildTodo(childTodo);

        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void todoApproval(TodoReqDTO todoReqDTO) {
        TodoResDTO res = workbenchMapper.todoDetail(todoReqDTO.getId());
        if (Objects.isNull(res)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        todoReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        // 更新为已办
        workbenchMapper.todoApproval(todoReqDTO);
        // 相同阶段的待办删除
//        if (StringUtils.isNotEmpty(todoReqDTO.getId()) && !todoReqDTO.getProcessKey().contains(CommonConstants.VEHICLE_CONTAINS)) {
            workbenchMapper.sameStageTodoDelete(res, TokenUtils.getCurrentPersonId());
//        }

        // 根据流程名进行审核流程
        switch (Objects.requireNonNull(BpmnFlowEnum.find(todoReqDTO.getProcessKey()))) {
            // 车辆部报表
            case vehicle_daily:
            case vehicle_weekly:
            case vehicle_monthly:
                vehicleReportApproval(todoReqDTO, res);
                break;
            // 客运部报表
            case traffic_daily:
                trafficDailyApproval(todoReqDTO, res);
                break;
            case traffic_weekly:
                trafficWeeklyApproval(todoReqDTO, res);
                break;
            case traffic_monthly:
                trafficMonthlyApproval(todoReqDTO, res);
                break;
            // 运营报表
            case operate_daily:
            case operate_weekly:
            case operate_monthly:
                operateReportApproval(todoReqDTO, res);
                break;
            default:
                break;
        }
    }

    @Override
    public void commitApproval(ApprovalReqDTO approvalReqDTO) {
        // 根据节点获取审批人员
        List<String> userList = getUserByNode(approvalReqDTO.getCurrentNode());
        if (StringUtils.isNotEmpty(userList)) {
            approvalReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
            approvalReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
            // 发送待办
            for (String u : userList) {
                approvalReqDTO.setId(TokenUtils.getUuId());
                approvalReqDTO.setApprovalUser(u);
                workbenchMapper.addTodo(approvalReqDTO);
            }
        }
    }

    /**
     * 车辆部报表审批
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     */
    @Transactional(rollbackFor = Exception.class)
    private void vehicleReportApproval(TodoReqDTO todoReqDTO, TodoResDTO todoResDTO) {
        // 标题
        String titlePrefix = todoResDTO.getTitle().replace("-请审批","").replace("-请查阅","");
//        switch (todoResDTO.getDataType()) {
//            case CommonConstants.DATA_TYPE_DAILY:
//                titlePrefix = "车辆部日报";
//                break;
//            case CommonConstants.DATA_TYPE_WEEKLY:
//                titlePrefix = "车辆部周报";
//                break;
//            case CommonConstants.DATA_TYPE_MONTHLY:
//                titlePrefix = "车辆部月报";
//                break;
//            default:
//                break;
//        }
        // 流转下一节点标记
        String goNextFlag = CommonConstants.ONE_STRING;
        String status = "";
        // 通过
        if (CommonConstants.ONE_STRING.equals(todoReqDTO.getApprovalStatus())) {
            status = CommonConstants.ONE_STRING;
        } else {
            status = CommonConstants.ZERO_STRING;
            goNextFlag = CommonConstants.ZERO_STRING;
            // 驳回时删除同阶段存在的未处理待办
//            workbenchMapper.sameStageTodoDelete(todoResDTO, TokenUtils.getCurrentPersonId());
        }
        updateVehicle(todoReqDTO.getReportId(), status, todoResDTO.getDataType());
        // 车辆部同阶段存在待办时不流转下一节点
//        if (workbenchMapper.sameStageTodoIsExist(todoResDTO) > 0) {
//            goNextFlag = CommonConstants.ZERO_STRING;
//        }
        if (CommonConstants.ONE_STRING.equals(goNextFlag)) {
            String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
            if (StringUtils.isEmpty(nextNode)) {
                // 下一步节点不存在时结束流程
                status = CommonConstants.TWO_STRING;
                updateOperate(todoReqDTO.getReportId(), status, todoResDTO.getDataType());
            } else {
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                String title = titlePrefix + "-请审批";
                // 下一节点不是待办时，更新报表为审批完成
                if (!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)) {
                    title = titlePrefix + "-请查阅";
                    status = CommonConstants.TWO_STRING;
                    updateVehicle(todoReqDTO.getReportId(), status, todoResDTO.getDataType());
                }
                // 节点流转
                for (String user : nextUserList) {
                    addTodo(title, todoReqDTO.getReportId(), todoResDTO.getReportTable(), nodeDetail.getNodeType(),
                            todoResDTO.getDataType(), nodeDetail.getFlowId(), nodeDetail.getNodeId(), user, CommonConstants.ZERO_STRING, null);
                }
            }
        }
    }

    /**
     * 修改车辆部报表状态
     *
     * @param id     报表id
     * @param status 状态
     * @param type   报表类型1日报,2周报,3月报
     */
    private void updateVehicle(String id, String status, String type) {
        ReportUpdateReqDTO modifyReq = new ReportUpdateReqDTO();
        modifyReq.setId(id);
        modifyReq.setStatus(status);
        modifyReq.setUpdateBy(TokenUtils.getCurrentPersonId());
        if (CommonConstants.ONE_STRING.equals(type)) {
            vehicleReportMapper.modifyDailyByFlow(modifyReq);
        } else if (CommonConstants.TWO_STRING.equals(type)) {
            vehicleReportMapper.modifyWeeklyByFlow(modifyReq);
        } else if (CommonConstants.THREE_STRING.equals(type)) {
            vehicleReportMapper.modifyMonthlyByFlow(modifyReq);
        }
    }

    /**
     * 客运部日报审批
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     */
    @Transactional(rollbackFor = Exception.class)
    private void trafficDailyApproval(TodoReqDTO todoReqDTO, TodoResDTO todoResDTO) {

        //驳回情况下更新报表为草稿
        if(CommonConstants.TWO_STRING.equals(todoReqDTO.getApprovalStatus())){
            DailyReportReqDTO dailyReportReqDTO = new DailyReportReqDTO();
            dailyReportReqDTO.setId(todoReqDTO.getReportId());
            dailyReportReqDTO.setStatus(CommonConstants.ZERO_STRING);
            dailyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
            trafficReportMapper.modifyDaily(dailyReportReqDTO);
        }

        // 标题
        String titlePrefix = todoResDTO.getTitle().replace("-请审批","").replace("-请查阅","");
        // 若属于第一个节点，需查询这三个节点下是否还存在未审批的待办，没有则可以流转到下一节点
        List<String> nodes = Arrays.asList(CommonConstants.TRAFFIC_DAILY_NODE1_SUB);
        // 获取各子报表
        List<DailyReportResDTO> dailyList = trafficReportMapper.queryDailyById(todoReqDTO.getReportId());

        if (nodes.contains(todoResDTO.getCurrentNode())) {
            List<TodoResDTO> todoList = workbenchMapper.queryTodoByNode(todoReqDTO.getProcessKey(), todoReqDTO.getReportId(), nodes);
            List<String> status = dailyList.stream().map(DailyReportResDTO::getStatus).collect(Collectors.toList());
            if ((Objects.isNull(todoList) || todoList.size() == 0) && !status.contains(CommonConstants.ZERO_STRING)) {
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);
                // 节点流转
                for (String user : nextUserList) {
                    String title = titlePrefix + "-请审批";
                    // 主报表添加待办
                    String parentId = addTodo(title, dailyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_DAILY_REPORT, nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_DAILY, nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                            user, CommonConstants.ZERO_STRING, null);
                    // 发送子报表待办（隐藏）
                    for (DailyReportResDTO r : dailyList) {
                        if(CommonConstants.ONE_STRING.equals(r.getStatus())){
                            addTodo(title, r.getId(), CommonConstants.TRAFFIC_DAILY_REPORT,
                                    nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_DAILY,
                                    nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                                    user, CommonConstants.ONE_STRING, parentId);
                        }
                    }
                }
            }
        } else {
            // 客运部报表子待办/主待办联动修改
            ReportUpdateReqDTO modifyReq = childTodoUpdate(todoReqDTO, todoResDTO);
            // 获取各子报表
            String goNextFlag = CommonConstants.ONE_STRING;
            for (DailyReportResDTO daily : dailyList) {
                // 存在草稿、一级审核状态下，不流转
                if (CommonConstants.ONE_STRING.equals(daily.getStatus()) || CommonConstants.ZERO_STRING.equals(daily.getStatus())) {
                    goNextFlag = CommonConstants.ZERO_STRING;
                    break;
                }
            }
            // 子报表都审核完毕,流转下一节点
            if (goNextFlag.equals(CommonConstants.ONE_STRING)) {
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);
                String title = titlePrefix + "-请审批";
                // 下一节点不是待办,则更新报表为审批完成
                if (!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)) {
                    title = titlePrefix + "-请查阅";
                    trafficReportMapper.dailyApprovalComplete(modifyReq);
                }
                // 节点流转
                for (String user : nextUserList) {
                    addTodo(DateUtil.formatDate(dailyList.get(0).getDailyDate()) + title,
                            dailyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_DAILY_REPORT,
                            nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_DAILY,
                            nodeDetail.getFlowId(),
                            nodeDetail.getNodeId(), user, CommonConstants.ZERO_STRING, null);
                }
            }
        }
    }

    /**
     * 客运部周报审批
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     */
    @Transactional(rollbackFor = Exception.class)
    private void trafficWeeklyApproval(TodoReqDTO todoReqDTO, TodoResDTO todoResDTO) {

        //驳回情况下更新报表为草稿
        if(CommonConstants.TWO_STRING.equals(todoReqDTO.getApprovalStatus())){
            WeeklyReportReqDTO weeklyReportReqDTO = new WeeklyReportReqDTO();
            weeklyReportReqDTO.setId(todoReqDTO.getReportId());
            weeklyReportReqDTO.setStatus(CommonConstants.ZERO_STRING);
            weeklyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
            trafficReportMapper.modifyWeekly(weeklyReportReqDTO);
        }

        // 标题
        String titlePrefix = todoResDTO.getTitle().replace("-请审批","").replace("-请查阅","");

        // 若属于第一个节点，需查询这三个节点下是否还存在未审批的待办，没有则可以流转到下一节点
        List<String> nodes = Arrays.asList(CommonConstants.TRAFFIC_WEEKLY_NODE1_SUB);
        // 获取各子报表
        List<WeeklyReportResDTO> weeklyList = trafficReportMapper.queryWeeklyById(todoReqDTO.getReportId());

        if (nodes.contains(todoResDTO.getCurrentNode())) {
            List<TodoResDTO> todoList = workbenchMapper.queryTodoByNode(todoReqDTO.getProcessKey(), todoReqDTO.getReportId(), nodes);
            List<String> status = weeklyList.stream().map(WeeklyReportResDTO::getStatus).collect(Collectors.toList());
            if ((Objects.isNull(todoList) || todoList.size() == 0) && !status.contains(CommonConstants.ZERO_STRING)) {
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);
                // 节点流转
                for (String user : nextUserList) {
                    String title = titlePrefix + "-请审批";
                    // 主报表添加待办
                    String parentId = addTodo(title, weeklyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_WEEKLY_REPORT, nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_WEEKLY, nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                            user, CommonConstants.ZERO_STRING, null);
                    // 判断子报表状态 为1审批中则发送待办
                    for (WeeklyReportResDTO r : weeklyList) {
                        if(CommonConstants.ONE_STRING.equals(r.getStatus())){
                            addTodo(title, r.getId(), CommonConstants.TRAFFIC_WEEKLY_REPORT,
                                    nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_WEEKLY,
                                    nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                                    user, CommonConstants.ONE_STRING, parentId);
                        }
                    }
                }
            }
        } else {
            // 客运部报表子待办/主待办联动修改
            ReportUpdateReqDTO modifyReq = childTodoUpdate(todoReqDTO, todoResDTO);
            // 获取各子报表
            String goNextFlag = CommonConstants.ONE_STRING;
            for (WeeklyReportResDTO weekly : weeklyList) {
                // 存在草稿、一级审核状态下，不流转
                if(!todoReqDTO.getReportId().equals(weekly.getId())){
                    if("traffic_weekly_node3".equals(todoResDTO.getCurrentNode())){
                        if(CommonConstants.TWO_STRING.equals(todoReqDTO.getApprovalStatus())){
                            goNextFlag = CommonConstants.ZERO_STRING;
                            break;
                        }
                        if (CommonConstants.THREE_STRING.equals(weekly.getStatus()) || CommonConstants.ZERO_STRING.equals(weekly.getStatus())) {
                            goNextFlag = CommonConstants.ZERO_STRING;
                            break;
                        }
                    }else{
                        if (CommonConstants.ONE_STRING.equals(weekly.getStatus()) || CommonConstants.ZERO_STRING.equals(weekly.getStatus())) {
                            goNextFlag = CommonConstants.ZERO_STRING;
                            break;
                        }
                    }
                }

            }
            // 子报表都审核完毕,流转下一节点
            if (goNextFlag.equals(CommonConstants.ONE_STRING)) {
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);
                String title = titlePrefix + "-请审批";
                // 下一节点不是待办,则更新报表为审批完成
                if (!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)) {
                    title = titlePrefix + "-请查阅";
                    trafficReportMapper.weeklyApprovalComplete(modifyReq);
                }
                // 节点流转
                for (String user : nextUserList) {
                    String parentId = addTodo(DateUtil.formatDate(weeklyList.get(0).getStartDate()) + "-" + DateUtil.formatDate(weeklyList.get(0).getEndDate()) + title,
                            weeklyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_WEEKLY_REPORT,
                            nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_WEEKLY,
                            nodeDetail.getFlowId(),
                            nodeDetail.getNodeId(), user, CommonConstants.ZERO_STRING, null);

                    //子报表审批
                    if(nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)){
                        for (WeeklyReportResDTO r : weeklyList) {
                            addTodo(title, r.getId(), CommonConstants.TRAFFIC_WEEKLY_REPORT,
                                    nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_WEEKLY,
                                    nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                                    user, CommonConstants.ONE_STRING, parentId);
                        }
                    }
                }
            }
        }
    }

    /**
     * 客运部月报审批
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     */
    @Transactional(rollbackFor = Exception.class)
    private void trafficMonthlyApproval(TodoReqDTO todoReqDTO, TodoResDTO todoResDTO) {

        //驳回情况下更新报表为草稿
        if(CommonConstants.TWO_STRING.equals(todoReqDTO.getApprovalStatus())){
            MonthlyReportReqDTO monthlyReportReqDTO = new MonthlyReportReqDTO();
            monthlyReportReqDTO.setId(todoReqDTO.getReportId());
            monthlyReportReqDTO.setStatus(CommonConstants.ZERO_STRING);
            monthlyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
            trafficReportMapper.modifyMonthly(monthlyReportReqDTO);
        }

        // 标题
        String titlePrefix = todoResDTO.getTitle().replace("-请审批","").replace("-请查阅","");

        // 若属于第一个节点，需查询这三个节点下是否还存在未审批的待办，没有则可以流转到下一节点
        List<String> nodes = Arrays.asList(CommonConstants.TRAFFIC_MONTHLY_NODE1_SUB);
        // 获取各子报表
        List<MonthlyReportResDTO> monthlyList = trafficReportMapper.queryMonthlyById(todoReqDTO.getReportId());

        if (nodes.contains(todoResDTO.getCurrentNode())) {
            List<TodoResDTO> todoList = workbenchMapper.queryTodoByNode(todoReqDTO.getProcessKey(), todoReqDTO.getReportId(), nodes);
            List<String> status = monthlyList.stream().map(MonthlyReportResDTO::getStatus).collect(Collectors.toList());
            if ((Objects.isNull(todoList) || todoList.size() == 0) && !status.contains(CommonConstants.ZERO_STRING)) {
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);
                // 节点流转
                for (String user : nextUserList) {
                    String title = titlePrefix + "-请审批";
                    // 主报表添加待办
                    String parentId = addTodo(title, monthlyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_MONTHLY_REPORT, nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_MONTHLY, nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                            user, CommonConstants.ZERO_STRING, null);
                    // 判断子报表状态 为1审批中则发送待办
                    for (MonthlyReportResDTO r : monthlyList) {
                        if(CommonConstants.ONE_STRING.equals(r.getStatus())){
                            addTodo(title, r.getId(), CommonConstants.TRAFFIC_MONTHLY_REPORT,
                                    nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_MONTHLY,
                                    nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                                    user, CommonConstants.ONE_STRING, parentId);
                        }
                    }
                }
            }
        } else {
            // 客运部月报子待办/主待办联动修改
            ReportUpdateReqDTO modifyReq = childTodoUpdate(todoReqDTO, todoResDTO);
            // 获取各子报表
            String goNextFlag = CommonConstants.ONE_STRING;
            for (MonthlyReportResDTO monthly : monthlyList) {
                // 存在草稿、一级审核状态下，不流转
                if(!todoReqDTO.getReportId().equals(monthly.getId())){
                    if("traffic_weekly_node3".equals(todoResDTO.getCurrentNode())){
                        if(CommonConstants.TWO_STRING.equals(todoReqDTO.getApprovalStatus())){
                            goNextFlag = CommonConstants.ZERO_STRING;
                            break;
                        }
                        if (CommonConstants.THREE_STRING.equals(monthly.getStatus()) || CommonConstants.ZERO_STRING.equals(monthly.getStatus())) {
                            goNextFlag = CommonConstants.ZERO_STRING;
                            break;
                        }
                    }else{
                        if (CommonConstants.ONE_STRING.equals(monthly.getStatus()) || CommonConstants.ZERO_STRING.equals(monthly.getStatus())) {
                            goNextFlag = CommonConstants.ZERO_STRING;
                            break;
                        }
                    }
                }
            }
            // 子报表都审核完毕,流转下一节点
            if (goNextFlag.equals(CommonConstants.ONE_STRING)) {
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);
                String title = titlePrefix + "-请审批";
                // 下一节点不是待办,则更新报表为审批完成
                if (!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)) {
                    title = titlePrefix + "-请查阅";
                    trafficReportMapper.monthlyApprovalComplete(modifyReq);
                }
                // 节点流转
                for (String user : nextUserList) {
                    String parentId = addTodo(DateUtil.formatDate(monthlyList.get(0).getStartDate()) + "-" + DateUtil.formatDate(monthlyList.get(0).getEndDate()) + title,
                            monthlyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_MONTHLY_REPORT,
                            nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_MONTHLY,
                            nodeDetail.getFlowId(),
                            nodeDetail.getNodeId(), user, CommonConstants.ZERO_STRING, null);

                    //子报表审批
                    if(nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)){
                        for (MonthlyReportResDTO r : monthlyList) {
                            addTodo(title, r.getId(), CommonConstants.TRAFFIC_MONTHLY_REPORT,
                                    nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_MONTHLY,
                                    nodeDetail.getFlowId(), nodeDetail.getNodeId(),
                                    user, CommonConstants.ONE_STRING, parentId);
                        }
                    }

                }
            }
        }
    }

    /**
     * 客运部报表子待办/主待办联动修改
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     * @return 报表状态修改类
     */
    private ReportUpdateReqDTO childTodoUpdate(TodoReqDTO todoReqDTO, TodoResDTO todoResDTO) {
        // 子待办
        String modifyParenFlag = CommonConstants.ONE_STRING;
        String parenStatus = CommonConstants.ONE_STRING;
        List<TodoResDTO> subTodoList = workbenchMapper.queryTodoByParent(todoResDTO.getParentId());
        // 存在未完成的待办
        for (TodoResDTO todo : subTodoList) {
            if (CommonConstants.ZERO_STRING.equals(todo.getStatus()) && !todo.getId().equals(todoReqDTO.getId())) {
                modifyParenFlag = CommonConstants.ZERO_STRING;
                break;
            }
        }
        // 存在驳回的情况,父待办则为驳回
        for (TodoResDTO todo : subTodoList) {
            if (CommonConstants.TWO_STRING.equals(todo.getApprovalStatus())) {
                parenStatus = CommonConstants.TWO_STRING;
                break;
            }
        }
        // 将主待办更新为已办
        if (CommonConstants.ONE_STRING.equals(modifyParenFlag)) {
            TodoReqDTO parentTodoReq = new TodoReqDTO();
            parentTodoReq.setId(todoResDTO.getParentId());
            parentTodoReq.setApprovalStatus(parenStatus);
            parentTodoReq.setUpdateBy(TokenUtils.getCurrentPersonId());
            workbenchMapper.todoApproval(parentTodoReq);
        }
        // 更新子报表状态
        ReportUpdateReqDTO modifyReq = new ReportUpdateReqDTO();
        modifyReq.setId(todoReqDTO.getReportId());
        modifyReq.setUpdateBy(TokenUtils.getCurrentPersonId());
        if (CommonConstants.ONE_STRING.equals(todoReqDTO.getApprovalStatus())) {
            modifyReq.setStatus(CommonConstants.THREE_STRING);
            if("traffic_weekly_node3".equals(todoResDTO.getCurrentNode()) || "traffic_monthly_node3".equals(todoResDTO.getCurrentNode())){
                modifyReq.setStatus(CommonConstants.FOUR_STRING);
            }
        } else {
            modifyReq.setStatus(CommonConstants.ZERO_STRING);
        }
        if (CommonConstants.ONE_STRING.equals(todoResDTO.getDataType())) {
            trafficReportMapper.modifyDailyByFlow(modifyReq);
        } else if (CommonConstants.TWO_STRING.equals(todoResDTO.getDataType())) {
            trafficReportMapper.modifyWeeklyByFlow(modifyReq);
        } else if (CommonConstants.THREE_STRING.equals(todoResDTO.getDataType())) {
            trafficReportMapper.modifyMonthlyByFlow(modifyReq);
        }
        return modifyReq;
    }

    /**
     * 运营日报/周报/月报表审批
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     */
    @Transactional(rollbackFor = Exception.class)
    private void operateReportApproval(TodoReqDTO todoReqDTO, TodoResDTO todoResDTO) {

        // 标题
        String titlePrefix = todoResDTO.getTitle().replace("-请审批","").replace("-请查阅","");

        // 标题
//        String titlePrefix = "";
//        switch (todoResDTO.getDataType()) {
//            case CommonConstants.DATA_TYPE_DAILY:
//                titlePrefix = "运营日报";
//                break;
//            case CommonConstants.DATA_TYPE_WEEKLY:
//                titlePrefix = "运营周报";
//                break;
//            case CommonConstants.DATA_TYPE_MONTHLY:
//                titlePrefix = "运营月报";
//                break;
//            default:
//                break;
//        }

        // 流转下一节点标记
        String goNextFlag = CommonConstants.ONE_STRING;
        String status = "";
        // 通过
        if (CommonConstants.ONE_STRING.equals(todoReqDTO.getApprovalStatus())) {
            status = CommonConstants.ONE_STRING;
        } else {
            status = CommonConstants.ZERO_STRING;
            goNextFlag = CommonConstants.ZERO_STRING;
        }
        updateOperate(todoReqDTO.getReportId(), status, todoResDTO.getDataType());

        if (CommonConstants.ONE_STRING.equals(goNextFlag)) {
            String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
            if (StringUtils.isEmpty(nextNode)) {
                // 下一步节点不存在时结束流程
                status = CommonConstants.TWO_STRING;
                updateOperate(todoReqDTO.getReportId(), status, todoResDTO.getDataType());
            } else {
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                String title = titlePrefix + "-请审批";
                // 下一节点不是待办时，更新报表为审批完成
                if (!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)) {
                    title = titlePrefix + "-请查阅";
                    status = CommonConstants.TWO_STRING;
                    updateOperate(todoReqDTO.getReportId(), status, todoResDTO.getDataType());
                }
                // 节点流转
                for (String user : nextUserList) {
                    addTodo(title, todoReqDTO.getReportId(), todoResDTO.getReportTable(), nodeDetail.getNodeType(),
                            todoResDTO.getDataType(), nodeDetail.getFlowId(), nodeDetail.getNodeId(), user, CommonConstants.ZERO_STRING, null);
                }
            }
        }
    }

    /**
     * 修改运营报表状态
     * @param id     报表id
     * @param status 状态
     * @param type   报表类型1日报,2周报,3月报
     */
    private void updateOperate(String id, String status, String type) {
        ReportUpdateReqDTO modifyReq = new ReportUpdateReqDTO();
        modifyReq.setId(id);
        modifyReq.setStatus(status);
        modifyReq.setUpdateBy(TokenUtils.getCurrentPersonId());
        if (CommonConstants.ONE_STRING.equals(type)) {
            operateReportMapper.modifyDailyByFlow(modifyReq);
        } else if (CommonConstants.TWO_STRING.equals(type)) {
            operateReportMapper.modifyWeeklyByFlow(modifyReq);
        } else if (CommonConstants.THREE_STRING.equals(type)) {
            operateReportMapper.modifyMonthlyByFlow(modifyReq);
        }
    }

    /**
     * 发送待办
     * @param title       代办标题
     * @param reportId    报表id
     * @param reportTable 报表表明
     * @param todoType    事项类型:1待办,2待阅
     * @param dataType    所属数据类型:1日报,2周报,3月报
     * @param processKey  流程名
     * @param currentNode 当前审批节点名
     * @param userId      用户id
     * @param isHide      是否隐藏
     * @param parentId    父ID
     * @return 返回待办id
     */
    private String addTodo(String title, String reportId, String reportTable, String todoType, String dataType,
                           String processKey, String currentNode, String userId, String isHide, String parentId) {
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle(title);
        approvalReqDTO.setReportId(reportId);
        approvalReqDTO.setReportTable(reportTable);
        approvalReqDTO.setTodoType(todoType);
        approvalReqDTO.setDataType(dataType);
        approvalReqDTO.setProcessKey(processKey);
        approvalReqDTO.setCurrentNode(currentNode);
        approvalReqDTO.setId(TokenUtils.getUuId());
        approvalReqDTO.setApprovalUser(userId);
        approvalReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        if (StringUtils.isNotEmpty(isHide)) {
            approvalReqDTO.setIsHide(isHide);
        }
        if (StringUtils.isNotEmpty(parentId)) {
            approvalReqDTO.setParentId(parentId);
        }
        workbenchMapper.addTodo(approvalReqDTO);
        return approvalReqDTO.getId();
    }

    /**
     * 根据节点获取用户
     * @param nodeId 节点id
     * @return 用户id列表
     */
    private List<String> getUserByNode(String nodeId) {
        String roleStr = workbenchMapper.queryRoleByNode(nodeId);
        List<String> userList = null;
        if (StringUtils.isNotEmpty(roleStr)) {
            List<String> roleList = Arrays.asList(roleStr.split(","));
            userList = workbenchMapper.queryUserByRole(roleList);
        }
        return userList;
    }

}
