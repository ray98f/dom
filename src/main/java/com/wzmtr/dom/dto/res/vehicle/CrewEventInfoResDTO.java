package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CrewEventInfoResDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 事件类型
     * */
    @ApiModelProperty(value = " 事件类型")
    private String eventType;

    /**
     * 事件概况
     * */
    @ApiModelProperty(value = " 事件概况")
    private String eventDesc;

    /**
     * 司机处理
     * */
    @ApiModelProperty(value = " 司机处理")
    private String driverHandle;

    /**
     * 整改措施
     * */
    @ApiModelProperty(value = " 整改措施")
    private String eventMeasure;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

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
