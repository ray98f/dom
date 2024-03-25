package com.wzmtr.dom.service.traffic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzmtr.dom.dto.req.traffic.ImportantWorkReqDTO;
import com.wzmtr.dom.dto.res.traffic.ImportantWorkResDTO;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/22 13:47
 */
public interface ImportantWorkService {

    /**
     * 重要工作落实情况-列表
     * @param dataType 查询参数
     * @param startDate 查询参数
     * @param endDate 查询参数
     * @param pageReqDTO 分页参数
     * @return 重要工作落实情况列表
     */
    Page<ImportantWorkResDTO> list(String dataType, String startDate, String endDate, PageReqDTO pageReqDTO);

    /**
     * 重要工作落实情况-详情
     * @param recordId 入参数
     * @return 重要工作落实情况-详情
     */
    ImportantWorkResDTO detail(String recordId);

    /**
     * 重要工作落实情况-新增
     * @param currentLoginUser 入参数
     * @param importantWorkReqDTO 入参数
     */
    void add(CurrentLoginUser currentLoginUser, ImportantWorkReqDTO importantWorkReqDTO);

    /**
     * 重要工作落实情况-编辑
     * @param currentLoginUser 入参数
     * @param importantWorkReqDTO 入参数
     */
    void modify(CurrentLoginUser currentLoginUser, ImportantWorkReqDTO importantWorkReqDTO);
}
