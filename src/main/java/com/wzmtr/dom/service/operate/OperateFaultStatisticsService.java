package com.wzmtr.dom.service.operate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.operate.OperateFaultStatisticsReqDTO;
import com.wzmtr.dom.dto.res.operate.fault.FaultStatisticsResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

import java.text.ParseException;
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
     * @param dataType 数据类型
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @return 报表详情
     */
    FaultStatisticsResDTO report(String dataType, String startDate, String endDate) throws ParseException;

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
