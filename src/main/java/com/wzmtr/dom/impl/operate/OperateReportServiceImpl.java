package com.wzmtr.dom.impl.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.operate.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.operate.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.system.ReportUpdateReqDTO;
import com.wzmtr.dom.dto.res.operate.DailyReportResDTO;
import com.wzmtr.dom.dto.res.operate.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.operate.WeeklyReportResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.BpmnFlowEnum;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.operate.OperateReportMapper;
import com.wzmtr.dom.service.operate.OperateReportService;
import com.wzmtr.dom.service.system.WorkbenchService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运营-运营报表
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@Service
public class OperateReportServiceImpl implements OperateReportService {


    @Autowired
    private WorkbenchService workbenchService;

    @Autowired
    private OperateReportMapper operateReportMapper;

    @Override
    public Page<DailyReportResDTO> pageDaily(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateReportMapper.pageDaily(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public DailyReportResDTO detailDaily(String id) {
        return operateReportMapper.detailDaily(id);
    }

    @Override
    public void addDaily(DailyReportReqDTO dailyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = operateReportMapper.selectDailyIsExist(dailyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期运营日报数据已存在，无法重复新增");
        }
        dailyReportReqDTO.setId(TokenUtils.getUuId());
        dailyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        operateReportMapper.addDaily(dailyReportReqDTO);
    }

    @Override
    public void modifyDaily(DailyReportReqDTO dailyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = operateReportMapper.selectDailyIsExist(dailyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        dailyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        operateReportMapper.modifyDaily(dailyReportReqDTO);
    }

    @Override
    public void deleteDaily(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            operateReportMapper.deleteDaily(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public Page<WeeklyReportResDTO> pageWeekly(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateReportMapper.pageWeekly(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public WeeklyReportResDTO detailWeekly(String id) {
        return operateReportMapper.detailWeekly(id);
    }

    @Override
    public void addWeekly(WeeklyReportReqDTO weeklyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = operateReportMapper.selectWeeklyIsExist(weeklyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期运营周报数据已存在，无法重复新增");
        }
        weeklyReportReqDTO.setId(TokenUtils.getUuId());
        weeklyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        operateReportMapper.addWeekly(weeklyReportReqDTO);
    }

    @Override
    public void modifyWeekly(WeeklyReportReqDTO weeklyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = operateReportMapper.selectWeeklyIsExist(weeklyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        weeklyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        operateReportMapper.modifyWeekly(weeklyReportReqDTO);
    }

    @Override
    public void deleteWeekly(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            operateReportMapper.deleteWeekly(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public Page<MonthlyReportResDTO> pageMonthly(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return operateReportMapper.pageMonthly(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public MonthlyReportResDTO detailMonthly(String id) {
        return operateReportMapper.detailMonthly(id);
    }

    @Override
    public void addMonthly(MonthlyReportReqDTO monthlyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = operateReportMapper.selectMonthlyIsExist(monthlyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期运营月报数据已存在，无法重复新增");
        }
        monthlyReportReqDTO.setId(TokenUtils.getUuId());
        monthlyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        operateReportMapper.addMonthly(monthlyReportReqDTO);
    }

    @Override
    public void modifyMonthly(MonthlyReportReqDTO monthlyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = operateReportMapper.selectMonthlyIsExist(monthlyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        monthlyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        operateReportMapper.modifyMonthly(monthlyReportReqDTO);
    }

    @Override
    public void deleteMonthly(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            operateReportMapper.deleteMonthly(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void commitDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO) {
        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle("运营日报-请审批");
        approvalReqDTO.setReportId(dailyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.VEHICLE_DAILY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_DAILY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.operate_daily.value());
        approvalReqDTO.setCurrentNode(CommonConstants.VEHICLE_DAILY_NODE1);
        // 提交流程
        workbenchService.commitApproval(approvalReqDTO);
        // 修改报表状态为审核中
        underReviewReport(dailyReportReqDTO.getId(), "1");
    }

    @Override
    public void commitWeekly(CurrentLoginUser currentLoginUser, WeeklyReportReqDTO weeklyReportReqDTO) {
        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle("运营周报-请审批");
        approvalReqDTO.setReportId(weeklyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.VEHICLE_WEEKLY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_WEEKLY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.operate_weekly.value());
        approvalReqDTO.setCurrentNode(CommonConstants.VEHICLE_WEEKLY_NODE1);
        // 提交流程
        workbenchService.commitApproval(approvalReqDTO);
        // 修改报表状态为审核中
        underReviewReport(weeklyReportReqDTO.getId(), "2");
    }

    @Override
    public void commitMonthly(CurrentLoginUser currentLoginUser, MonthlyReportReqDTO monthlyReportReqDTO) {
        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle("运营月报-请审批");
        approvalReqDTO.setReportId(monthlyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.VEHICLE_MONTHLY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_MONTHLY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.operate_monthly.value());
        approvalReqDTO.setCurrentNode(CommonConstants.VEHICLE_MONTHLY_NODE1);
        // 提交流程
        workbenchService.commitApproval(approvalReqDTO);
        // 修改报表状态为审核中
        underReviewReport(monthlyReportReqDTO.getId(), "3");
    }

    /**
     * 修改报表状态为审核中
     * @param id 报表id
     * @param type 类型 1日报2周报3月报
     */
    private void underReviewReport(String id, String type) {
        ReportUpdateReqDTO reqDTO = new ReportUpdateReqDTO();
        reqDTO.setId(id);
        reqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        reqDTO.setStatus(CommonConstants.ONE_STRING);
        if (CommonConstants.ONE_STRING.equals(type)) {
            operateReportMapper.modifyDailyByFlow(reqDTO);
        } else if (CommonConstants.TWO_STRING.equals(type)) {
            operateReportMapper.modifyWeeklyByFlow(reqDTO);
        } else if (CommonConstants.THREE_STRING.equals(type)) {
            operateReportMapper.modifyMonthlyByFlow(reqDTO);
        }
    }

}
