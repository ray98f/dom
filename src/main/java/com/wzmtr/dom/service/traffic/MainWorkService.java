package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.ImportantWorkReqDTO;
import com.wzmtr.dom.dto.req.traffic.MainWorkReqDTO;
import com.wzmtr.dom.dto.res.traffic.ImportantWorkResDTO;
import com.wzmtr.dom.dto.res.traffic.MainWorkResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 13:47
 */
public interface MainWorkService {

    /**
     * 主要工作情况-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 主要工作情况列表
     */
    Page<MainWorkResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 主要工作情况-详情
     * @param recordId 入参数
     * @return 主要工作情况-详情
     */
    MainWorkResDTO detail(String recordId, String startDate, String endDate);

    /**
     * 主要工作情况-新增
     * @param currentLoginUser 入参数
     * @param mainWorkReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, MainWorkReqDTO mainWorkReqDTO);

    /**
     * 主要工作情况-编辑
     * @param currentLoginUser 入参数
     * @param mainWorkReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, MainWorkReqDTO mainWorkReqDTO);
}
