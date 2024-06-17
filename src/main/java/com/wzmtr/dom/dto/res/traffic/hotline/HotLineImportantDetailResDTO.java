package com.wzmtr.dom.dto.res.traffic.hotline;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 16:58
 */
@Data
public class HotLineImportantDetailResDTO {

    @ApiModelProperty("ID")
    private String id;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Long count;
    /**
     * 主要内容
     */
    @ApiModelProperty("主要内容")
    private String content;
    /**
     * 处理结果
     */
    @ApiModelProperty("处理结果")
    private String result;
    /**
     * 关键词
     */
    @ApiModelProperty("关键词")
    private String keyword;
    /**
     * 热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他
     */
    @ApiModelProperty("热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他")
    private String hotlineType;
    /**
     * 数据所属日期
     */
    @ApiModelProperty("数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;

    @ApiModelProperty("隐藏标识")
    private String isHide;
}
