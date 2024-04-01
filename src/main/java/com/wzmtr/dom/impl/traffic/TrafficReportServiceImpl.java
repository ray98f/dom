package com.wzmtr.dom.impl.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.page.PageMethod;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.dto.req.system.ApprovalReqDTO;
import com.wzmtr.dom.dto.req.traffic.DailyReportReqDTO;
import com.wzmtr.dom.dto.res.traffic.DailyReportResDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionDetailResDTO;
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
import java.util.Arrays;
import java.util.List;

/**
 * 客运部-客运部报表
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/21 15:14
 */
@Service
public class TrafficReportServiceImpl implements TrafficReportService {

    @Autowired
    private TrafficReportMapper reportMapper;

    @Autowired
    private WorkbenchService workbenchService;

    @Override
    public Page<DailyReportResDTO> dailyList(String startDate, String endDate, PageReqDTO pageReqDTO) {
        PageMethod.startPage(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
        return reportMapper.dailyList(pageReqDTO.of(),startDate,endDate);
    }

    @Override
    public DailyReportResDTO dailyDetail(String id) {

        //主报表信息
        DailyReportResDTO detail = reportMapper.dailyDetail(id);

        //先假设主报表默认可提交审核,根据报表状态更新
        detail.setSubmitFlag(CommonConstants.ONE_STRING);

        //主报表若存在审核中 或者已审核
        if(CommonConstants.ZERO_STRING.equals(detail.getStatus())){
            detail.setSubmitFlag(CommonConstants.ZERO_STRING);
        }

        //子报表信息
        List<DailyReportResDTO> subDailyReportNew = new ArrayList<>();
        List<DailyReportResDTO> subDailyReport = reportMapper.queryDailyByParent(id);
        for(DailyReportResDTO item : subDailyReport){
            switch (item.getReportType()){
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
            if(CommonConstants.ZERO_STRING.equals(item.getStatus())){
                item.setSubmitFlag(CommonConstants.ONE_STRING);
            }
            subDailyReportNew.add(item);

            //子报表有草稿或审核中状态,主报表不可提交审批
            if((CommonConstants.ZERO_STRING.equals(item.getStatus()) || CommonConstants.ONE_STRING.equals(item.getStatus()))){
                detail.setSubmitFlag(CommonConstants.ZERO_STRING);
            }
        }
        detail.setSubDailyReport(subDailyReport);

        return detail;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaily(CurrentLoginUser currentLoginUser,DailyReportReqDTO dailyReportReqDTO) {
        int existFlag = reportMapper.checkDailyExist(dailyReportReqDTO);
        if(existFlag > 0){
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        try{
            //日期校验
            if(!dailyReportReqDTO.getStartDate().equals(dailyReportReqDTO.getEndDate())){
                throw new CommonException(ErrorCode.DATE_ERROR);
            }
            dailyReportReqDTO.setDailyDate(dailyReportReqDTO.getStartDate());

            //先增加当日父报表
            String reportId = TokenUtils.getUuId();
            dailyReportReqDTO.setCreateBy(currentLoginUser.getPersonId());
            dailyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
            dailyReportReqDTO.setId(reportId);
            reportMapper.addDaily(dailyReportReqDTO);

            //增加客流、热线、安全生产 三个子报表 TODO
            String[] typeArray = CommonConstants.TRAFFIC_REPORT_TYPE;
            List<String> typeList = Arrays.asList(typeArray);
            for(String s: typeList){
                dailyReportReqDTO.setId("sub" + s + "-" + TokenUtils.getUuId());
                dailyReportReqDTO.setParentId(reportId);
                dailyReportReqDTO.setReportType(s);
                reportMapper.addDaily(dailyReportReqDTO);
            }

        }catch (Exception e){
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void modifyDaily(CurrentLoginUser currentLoginUser,DailyReportReqDTO dailyReportReqDTO) {
        dailyReportReqDTO.setUpdateBy(currentLoginUser.getPersonId());
        int res = reportMapper.modifyDaily(dailyReportReqDTO);
        if( res <= 0){
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void commitDaily(CurrentLoginUser currentLoginUser, DailyReportReqDTO dailyReportReqDTO) {

        //报表审核参数
        ApprovalReqDTO approvalReqDTO = new ApprovalReqDTO();
        approvalReqDTO.setId(TokenUtils.getUuId());
        approvalReqDTO.setApprovalResult(dailyReportReqDTO.getId());
        approvalReqDTO.setReportTable(CommonConstants.TRAFFIC_DAILY_REPORT);
        approvalReqDTO.setTodoType(CommonConstants.ONE_STRING);
        approvalReqDTO.setDataType(CommonConstants.DATA_TYPE_DAILY);
        switch (dailyReportReqDTO.getReportType()){
            case CommonConstants.ONE_STRING:
                approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_daily_sub1.value());
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_DAILY_SUB1_NODE1);
                break;
            case CommonConstants.TWO_STRING:
                approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_daily_sub2.value());
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_DAILY_SUB2_NODE1);
                break;
            case CommonConstants.THREE_STRING:
                approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_daily_sub3.value());
                approvalReqDTO.setCurrentNode(CommonConstants.TRAFFIC_DAILY_SUB3_NODE1);
                break;
            default:
                approvalReqDTO.setProcessKey(BpmnFlowEnum.traffic_daily.value());
                break;
        }

        //提交流程
        workbenchService.commitApproval(approvalReqDTO);
    }

    @Override
    public void deleteDaily(List<String> ids) {

    }
}
