package com.wzmtr.dom.service.common;

import com.wzmtr.dom.dto.res.operate.PlanStatisticsResDTO;
import com.wzmtr.dom.dto.res.operate.UnsaturationConstructResDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 公共分类-第三方调用
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public interface ThirdService {

    /**
     * 获取不饱和施工作业
     * @return 施工作业列表
     */
    List<UnsaturationConstructResDTO> getUnsaturationConstruct(String startTime,String endTime);

    PlanStatisticsResDTO getPlanStatistics(String startTime,String endTime);

}
