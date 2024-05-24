package com.wzmtr.dom.dto.res.operate;

import com.wzmtr.dom.dto.res.traffic.hotline.HotLineImportantDetailResDTO;
import com.wzmtr.dom.dto.res.traffic.hotline.HotLineSummaryDetailResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 运营-热线服务情况结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Data
public class HotLineResDTO {

    /**
     * 服务热线汇总
     */
    @ApiModelProperty("服务热线汇总")
    private HotLineSummaryDetailResDTO hotLineSummary;
    /**
     * 热线重要内容列表
     */
    @ApiModelProperty("热线重要内容列表")
    private List<HotLineImportantDetailResDTO> hotLineImportant;
}
