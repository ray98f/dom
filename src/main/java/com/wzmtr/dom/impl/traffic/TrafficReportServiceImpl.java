package com.wzmtr.dom.impl.traffic;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.system.ReportUpdateReqDTO;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.MonthlyReportReqDTO;
import com.wzmtr.dom.dto.req.traffic.WeeklyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.MonthlyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.WeeklyReportResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.enums.BpmnFlowEnum;
import com.wzmtr.dom.enums.ErrorCode;
import com.wzmtr.dom.enums.TrafficReportRole;
import com.wzmtr.dom.exception.CommonException;
import com.wzmtr.dom.mapper.traffic.TrafficReportMapper;
import com.wzmtr.dom.service.system.WorkbenchService;
import com.wzmtr.dom.service.traffic.TrafficReportService;
import com.wzmtr.dom.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 客运部-客运部报表
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/21 15:14
 */
@Service
public class TrafficReportServiceImpl implements TrafficReportService {

    @Autowired
    private TrafficReportMapper trafficReportMapper;

    @Autowired
    private WorkbenchService workbenchService;

    @Override
    public Page<DailyReportResDTO> dailyList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trafficReportMapper.dailyList(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public DailyReportResDTO dailyDetail(String id,String dataType, String startDate, String endDate) {

        //主报表信息
        DailyReportResDTO detail = trafficReportMapper.dailyDetail(id,dataType,startDate,endDate);

        //先假设主报表默认可提交审核,根据报表状态更新
        detail.setSubmitFlag(CommonConstants.ONE_STRING);

        //主报表若存在审核中 或者已审核
        if (CommonConstants.ZERO_STRING.equals(detail.getStatus())) {
            detail.setSubmitFlag(CommonConstants.ZERO_STRING);
        }

        //子报表信息
        List<DailyReportResDTO> subDailyReportNew = new ArrayList<>();
        List<DailyReportResDTO> subDailyReport = trafficReportMapper.queryDailyByParent(id);
        for (DailyReportResDTO item : subDailyReport) {
            switch (item.getReportType()) {
                case CommonConstants.ONE_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_1.roles());
                    break;
                case CommonConstants.TWO_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_2.roles());
                    break;
                case CommonConstants.THREE_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_3.roles());
                    break;
                default:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_0.roles());
                    break;
            }
            //子报表为草稿状态 可提交审批
            item.setSubmitFlag(CommonConstants.ZERO_STRING);
            if (CommonConstants.ZERO_STRING.equals(item.getStatus())) {
                item.setSubmitFlag(CommonConstants.ONE_STRING);
            }
            subDailyReportNew.add(item);

            //子报表有草稿或审核中状态,主报表不可提交审批
            if ((CommonConstants.ZERO_STRING.equals(item.getStatus()) || CommonConstants.ONE_STRING.equals(item.getStatus()))) {
                detail.setSubmitFlag(CommonConstants.ZERO_STRING);
            }
        }
        detail.setSubDailyReport(subDailyReportNew);

        return detail;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO) {
        int existFlag = trafficReportMapper.checkDailyExist(dailyReportReqDTO);
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        try {
            //日期校验
            if (!dailyReportReqDTO.getStartDate().equals(dailyReportReqDTO.getEndDate())) {
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            dailyReportReqDTO.setDailyDate(dailyReportReqDTO.getStartDate());

            //先增加当日父报表
            String reportId = TokenUtils.getUuId();
            dailyReportReqDTO.setCreateBy(currentLoginUser.getPersonId());
            dailyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            dailyReportReqDTO.setId(reportId);
            trafficReportMapper.addDaily(dailyReportReqDTO);

            //增加客流、热线、安全生产 三个子报表 TODO
            String[] typeArray = CommonConstants.TRAFFIC_REPORT_TYPE;
            for (String s : typeArray) {
                dailyReportReqDTO.setId("sub" + s + "-" + TokenUtils.getUuId());
                dailyReportReqDTO.setParentId(reportId);
                dailyReportReqDTO.setReportType(s);
                trafficReportMapper.addDaily(dailyReportReqDTO);
            }

        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO) {
        dailyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = trafficReportMapper.modifyDaily(dailyReportReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
        //更新子表
        if(CommonConstants.ZERO_STRING.equals(dailyReportReqDTO.getReportType())){

            List<DailyReportResDTO> subList = trafficReportMapper.queryDailyByParent(dailyReportReqDTO.getId());

            for(DailyReportResDTO r : subList){
                DailyReportReqDTO req = new DailyReportReqDTO();
                req.setId(r.getId());
                req.setUpdateBy(currentLoginUser.getPersonId());
                switch (r.getReportType()){
                    case CommonConstants.ONE_STRING:
                        req.setIfPassenger(dailyReportReqDTO.getIfPassenger());
                        req.setIfIncome(dailyReportReqDTO.getIfIncome());
                        req.setIfTicketUse(dailyReportReqDTO.getIfTicketUse());
                        req.setIfOnewaySale(dailyReportReqDTO.getIfOnewaySale());
                        break;
                    case CommonConstants.TWO_STRING:
                        req.setIfHotlineSummary(dailyReportReqDTO.getIfHotlineSummary());
                        req.setIfHotlineImportant(dailyReportReqDTO.getIfHotlineImportant());
                        req.setIfTransmitInfo(dailyReportReqDTO.getIfTransmitInfo());
                        break;
                    case CommonConstants.THREE_STRING:
                        req.setIfProductionSummary(dailyReportReqDTO.getIfProductionSummary());
                        req.setIfProductionInfo(dailyReportReqDTO.getIfProductionInfo());
                        break;
                    default:
                        break;
                }
                trafficReportMapper.modifyDaily(req);
            }


        //更新主表
        }else{
            trafficReportMapper.modifyMainDaily(dailyReportReqDTO);
        }
    }

    @Override
    public void commitDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO) {

        DailyReportResDTO detail = trafficReportMapper.dailyDetail(dailyReportReqDTO.getId(),null,null,null);
        String title = "【"+ DateUtil.formatDate(detail.getDailyDate())+"】客运部部日报-请审批";

        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle(title);
        approvalReqDTO.setReportId(dailyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.TRAFFIC_DAILY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_DAILY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_daily.value());
        switch (dailyReportReqDTO.getReportType()) {
            case CommonConstants.ONE_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_DAILY_NODE1_SUB1);
                break;
            case CommonConstants.TWO_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_DAILY_NODE1_SUB2);
                break;
            case CommonConstants.THREE_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_DAILY_NODE1_SUB3);
                break;
            default:
                break;
        }
        // 提交流程
        workbenchService.commitApproval(approvalReqDTO);
        // 修改报表状态为审核中
        underReviewReport(dailyReportReqDTO.getId(), "1");
    }

    @Override
    public Page<WeeklyReportResDTO> weeklyList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trafficReportMapper.weeklyList(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public WeeklyReportResDTO detailWeekly(String id) {
        //主报表信息
        WeeklyReportResDTO detail = trafficReportMapper.weeklyDetail(id);

        //先假设主报表默认可提交审核,根据报表状态更新
        detail.setSubmitFlag(CommonConstants.ONE_STRING);

        //主报表若存在审核中 或者已审核
        if (CommonConstants.ZERO_STRING.equals(detail.getStatus())) {
            detail.setSubmitFlag(CommonConstants.ZERO_STRING);
        }

        //子报表信息
        List<WeeklyReportResDTO> subWeeklyReportNew = new ArrayList<>();
        List<WeeklyReportResDTO> subWeeklyReport = trafficReportMapper.queryWeeklyByParent(id);
        for (WeeklyReportResDTO item : subWeeklyReport) {
            switch (item.getReportType()) {
                case CommonConstants.ONE_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_1.roles());
                    break;
                case CommonConstants.TWO_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_2.roles());
                    break;
                case CommonConstants.THREE_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_3.roles());
                    break;
                default:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_0.roles());
                    break;
            }
            //子报表为草稿状态 可提交审批
            item.setSubmitFlag(CommonConstants.ZERO_STRING);
            if (CommonConstants.ZERO_STRING.equals(item.getStatus())) {
                item.setSubmitFlag(CommonConstants.ONE_STRING);
            }
            subWeeklyReportNew.add(item);

            //子报表有草稿或审核中状态,主报表不可提交审批
            if ((CommonConstants.ZERO_STRING.equals(item.getStatus()) || CommonConstants.ONE_STRING.equals(item.getStatus()))) {
                detail.setSubmitFlag(CommonConstants.ZERO_STRING);
            }
        }
        detail.setSubWeeklyReport(subWeeklyReportNew);

        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWeekly(CurrentLoginUser currentLoginUser, WeeklyReportReqDTO weeklyReportReqDTO) {
        int existFlag = trafficReportMapper.checkWeeklyExist(weeklyReportReqDTO);
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        try {

            //先增加父报表
            String reportId = TokenUtils.getUuId();
            weeklyReportReqDTO.setCreateBy(currentLoginUser.getPersonId());
            weeklyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            weeklyReportReqDTO.setId(reportId);
            trafficReportMapper.addWeekly(weeklyReportReqDTO);

            //增加客流、热线、安全生产 三个子报表 TODO
            String[] typeArray = CommonConstants.TRAFFIC_REPORT_TYPE;
            for (String s : typeArray) {
                weeklyReportReqDTO.setId("sub" + s + "-" + TokenUtils.getUuId());
                weeklyReportReqDTO.setParentId(reportId);
                weeklyReportReqDTO.setReportType(s);
                trafficReportMapper.addWeekly(weeklyReportReqDTO);
            }

        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modifyWeekly(CurrentLoginUser currentLoginUser, WeeklyReportReqDTO weeklyReportReqDTO) {
        weeklyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = trafficReportMapper.modifyWeekly(weeklyReportReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //更新子表
        if(CommonConstants.ZERO_STRING.equals(weeklyReportReqDTO.getReportType())){

            List<WeeklyReportResDTO> subList = trafficReportMapper.queryWeeklyByParent(weeklyReportReqDTO.getId());

            for(WeeklyReportResDTO r : subList){
                WeeklyReportReqDTO req = new WeeklyReportReqDTO();
                req.setId(r.getId());
                req.setUpdateBy(currentLoginUser.getPersonId());
                switch (r.getReportType()){
                    case CommonConstants.ONE_STRING:
                        req.setIfPassenger(weeklyReportReqDTO.getIfPassenger());
                        req.setIfIncome(weeklyReportReqDTO.getIfIncome());
                        req.setIfTicketUse(weeklyReportReqDTO.getIfTicketUse());
                        req.setIfOnewaySale(weeklyReportReqDTO.getIfOnewaySale());
                        break;
                    case CommonConstants.TWO_STRING:
                        req.setIfHotlineSummary(weeklyReportReqDTO.getIfHotlineSummary());
                        req.setIfHotlineImportant(weeklyReportReqDTO.getIfHotlineImportant());
                        req.setIfTransmitInfo(weeklyReportReqDTO.getIfTransmitInfo());
                        break;
                    case CommonConstants.THREE_STRING:
                        req.setIfProductionSummary(weeklyReportReqDTO.getIfProductionSummary());
                        req.setIfProductionInfo(weeklyReportReqDTO.getIfProductionInfo());
                        break;
                    default:
                        break;
                }
                trafficReportMapper.modifyWeekly(req);
            }


            //更新主表
        }else{
            trafficReportMapper.modifyMainWeekly(weeklyReportReqDTO);
        }

    }

    @Override
    public void commitWeekly(CurrentLoginUser currentLoginUser, WeeklyReportReqDTO weeklyReportReqDTO) {

        WeeklyReportResDTO detail = trafficReportMapper.weeklyDetail(weeklyReportReqDTO.getId());
        String title = "【"+DateUtil.formatDate(detail.getStartDate())+"~"+DateUtil.formatDate(detail.getEndDate())+"】客运部周报-请审批";


        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle(title);
        approvalReqDTO.setReportId(weeklyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.TRAFFIC_WEEKLY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_WEEKLY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_weekly.value());
        switch (weeklyReportReqDTO.getReportType()) {
            case CommonConstants.ONE_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_WEEKLY_NODE1_SUB1);
                break;
            case CommonConstants.TWO_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_WEEKLY_NODE1_SUB2);
                break;
            case CommonConstants.THREE_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_WEEKLY_NODE1_SUB3);
                break;
            default:
                break;
        }
        // 提交流程
        workbenchService.commitApproval(approvalReqDTO);
        // 修改报表状态为审核中
        underReviewReport(weeklyReportReqDTO.getId(), "2");
    }

    @Override
    public Page<MonthlyReportResDTO> monthlyList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return trafficReportMapper.monthlyList(pageReqDTO.of(), startDate, endDate);
    }

    @Override
    public MonthlyReportResDTO detailMonthly(String id) {
        //主报表信息
        MonthlyReportResDTO detail = trafficReportMapper.monthlyDetail(id);

        //先假设主报表默认可提交审核,根据报表状态更新
        detail.setSubmitFlag(CommonConstants.ONE_STRING);

        //主报表若存在审核中 或者已审核
        if (CommonConstants.ZERO_STRING.equals(detail.getStatus())) {
            detail.setSubmitFlag(CommonConstants.ZERO_STRING);
        }

        //子报表信息
        List<MonthlyReportResDTO> subWeeklyReportNew = new ArrayList<>();
        List<MonthlyReportResDTO> subWeeklyReport = trafficReportMapper.queryMonthlyByParent(id);
        for (MonthlyReportResDTO item : subWeeklyReport) {
            switch (item.getReportType()) {
                case CommonConstants.ONE_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_1.roles());
                    break;
                case CommonConstants.TWO_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_2.roles());
                    break;
                case CommonConstants.THREE_STRING:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_3.roles());
                    break;
                default:
                    item.setVisibleRoles(TrafficReportRole.REPORT_TYPE_0.roles());
                    break;
            }

            //子报表为草稿状态 可提交审批
            item.setSubmitFlag(CommonConstants.ZERO_STRING);
            if (CommonConstants.ZERO_STRING.equals(item.getStatus())) {
                item.setSubmitFlag(CommonConstants.ONE_STRING);
            }
            subWeeklyReportNew.add(item);

            //子报表有草稿或审核中状态,主报表不可提交审批
            if ((CommonConstants.ZERO_STRING.equals(item.getStatus()) || CommonConstants.ONE_STRING.equals(item.getStatus()))) {
                detail.setSubmitFlag(CommonConstants.ZERO_STRING);
            }
        }
        detail.setSubMonthlyReport(subWeeklyReportNew);

        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMonthly(CurrentLoginUser currentLoginUser, MonthlyReportReqDTO monthlyReportReqDTO) {
        int existFlag = trafficReportMapper.checkMonthlyExist(monthlyReportReqDTO);
        if (existFlag > 0) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        try {

            //先增加父报表
            String reportId = TokenUtils.getUuId();
            monthlyReportReqDTO.setCreateBy(currentLoginUser.getPersonId());
            monthlyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            monthlyReportReqDTO.setId(reportId);
            trafficReportMapper.addMonthly(monthlyReportReqDTO);

            //增加客流、热线、安全生产 三个子报表 TODO
            String[] typeArray = CommonConstants.TRAFFIC_REPORT_TYPE;
            for (String s : typeArray) {
                monthlyReportReqDTO.setId("sub" + s + "-" + TokenUtils.getUuId());
                monthlyReportReqDTO.setParentId(reportId);
                monthlyReportReqDTO.setReportType(s);
                trafficReportMapper.addMonthly(monthlyReportReqDTO);
            }

        } catch (Exception e) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modifyMonthly(CurrentLoginUser currentLoginUser, MonthlyReportReqDTO monthlyReportReqDTO) {
        monthlyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = trafficReportMapper.modifyMonthly(monthlyReportReqDTO);
        if (res <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }

        //更新子表
        if(CommonConstants.ZERO_STRING.equals(monthlyReportReqDTO.getReportType())){

            List<MonthlyReportResDTO> subList = trafficReportMapper.queryMonthlyByParent(monthlyReportReqDTO.getId());

            for(MonthlyReportResDTO r : subList){
                MonthlyReportReqDTO req = new MonthlyReportReqDTO();
                req.setId(r.getId());
                req.setUpdateBy(currentLoginUser.getPersonId());
                switch (r.getReportType()){
                    case CommonConstants.ONE_STRING:
                        req.setIfPassenger(monthlyReportReqDTO.getIfPassenger());
                        req.setIfIncome(monthlyReportReqDTO.getIfIncome());
                        req.setIfTicketUse(monthlyReportReqDTO.getIfTicketUse());
                        req.setIfOnewaySale(monthlyReportReqDTO.getIfOnewaySale());
                        break;
                    case CommonConstants.TWO_STRING:
                        req.setIfHotlineSummary(monthlyReportReqDTO.getIfHotlineSummary());
                        req.setIfHotlineImportant(monthlyReportReqDTO.getIfHotlineImportant());
                        req.setIfTransmitInfo(monthlyReportReqDTO.getIfTransmitInfo());
                        break;
                    case CommonConstants.THREE_STRING:
                        req.setIfProductionSummary(monthlyReportReqDTO.getIfProductionSummary());
                        req.setIfProductionInfo(monthlyReportReqDTO.getIfProductionInfo());
                        break;
                    default:
                        break;
                }
                trafficReportMapper.modifyMonthly(req);
            }

            //更新主表
        }else{
            trafficReportMapper.modifyMainMonthly(monthlyReportReqDTO);
        }

    }

    @Override
    public void commitMonthly(CurrentLoginUser currentLoginUser, MonthlyReportReqDTO monthlyReportReqDTO) {

        MonthlyReportResDTO detail = trafficReportMapper.monthlyDetail(monthlyReportReqDTO.getId());
        String title = "【"+DateUtil.formatDate(detail.getStartDate())+"~"+DateUtil.formatDate(detail.getEndDate())+"】客运部月报-请审批";

        // 报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setTitle(title);
        approvalReqDTO.setReportId(monthlyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.TRAFFIC_MONTHLY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_MONTHLY);
        approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_monthly.value());
        switch (monthlyReportReqDTO.getReportType()) {
            case CommonConstants.ONE_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_MONTHLY_NODE1_SUB1);
                break;
            case CommonConstants.TWO_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_MONTHLY_NODE1_SUB2);
                break;
            case CommonConstants.THREE_STRING:
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_MONTHLY_NODE1_SUB3);
                break;
            default:
                break;
        }
        // 提交流程
        workbenchService.commitApproval(approvalReqDTO);
        // 修改报表状态为审核中
        underReviewReport(monthlyReportReqDTO.getId(), "3");
    }

    /**
     * 修改报表状态为审核中
     * @param id   报表id
     * @param type 类型 1日报2周报3月报
     */
    private void underReviewReport(String id, String type) {
        ReportUpdateReqDTO reqDTO = new ReportUpdateReqDTO();
        reqDTO.setId(id);
        reqDTO.setUpdateBy(TokenUtils.getCurrentPersonId());
        reqDTO.setStatus(CommonConstants.ONE_STRING);
        if (CommonConstants.ONE_STRING.equals(type)) {
            trafficReportMapper.modifyDailyByFlow(reqDTO);
        } else if (CommonConstants.TWO_STRING.equals(type)) {
            trafficReportMapper.modifyWeeklyByFlow(reqDTO);
        } else if (CommonConstants.THREE_STRING.equals(type)) {
            trafficReportMapper.modifyMonthlyByFlow(reqDTO);
        }
    }
}
