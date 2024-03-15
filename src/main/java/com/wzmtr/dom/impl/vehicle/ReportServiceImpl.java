package com.wzmtr.dom.impl.vehicle;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.dto.req.vehicle.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.vehicle.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.vehicle.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.vehicle.DailyReportResDTO;
import com.wzmtr.dom.dto.res.vehicle.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.vehicle.WeeklyReportResDTO;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.vehicle.ReportMapper;
import com.wzmtr.dom.service.vehicle.ReportService;
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
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Page<DailyReportResDTO> pageDaily(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return reportMapper.pageDaily(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public DailyReportResDTO detailDaily(String id) {
        return reportMapper.detailDaily(id);
    }

    @Override
    public DailyReportResDTO ocmUserDaily(String date) {
        // todo 根据日期获取当天 乘务系统 人员
        return new DailyReportResDTO();
    }

    @Override
    public void addDaily(DailyReportReqDTO dailyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = reportMapper.selectDailyIsExist(dailyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车辆部日报数据已存在，无法重复新增");
        }
        dailyReportReqDTO.setId(TokenUtils.getUuId());
        dailyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        reportMapper.addDaily(dailyReportReqDTO);
    }

    @Override
    public void modifyDaily(DailyReportReqDTO dailyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = reportMapper.selectDailyIsExist(dailyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        dailyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        reportMapper.modifyDaily(dailyReportReqDTO);
    }

    @Override
    public void deleteDaily(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            reportMapper.deleteDaily(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public Page<WeeklyReportResDTO> pageWeekly(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return reportMapper.pageWeekly(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public WeeklyReportResDTO detailWeekly(String id) {
        return reportMapper.detailWeekly(id);
    }

    @Override
    public void addWeekly(WeeklyReportReqDTO weeklyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = reportMapper.selectWeeklyIsExist(weeklyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车辆部周报数据已存在，无法重复新增");
        }
        weeklyReportReqDTO.setId(TokenUtils.getUuId());
        weeklyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        reportMapper.addWeekly(weeklyReportReqDTO);
    }

    @Override
    public void modifyWeekly(WeeklyReportReqDTO weeklyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = reportMapper.selectWeeklyIsExist(weeklyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        weeklyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        reportMapper.modifyWeekly(weeklyReportReqDTO);
    }

    @Override
    public void deleteWeekly(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            reportMapper.deleteWeekly(ids, TokenUtils.getCurrentPersonId());
        }
    }

    @Override
    public Page<MonthlyReportResDTO> pageMonthly(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return reportMapper.pageMonthly(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public MonthlyReportResDTO detailMonthly(String id) {
        return reportMapper.detailMonthly(id);
    }

    @Override
    public void addMonthly(MonthlyReportReqDTO monthlyReportReqDTO) {
        // 判断新增数据所属时间是否已存在数据
        Integer result = reportMapper.selectMonthlyIsExist(monthlyReportReqDTO);
        if (result > 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "所属日期车辆部月报数据已存在，无法重复新增");
        }
        monthlyReportReqDTO.setId(TokenUtils.getUuId());
        monthlyReportReqDTO.setCreateBy(TokenUtils.getCurrentPersonId());
        reportMapper.addMonthly(monthlyReportReqDTO);
    }

    @Override
    public void modifyMonthly(MonthlyReportReqDTO monthlyReportReqDTO) {
        // 判断修改数据版本号是否一致
        Integer result = reportMapper.selectMonthlyIsExist(monthlyReportReqDTO);
        if (result == 0) {
            throw new CommonException(ErrorCode.NORMAL_ERROR, "当前数据已被编辑，请刷新列表并重新编辑数据");
        }
        monthlyReportReqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        reportMapper.modifyMonthly(monthlyReportReqDTO);
    }

    @Override
    public void deleteMonthly(List<String> ids) {
        if (StringUtils.isNotEmpty(ids)) {
            reportMapper.deleteMonthly(ids, TokenUtils.getCurrentPersonId());
        }
    }

}
