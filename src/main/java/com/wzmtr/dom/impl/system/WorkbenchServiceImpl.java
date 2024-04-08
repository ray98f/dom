package com.wzmtr.dom.impl.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.system.TodoReqDTO;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.system.WorkbenchMapper;
import com.wzmtr.dom.service.system.WorkbenchService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void todoApproval(TodoReqDTO todoReqDTO) {
        TodoResDTO res = workbenchMapper.todoDetail(todoReqDTO.getId());
        if (Objects.isNull(res)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        todoReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        workbenchMapper.todoApproval(todoReqDTO);
        // todo 根据流程配置进行下一步审核

    }

    @Override
    public void commitApproval(ApprovalReqDTO approvalReqDTO) {
        //TODO
        //根据节点获取审批人员
        String roleStr = workbenchMapper.queryRoleByNode(approvalReqDTO.getCurrentNode());
        if(StringUtils.isNotEmpty(roleStr)){
            List<String> roleList = Arrays.asList(roleStr.split(","));
            List<String> userList = workbenchMapper.queryUserByRole(roleList);

            //发送待办
            for(String u:userList){
                approvalReqDTO.setApprovalUser(u);

                workbenchMapper.addTodo(approvalReqDTO);
            }

        }



    }

}
