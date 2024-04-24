package com.wzmtr.dom.impl.system;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return workbenchMapper.todoPage(pageReqDTO.of(), type, TokenUtils.getCurrentPersonId());
    }

    @Override
    public TodoResDTO todoDetail(String id) {
        return workbenchMapper.todoDetail(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void todoApproval(TodoReqDTO todoReqDTO) {
        TodoResDTO res = workbenchMapper.todoDetail(todoReqDTO.getId());
        if (Objects.isNull(res)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        todoReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());

        //更新为已办
        workbenchMapper.todoApproval(todoReqDTO);

        // todo 根据流程配置进行下一步审核
        switch (Objects.requireNonNull(BpmnFlowEnum.find(todoReqDTO.getProcessKey()))){
            case vehicle_daily:
            case vehicle_weekly:
            case vehicle_monthly:
                vehicleReportApproval(todoReqDTO,res);
                break;
            case traffic_daily:
                trafficDailyApproval(todoReqDTO,res);
                break;
            case traffic_weekly:
                trafficWeeklyApproval(todoReqDTO,res);
                break;
            case traffic_monthly:
                trafficMonthlyApproval(todoReqDTO,res);
                break;
            case operate_daily:
            case operate_weekly:
            case operate_monthly:
                operateReportApproval(todoReqDTO,res);
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
        String titlePrefix = "";
        switch (todoResDTO.getDataType()) {
            case CommonConstants.DATA_TYPE_DAILY:
                titlePrefix = "车辆部日报";
                break;
            case CommonConstants.DATA_TYPE_WEEKLY:
                titlePrefix = "车辆部周报";
                break;
            case CommonConstants.DATA_TYPE_MONTHLY:
                titlePrefix = "车辆部月报";
                break;
            default:
                break;
        }
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
        updateVehicle(todoReqDTO.getReportId(), status, todoResDTO.getDataType());

        if (CommonConstants.ONE_STRING.equals(goNextFlag)) {
            String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
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
                        todoResDTO.getDataType(), nodeDetail.getFlowId(), nodeDetail.getNodeId(), user, "0", null);
            }
        }
    }

    /**
     * 修改车辆部报表状态
     * @param id 报表id
     * @param status 状态
     * @param type 报表类型1日报,2周报,3月报
     */
    private void updateVehicle(String id, String status, String type) {
        ReportUpdateReqDTO modifyReq = new ReportUpdateReqDTO();
        modifyReq.setId(id);
        modifyReq.setStatus(status);
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
     *
     * */
    @Transactional(rollbackFor = Exception.class)
    private void trafficDailyApproval(TodoReqDTO todoReqDTO,TodoResDTO todoResDTO){

        // 若属于第一个节点，需查询这三个节点下是否还存在未审批的待办，没有则可以流转到下一节点
        List<String> nodes = Arrays.asList(CommonConstants.TRAFFIC_DAILY_NODE1_SUB);
        if(nodes.contains(todoResDTO.getCurrentNode())){

            List<TodoResDTO> todoList = workbenchMapper.queryTodoByNode(todoReqDTO.getProcessKey(),todoReqDTO.getReportId(),nodes);
            if(todoList==null){
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                //获取各子报表
                List<DailyReportResDTO> dailyList = trafficReportMapper.queryDailyById(todoReqDTO.getReportId());

                //节点流转
                for(String u:nextUserList){
                    String title = DateUtil.formatDate(dailyList.get(0).getDailyDate()) + "运营日报-请审批";

                    //主报表添加待办
                    String parentId = addTodo(title,dailyList.get(0).getParentId(),CommonConstants.TRAFFIC_DAILY_REPORT,nodeDetail.getNodeType(),CommonConstants.DATA_TYPE_DAILY,nodeDetail.getFlowId(),nodeDetail.getNodeId(),u,null,null);

                    //判断子报表状态 为1审批中则发送待办
                    for(DailyReportResDTO r : dailyList){
                        addTodo(title, r.getId(), CommonConstants.TRAFFIC_DAILY_REPORT, nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_DAILY, nodeDetail.getFlowId(), nodeDetail.getNodeId(),u,CommonConstants.ONE_STRING,parentId);
                    }
                }
            }
        }else{

            //子待办
            String modifyParenFlag = CommonConstants.ONE_STRING;
            String parenStatus = CommonConstants.ONE_STRING;
            List<TodoResDTO> subTodoList = workbenchMapper.queryTodoByParent(todoResDTO.getParentId());
            for(TodoResDTO t:subTodoList){

                //存在未完成的待办
                if(CommonConstants.ZERO_STRING.equals(t.getStatus())){
                    modifyParenFlag = CommonConstants.ZERO_STRING;
                }
                //存在驳回的情况,父待办则为驳回
                if(CommonConstants.TWO_STRING.equals(t.getApprovalStatus())){
                    parenStatus = CommonConstants.TWO_STRING;
                }
            }

            //将主待办更新为已办
            if(CommonConstants.ONE_STRING.equals(modifyParenFlag)){
                TodoReqDTO parentTodoReq = new TodoReqDTO();
                parentTodoReq.setId(todoResDTO.getParentId());
                parentTodoReq.setApprovalStatus(parenStatus);
                workbenchMapper.todoApproval(parentTodoReq);
            }

            //更新子报表状态
            DailyReportReqDTO modifyReq = new DailyReportReqDTO();
            modifyReq.setId(todoReqDTO.getReportId());
            modifyReq.setUpdateBy(TokenUtils.getCurrentPersonId());

            //通过
            if(CommonConstants.ONE_STRING.equals(todoReqDTO.getApprovalStatus())){
                modifyReq.setStatus(CommonConstants.THREE_STRING);
            }else{
                modifyReq.setStatus(CommonConstants.ZERO_STRING);
            }
            trafficReportMapper.modifyDaily(modifyReq);

            //获取各子报表
            String goNextFlag = CommonConstants.ONE_STRING;
            List<DailyReportResDTO> dailyList = trafficReportMapper.queryDailyById(todoReqDTO.getReportId());

            for(DailyReportResDTO d:dailyList){

                //存在草稿、一级审核状态下，不流转
                if(CommonConstants.ONE_STRING.equals(d.getStatus())
                        || CommonConstants.ZERO_STRING.equals(d.getStatus())){
                    goNextFlag = CommonConstants.ZERO_STRING;
                }
            }

            // 子报表都审核完毕,流转下一节点
            if(goNextFlag.equals(CommonConstants.ONE_STRING)){
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                String title = "运营日报-请审批";

                //下一节点不是待办,则更新报表为审批完成
                if(!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)){
                    title = "运营日报-请查阅";
                    modifyReq.setStatus(CommonConstants.TWO_STRING);
                    trafficReportMapper.dailyApprovalComplete(modifyReq);
                }
                //节点流转
                for(String u:nextUserList){
                    addTodo(DateUtil.formatDate(dailyList.get(0).getDailyDate()) + title,
                            dailyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_DAILY_REPORT,
                            nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_DAILY,
                            nodeDetail.getFlowId(),
                            nodeDetail.getNodeId(),u,"0",null);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    private void trafficWeeklyApproval(TodoReqDTO todoReqDTO,TodoResDTO todoResDTO){

        // 若属于第一个节点，需查询这三个节点下是否还存在未审批的待办，没有则可以流转到下一节点
        List<String> nodes = Arrays.asList(CommonConstants.TRAFFIC_WEEKLY_NODE1_SUB);
        if(nodes.contains(todoResDTO.getCurrentNode())){

            List<TodoResDTO> todoList = workbenchMapper.queryTodoByNode(todoReqDTO.getProcessKey(),todoReqDTO.getReportId(),nodes);
            if(todoList==null){
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                //获取各子报表
                List<WeeklyReportResDTO> weeklyList = trafficReportMapper.queryWeeklyById(todoReqDTO.getReportId());

                //节点流转
                for(String u:nextUserList){
                    String title = DateUtil.formatDate(weeklyList.get(0).getStartDate())+"-"+DateUtil.formatDate(weeklyList.get(0).getEndDate()) + "运营周报-请审批";

                    //主报表添加待办
                    String parentId = addTodo(title,weeklyList.get(0).getParentId(),CommonConstants.TRAFFIC_WEEKLY_REPORT,nodeDetail.getNodeType(),CommonConstants.DATA_TYPE_WEEKLY,nodeDetail.getFlowId(),nodeDetail.getNodeId(),u,null,null);

                    //判断子报表状态 为1审批中则发送待办
                    for(WeeklyReportResDTO r : weeklyList){
                        addTodo(title, r.getId(), CommonConstants.TRAFFIC_WEEKLY_REPORT, nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_WEEKLY, nodeDetail.getFlowId(), nodeDetail.getNodeId(),u,CommonConstants.ONE_STRING,parentId);
                    }
                }
            }
        }else{
            //子待办
            String modifyParenFlag = CommonConstants.ONE_STRING;
            String parenStatus = CommonConstants.ONE_STRING;
            List<TodoResDTO> subTodoList = workbenchMapper.queryTodoByParent(todoResDTO.getParentId());
            for(TodoResDTO t:subTodoList){

                //存在未完成的待办
                if(CommonConstants.ZERO_STRING.equals(t.getStatus())){
                    modifyParenFlag = CommonConstants.ZERO_STRING;
                }
                //存在驳回的情况,父待办则为驳回
                if(CommonConstants.TWO_STRING.equals(t.getApprovalStatus())){
                    parenStatus = CommonConstants.TWO_STRING;
                }
            }

            //将主待办更新为已办
            if(CommonConstants.ONE_STRING.equals(modifyParenFlag)){
                TodoReqDTO parentTodoReq = new TodoReqDTO();
                parentTodoReq.setId(todoResDTO.getParentId());
                parentTodoReq.setApprovalStatus(parenStatus);
                workbenchMapper.todoApproval(parentTodoReq);
            }

            //更新子报表状态
            WeeklyReportReqDTO modifyReq = new WeeklyReportReqDTO();
            modifyReq.setId(todoReqDTO.getReportId());
            modifyReq.setUpdateBy(TokenUtils.getCurrentPersonId());

            //通过
            if(CommonConstants.ONE_STRING.equals(todoReqDTO.getApprovalStatus())){
                modifyReq.setStatus(CommonConstants.THREE_STRING);
            }else{
                modifyReq.setStatus(CommonConstants.ZERO_STRING);
            }
            trafficReportMapper.modifyWeekly(modifyReq);

            //获取各子报表
            String goNextFlag = CommonConstants.ONE_STRING;
            List<WeeklyReportResDTO> weeklyList = trafficReportMapper.queryWeeklyById(todoReqDTO.getReportId());

            for(WeeklyReportResDTO d:weeklyList){

                //存在草稿、一级审核状态下，不流转
                if(CommonConstants.ONE_STRING.equals(d.getStatus())
                        || CommonConstants.ZERO_STRING.equals(d.getStatus())){
                    goNextFlag = CommonConstants.ZERO_STRING;
                }
            }

            // 子报表都审核完毕,流转下一节点
            if(goNextFlag.equals(CommonConstants.ONE_STRING)){
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                String title = "运营周报-请审批";

                //下一节点不是待办,则更新报表为审批完成
                if(!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)){
                    title = "运营周报-请查阅";
                    modifyReq.setStatus(CommonConstants.TWO_STRING);
                    trafficReportMapper.weeklyApprovalComplete(modifyReq);
                }
                //节点流转
                for(String u:nextUserList){
                    addTodo(DateUtil.formatDate(weeklyList.get(0).getStartDate())+"-"+DateUtil.formatDate(weeklyList.get(0).getEndDate()) + title,
                            weeklyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_WEEKLY_REPORT,
                            nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_WEEKLY,
                            nodeDetail.getFlowId(),
                            nodeDetail.getNodeId(),u,"0",null);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    private void trafficMonthlyApproval(TodoReqDTO todoReqDTO,TodoResDTO todoResDTO){
        // 若属于第一个节点，需查询这三个节点下是否还存在未审批的待办，没有则可以流转到下一节点
        List<String> nodes = Arrays.asList(CommonConstants.TRAFFIC_MONTHLY_NODE1_SUB);
        if(nodes.contains(todoResDTO.getCurrentNode())){

            List<TodoResDTO> todoList = workbenchMapper.queryTodoByNode(todoReqDTO.getProcessKey(),todoReqDTO.getReportId(),nodes);
            if(todoList==null){
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                //获取各子报表
                List<MonthlyReportResDTO> monthlyList = trafficReportMapper.queryMonthlyById(todoReqDTO.getReportId());

                //节点流转
                for(String u:nextUserList){
                    String title = DateUtil.formatDate(monthlyList.get(0).getStartDate())+"-"+DateUtil.formatDate(monthlyList.get(0).getEndDate()) + "运营月报-请审批";

                    //主报表添加待办
                    String parentId = addTodo(title,monthlyList.get(0).getParentId(),CommonConstants.TRAFFIC_MONTHLY_REPORT,nodeDetail.getNodeType(),CommonConstants.DATA_TYPE_MONTHLY,nodeDetail.getFlowId(),nodeDetail.getNodeId(),u,null,null);

                    //判断子报表状态 为1审批中则发送待办
                    for(MonthlyReportResDTO r : monthlyList){
                        addTodo(title, r.getId(), CommonConstants.TRAFFIC_MONTHLY_REPORT, nodeDetail.getNodeType(), CommonConstants.DATA_TYPE_MONTHLY, nodeDetail.getFlowId(), nodeDetail.getNodeId(),u,CommonConstants.ONE_STRING,parentId);
                    }
                }
            }
        }else{
            //子待办
            String modifyParenFlag = CommonConstants.ONE_STRING;
            String parenStatus = CommonConstants.ONE_STRING;
            List<TodoResDTO> subTodoList = workbenchMapper.queryTodoByParent(todoResDTO.getParentId());
            for(TodoResDTO t:subTodoList){

                //存在未完成的待办
                if(CommonConstants.ZERO_STRING.equals(t.getStatus())){
                    modifyParenFlag = CommonConstants.ZERO_STRING;
                }
                //存在驳回的情况,父待办则为驳回
                if(CommonConstants.TWO_STRING.equals(t.getApprovalStatus())){
                    parenStatus = CommonConstants.TWO_STRING;
                }
            }

            //将主待办更新为已办
            if(CommonConstants.ONE_STRING.equals(modifyParenFlag)){
                TodoReqDTO parentTodoReq = new TodoReqDTO();
                parentTodoReq.setId(todoResDTO.getParentId());
                parentTodoReq.setApprovalStatus(parenStatus);
                workbenchMapper.todoApproval(parentTodoReq);
            }

            //更新子报表状态
            MonthlyReportReqDTO modifyReq = new MonthlyReportReqDTO();
            modifyReq.setId(todoReqDTO.getReportId());
            modifyReq.setUpdateBy(TokenUtils.getCurrentPersonId());

            //通过
            if(CommonConstants.ONE_STRING.equals(todoReqDTO.getApprovalStatus())){
                modifyReq.setStatus(CommonConstants.THREE_STRING);
            }else{
                modifyReq.setStatus(CommonConstants.ZERO_STRING);
            }
            trafficReportMapper.modifyMonthly(modifyReq);

            //获取各子报表
            String goNextFlag = CommonConstants.ONE_STRING;
            List<MonthlyReportResDTO> monthlyList = trafficReportMapper.queryMonthlyById(todoReqDTO.getReportId());

            for(MonthlyReportResDTO d:monthlyList){

                //存在草稿、一级审核状态下，不流转
                if(CommonConstants.ONE_STRING.equals(d.getStatus())
                        || CommonConstants.ZERO_STRING.equals(d.getStatus())){
                    goNextFlag = CommonConstants.ZERO_STRING;
                }
            }

            // 子报表都审核完毕,流转下一节点
            if(goNextFlag.equals(CommonConstants.ONE_STRING)){
                String nextNode = workbenchMapper.queryNextNode(todoResDTO.getCurrentNode());
                FlowNodeResDTO nodeDetail = workbenchMapper.nodeDetail(nextNode);
                List<String> nextUserList = getUserByNode(nextNode);

                String title = "运营月报-请审批";

                //下一节点不是待办,则更新报表为审批完成
                if(!nodeDetail.getNodeType().equals(CommonConstants.ONE_STRING)){
                    title = "运营月报-请查阅";
                    modifyReq.setStatus(CommonConstants.TWO_STRING);
                    trafficReportMapper.monthlyApprovalComplete(modifyReq);
                }
                //节点流转
                for(String u:nextUserList){
                    addTodo(DateUtil.formatDate(monthlyList.get(0).getStartDate())+"-"+DateUtil.formatDate(monthlyList.get(0).getEndDate()) + title,
                            monthlyList.get(0).getParentId(),
                            CommonConstants.TRAFFIC_MONTHLY_REPORT,
                            nodeDetail.getNodeType(),
                            CommonConstants.DATA_TYPE_MONTHLY,
                            nodeDetail.getFlowId(),
                            nodeDetail.getNodeId(),u,"0",null);
                }
            }
        }
    }

    /**
     * 运营日报/周报/月报表审批
     * @param todoReqDTO 任务督办审批请求对象
     * @param todoResDTO 任务督办结果对象
     */
    @Transactional(rollbackFor = Exception.class)
    private void operateReportApproval(TodoReqDTO todoReqDTO,TodoResDTO todoResDTO){
        // 标题
        String titlePrefix = "";
        switch (todoResDTO.getDataType()) {
            case CommonConstants.DATA_TYPE_DAILY:
                titlePrefix = "运营日报";
                break;
            case CommonConstants.DATA_TYPE_WEEKLY:
                titlePrefix = "运营周报";
                break;
            case CommonConstants.DATA_TYPE_MONTHLY:
                titlePrefix = "运营月报";
                break;
            default:
                break;
        }
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
                        todoResDTO.getDataType(), nodeDetail.getFlowId(), nodeDetail.getNodeId(), user, "0", null);
            }
        }
    }

    /**
     * 修改运营报表状态
     * @param id 报表id
     * @param status 状态
     * @param type 报表类型1日报,2周报,3月报
     */
    private void updateOperate(String id, String status, String type) {
        ReportUpdateReqDTO modifyReq = new ReportUpdateReqDTO();
        modifyReq.setId(id);
        modifyReq.setStatus(status);
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
     * @param title 代办标题
     * @param reportId 报表id
     * @param reportTable 报表表明
     * @param todoType 事项类型:1待办,2待阅
     * @param dataType 所属数据类型:1日报,2周报,3月报
     * @param processKey 流程名
     * @param currentNode 当前审批节点名
     * @param userId 用户id
     * @param isHide 是否隐藏
     * @param parentId 父ID
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
