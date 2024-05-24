package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LineEventDetailResDTO extends BaseEntity {

    /**
     * 车辆类事件数
     * */
    @ApiModelProperty(value = "车辆类事件数")
    private Integer type1Count;

    /**
     * 信号类事件数
     * */
    @ApiModelProperty(value = " 信号类事件数")
    private Integer type2Count;

    /**
     * 侵限类事件数
     * */
    @ApiModelProperty(value = " 侵限类事件数")
    private Integer type3Count;

    /**
     * 设备类事件数
     * */
    @ApiModelProperty(value = " 设备类事件数")
    private Integer type4Count;

    /**
     * 其他类事件数
     * */
    @ApiModelProperty(value = " 其他类事件数")
    private Integer type5Count;

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
