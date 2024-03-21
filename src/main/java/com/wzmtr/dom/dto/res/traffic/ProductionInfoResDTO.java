package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionInfoResDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

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
     * 生产事件类型
     * */
    @ApiModelProperty(value = "生产事件类型")
    private String productionType;

    /**
     * 生产事件类型
     * */
    @ApiModelProperty(value = "事件标题")
    private String title;

    /**
     * 施工作业时间
     * */
    @ApiModelProperty(value = "施工作业时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date workTime;

    /**
     * 施工作业单位
     * */
    @ApiModelProperty(value = "施工作业单位")
    private String workDept;

    /**
     * 施工作业内容
     * */
    @ApiModelProperty(value = "施工作业内容")
    private String workContent;

    /**
     * 事件时间
     * */
    @ApiModelProperty(value = "事件时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date eventTime;

    /**
     * 事件描述
     * */
    @ApiModelProperty(value = "事件描述")
    private String eventDesc;

    /**
     * 事件影响
     * */
    @ApiModelProperty(value = "事件影响")
    private String eventAffect;

    /**
     * 现场处置
     * */
    @ApiModelProperty(value = "现场处置")
    private String eventHandle;

    /**
     * 图片
     * */
    @ApiModelProperty(value = "图片")
    private String picUrl;

    /**
     * 是否闭环:0否,1是
     * */
    @ApiModelProperty(value = "是否闭环:0否,1是")
    private String isClose;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

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
}
