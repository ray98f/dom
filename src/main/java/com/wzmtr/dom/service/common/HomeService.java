package com.wzmtr.dom.service.common;

import com.wzmtr.dom.dto.res.common.LastReportResDTO;

/**
 * 公共分类-首页
 * @author  zhangxin
 * @version 1.0
 * @date 2024/03/06
 */
public interface HomeService {

    /**
     * 获取最新报表
     * @return 获取最新报表
     */
    LastReportResDTO getLastReport();

}
