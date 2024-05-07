package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.system.ReportUpdateReqDTO;
import com.wzmtr.dom.dto.req.vehicle.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.vehicle.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.vehicle.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DailyReportResDTO;
import com.wzmtr.dom.dto.res.vehicle.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.vehicle.WeeklyReportResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.BpmnFlowEnum;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.VehicleReportMapper;
import com.wzmtr.dom.service.system.WorkbenchService;
import com.wzmtr.dom.service.vehicle.VehicleReportService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆部-车辆部报表
 * @author  Ray
 * @version 1.0
 * @date 2024/03/14
 */
@Service
public class VehicleReportServiceImpl implements VehicleReportService {


    @Autowired
    private WorkbenchService workbenchService;

    @Autowired
    private VehicleReportMapper vehicleReportMapper;

    @Override
    public Page<DailyReportResDTO> pageDaily(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return vehicleReportMapper.pageDaily(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public DailyReportResDTO detailDaily(String id) {
        return vehicleReportMapper.detailDaily(id);
    }

    @Override
    public DailyReportResDTO ocmUserDaily(String date) {
        // todo 根据日期获取当天 乘务系统 人员
        return new DailyReportResDTO();
    }

    @Override
    public void addDaily(DailyReportReqDTO dailyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = vehicleReportMapper.selectDailyIsExist(dailyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车辆部日报数据已存在，无法重复新增");
        }
        dailyReportReqDTO.setId(TokenUtils.getUuId());
        dailyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        vehicleReportMapper.addDaily(dailyReportReqDTO);
    }

    @Override
    public void modifyDaily(DailyReportReqDTO dailyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = vehicleReportMapper.selectDailyIsExist(dailyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        dailyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        vehicleReportMapper.modifyDaily(dailyReportReqDTO);
    }

    @Override
    public void deleteDaily(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            vehicleReportMapper.deleteDaily(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public Page<WeeklyReportResDTO> pageWeekly(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return vehicleReportMapper.pageWeekly(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public WeeklyReportResDTO detailWeekly(String id) {
        return vehicleReportMapper.detailWeekly(id);
    }

    @Override
    public void addWeekly(WeeklyReportReqDTO weeklyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = vehicleReportMapper.selectWeeklyIsExist(weeklyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车辆部周报数据已存在，无法重复新增");
        }
        weeklyReportReqDTO.setId(TokenUtils.getUuId());
        weeklyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        vehicleReportMapper.addWeekly(weeklyReportReqDTO);
    }

    @Override
    public void modifyWeekly(WeeklyReportReqDTO weeklyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = vehicleReportMapper.selectWeeklyIsExist(weeklyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        weeklyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        vehicleReportMapper.modifyWeekly(weeklyReportReqDTO);
    }

    @Override
    public void deleteWeekly(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            vehicleReportMapper.deleteWeekly(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public Page<MonthlyReportResDTO> pageMonthly(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return vehicleReportMapper.pageMonthly(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public MonthlyReportResDTO detailMonthly(String id) {
        return vehicleReportMapper.detailMonthly(id);
    }

    @Override
    public void addMonthly(MonthlyReportReqDTO monthlyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = vehicleReportMapper.selectMonthlyIsExist(monthlyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车辆部月报数据已存在，无法重复新增");
        }
        monthlyReportReqDTO.setId(TokenUtils.getUuId());
        monthlyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        vehicleReportMapper.addMonthly(monthlyReportReqDTO);
    }

    @Override
    public void modifyMonthly(MonthlyReportReqDTO monthlyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = vehicleReportMapper.selectMonthlyIsExist(monthlyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        monthlyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        vehicleReportMapper.modifyMonthly(monthlyReportReqDTO);
    }

    @Override
    public void deleteMonthly(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            vehicleReportMapper.deleteMonthly(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public void commitDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO) {
        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle("车辆部日报-请审批");
        approvalReqDTO.setReportId(dailyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.VEHICLE_DAILY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_DAILY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.vehicle_daily.value());
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
        approvalReqDTO.setTitle("车辆部周报-请审批");
        approvalReqDTO.setReportId(weeklyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.VEHICLE_WEEKLY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_WEEKLY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.vehicle_weekly.value());
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
        approvalReqDTO.setTitle("车辆部月报-请审批");
        approvalReqDTO.setReportId(monthlyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.VEHICLE_MONTHLY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_MONTHLY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.vehicle_monthly.value());
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
            vehicleReportMapper.modifyDailyByFlow(reqDTO);
        } else if (CommonConstants.TWO_STRING.equals(type)) {
            vehicleReportMapper.modifyWeeklyByFlow(reqDTO);
        } else if (CommonConstants.THREE_STRING.equals(type)) {
            vehicleReportMapper.modifyMonthlyByFlow(reqDTO);
        }
    }

}
