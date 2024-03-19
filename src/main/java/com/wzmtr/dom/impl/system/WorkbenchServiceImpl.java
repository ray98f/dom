package com.wzmtr.dom.impl.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.res.system.DailyStatisticsResDTO;
import com.wzmtr.dom.dto.res.system.TodoResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.mapper.system.WorkbenchMapper;
import com.wzmtr.dom.service.system.WorkbenchService;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Page<TodoResDTO> todoPage(PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return workbenchMapper.todoPage(pageReqDTO.of(), TokenUtils.getCurrentPersonId());
    }

    @Override
    public TodoResDTO todoDetail(String id) {
        return workbenchMapper.todoDetail(id);
    }

}
