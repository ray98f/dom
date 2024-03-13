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
public class LineEventInfoResDTO extends BaseEntity {

    @ApiModelProperty(value = "记录ID")
    private String recordId;

    @ApiModelProperty(value = " 事件类型1车辆类,2信号类,3侵限类,4设备类,5其他类")
    private String eventType;

    @ApiModelProperty(value = " 事件概况")
    private String eventDesc;

    @ApiModelProperty(value = " 事件分析")
    private String eventAnalyze;

    @ApiModelProperty(value = "版本号")
    private String version;

}
