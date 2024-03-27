package com.wzmtr.dom.dto.req.traffic.hotline;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:03
 */
@Data
public class HotLineImportantAddReqDTO {

    private List<HotLineImportantAddDataReqDTO> hotlineImportantList;
    /**
     * 数据所属日期
     */
    @ApiModelProperty("数据所属日期")
    private Date dataDate;
    /**
     * 数据类型 1日报 2周报 3月报
     */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;
    /**
     * 数据起始日期
     */
    @ApiModelProperty("数据起始日期")
    private Date startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    private Date endDate;
}
