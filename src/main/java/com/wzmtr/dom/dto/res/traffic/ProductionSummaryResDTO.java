package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionSummaryResDTO extends BaseEntity {


    /**
     * 车站编码
     * */
    @ApiModelProperty(value = "车站编码")
    private String stationCode;

    /**
     * 车站名称
     * */
    @ApiModelProperty(value = "车站名称")
    private String stationName;

    /**
     * 施工异常:前日/周/月件数
     * */
    @ApiModelProperty(value = "施工异常:前日/周/月件数")
    private Integer type1PreCount;

    /**
     * 施工异常:本日件数
     * */
    @ApiModelProperty(value = "施工异常:本日/周/月件数")
    private Integer type1Count;

    /**
     * 施工异常:概述
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private String type1Desc;

    /**
     * 施工异常:前日/周/月件数
     * */
    @ApiModelProperty(value = "施工异常:前日/周/月件数")
    private Integer type2PreCount;

    /**
     * 运检异常:本日件数
     * */
    @ApiModelProperty(value = "运检异常:本日/周/月件数")
    private Integer type2Count;

    /**
     * 运检异常:概述
     * */
    @ApiModelProperty(value = "运检异常:概述")
    private String type2Desc;

    /**
     * 行车异常:前日/周/月件数
     * */
    @ApiModelProperty(value = "行车异常:前日/周/月件数")
    private Integer type3PreCount;

    /**
     * 行车异常:本日件数
     * */
    @ApiModelProperty(value = "行车异常:本日/周/月件数")
    private Integer type3Count;

    /**
     * 行车异常:概述
     * */
    @ApiModelProperty(value = "行车异常:概述")
    private String type3Desc;

    /**
     * 其他突发事件:前日/周/月件数
     * */
    @ApiModelProperty(value = "其他突发事件:前日/周/月/周/月件数")
    private Integer type4PreCount;

    /**
     * 其他突发事件:本日件数
     * */
    @ApiModelProperty(value = "其他突发事件:本日/周/月件数")
    private Integer type4Count;

    /**
     * 其他突发事件:概述
     * */
    @ApiModelProperty(value = "其他突发事件:概述")
    private String type4Desc;

    /**
     * 其他事件:前日/周/月件数
     * */
    @ApiModelProperty(value = "其他事件:前日/周/月件数")
    private Integer type5PreCount;

    /**
     * 其他事件:本日件数
     * */
    @ApiModelProperty(value = "其他事件:本日/周/月件数")
    private Integer type5Count;

    /**
     * 其他事件:概述
     * */
    @ApiModelProperty(value = "其他事件:概述")
    private String type5Desc;

    /**
     * 其他事件:前日/周/月件数
     * */
    @ApiModelProperty(value = "其他事件:前日/周/月件数")
    private Integer type6PreCount;

    /**
     * 设备缺陷、故障:本日/周/月件数
     * */
    @ApiModelProperty(value = "设备缺陷、故障:本日/周/月件数")
    private Integer type6Count;

    /**
     * 设备缺陷、故障:概述
     * */
    @ApiModelProperty(value = "设备缺陷、故障:概述")
    private String type6Desc;

    /**
     * 说明
     * */
    @ApiModelProperty(value = "说明")
    private String remark;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 编辑标记
     * */
    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    /**
     * 数据类型:1:日报,2周报,3月报
     * */
    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:关键词")
    private String type1Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private String type2Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private String type3Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private String type4Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private String type5Keyword;
    /**
     * 设备缺陷、故障:  关键词
     * */
    @ApiModelProperty(value = "设备缺陷、故障:键词")
    private String type6Keyword;
}
