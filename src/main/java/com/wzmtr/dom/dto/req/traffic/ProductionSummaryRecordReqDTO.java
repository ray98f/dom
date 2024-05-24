package com.wzmtr.dom.dto.req.traffic;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日数据请求类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionSummaryRecordReqDTO extends BaseEntity {


    /**
     * 车站编码
     * */
    @ApiModelProperty(value = "车站编码")
    private String stationCode;

    /**
     * 施工异常:概述
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private String type1Desc;

    /**
     * 运检异常:概述
     * */
    @ApiModelProperty(value = "运检异常:概述")
    private String type2Desc;

    /**
     * 行车异常:概述
     * */
    @ApiModelProperty(value = "行车异常:概述")
    private String type3Desc;

    /**
     * 其他突发事件:概述
     * */
    @ApiModelProperty(value = "其他突发事件:概述")
    private String type4Desc;

    /**
     * 其他事件:概述
     * */
    @ApiModelProperty(value = "其他事件:概述")
    private String type5Desc;

    /**
     * 设备缺陷、故障:概述
     * */
    @ApiModelProperty(value = "设备缺陷、故障:概述")
    private String type6Desc;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:关键词")
    private String type1Keyword;

    /**
     * 运检异常:概述
     * */
    @ApiModelProperty(value = "运检异常:概述")
    private String type2Keyword;

    /**
     * 行车异常:概述
     * */
    @ApiModelProperty(value = "行车异常:概述")
    private String type3Keyword;

    /**
     * 其他突发事件:概述
     * */
    @ApiModelProperty(value = "其他突发事件:概述")
    private String type4Keyword;

    /**
     * 其他事件:概述
     * */
    @ApiModelProperty(value = "其他事件:概述")
    private String type5Keyword;

    /**
     * 设备缺陷、故障:概述
     * */
    @ApiModelProperty(value = "设备缺陷、故障:概述")
    private String type6Keyword;

    /**
     * 说明
     * */
    @ApiModelProperty(value = "说明")
    private String remark;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

}
