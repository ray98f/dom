package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.res.operate.fault.FaultStatisticsResDTO;
import com.wzmtr.dom.dto.res.operate.fault.ReportFaultStatisticsResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.util.List;

/**
 * 运营日报-故障统计
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:55
 */
public interface OperateFaultStatisticsService {

    /**
     * 故障统计记录-列表
     * @param dataType   查询参数
     * @param startDate  查询参数
     * @param endDate    查询参数
     * @param pageReqDTO 分页参数
     * @return 故障统计记录列表
     */
    Page<FaultStatisticsResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 故障统计-报表详情
     * @param date 日期
     * @return 报表详情
     */
    ReportFaultStatisticsResDTO report(String date);

    /**
     * @param currentLoginUser 入参数
     */
    void add(CurrentLoginUser currentLoginUser, OperateFaultStatisticsReqDTO req);


    /**
     * @param currentLoginUser 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, OperateFaultStatisticsReqDTO req);

    /**
     * @param ids ids
     */
    void delete(List<String> ids);
}
