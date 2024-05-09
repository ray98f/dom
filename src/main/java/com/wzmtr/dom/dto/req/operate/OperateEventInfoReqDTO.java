package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.dto.res.operate.OperateEventDetailResDTO;
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
public class OperateEventInfoReqDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 专业
     * */
    @ApiModelProperty(value = "专业")
    private String majorType;

    /**
     * 事件标题
     * */
    @ApiModelProperty(value = "事件标题")
    private String title;

    /**
     * 掉线事件数
     * */
    @ApiModelProperty(value = "掉线事件数")
    private Integer offLineCount;

    /**
     * 救援事件数
     * */
    @ApiModelProperty(value = "救援事件数")
    private Integer rescueCount;

    /**
     * 清客事件数
     * */
    @ApiModelProperty(value = "清客事件数")
    private Integer rutineGuestCount;

    /**
     * 加开事件数
     * */
    @ApiModelProperty(value = "加开事件数")
    private Integer addCount;

    /**
     * 晚点5分钟内事件数
     * */
    @ApiModelProperty(value = "晚点5分钟内事件数")
    private Integer delay1Count;

    /**
     * 晚点5分钟以上事件数
     * */
    @ApiModelProperty(value = "晚点5分钟以上事件数")
    private Integer delay2Count;

    /**
     * 停运事件数
     * */
    @ApiModelProperty(value = "停运事件数")
    private Integer stopCount;

    /**
     * 延误15分钟内件数
     * */
    @ApiModelProperty(value = "延误15分钟内件数")
    private Integer delay1EventCount;

    /**
     * 延误15分钟内列数
     * */
    @ApiModelProperty(value = "延误15分钟内列数")
    private Integer delay1VehicleCount;

    /**
     * 延误15分钟内件数
     * */
    @ApiModelProperty(value = "延误15分钟以上件数")
    private Integer delay2EventCount;

    /**
     * 延误15分钟内列数
     * */
    @ApiModelProperty(value = "延误15分钟以上列数")
    private Integer delay2VehicleCount;

    /**
     * 掉线事件数
     * */
    @ApiModelProperty(value = "站台门故障数")
    private Integer gateFaultCount;

    /**
     * 列车退出正线故障数
     * */
    @ApiModelProperty(value = "列车退出正线故障数")
    private Integer lineFaultCount;

    /**
     * 车辆系统故障数
     * */
    @ApiModelProperty(value = "车辆系统故障数")
    private Integer vehicleFaultCount;

    /**
     * 信号系统故障数
     * */
    @ApiModelProperty(value = "信号系统故障数")
    private Integer signalFaultCount;

    /**
     * 供电系统故障数
     * */
    @ApiModelProperty(value = "供电系统故障数")
    private Integer powerFaultCount;

    /**
     * 事件内容 概括
     * */
    @ApiModelProperty(value = "事件内容")
    private String eventDesc;

    /**
     * 造成指标
     * */
    @ApiModelProperty(value = "造成指标")
    private String effect;

    /**
     * 是否整改
     * */
    @ApiModelProperty(value = "是否整改")
    private String isRectified;

    /**
     * 事件等级
     * */
    @ApiModelProperty(value = "事件等级")
    private String eventLevel;

    /**
     * 是否闭环
     * */
    @ApiModelProperty(value = "是否闭环")
    private String isClose;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    /**
     * 是否编辑事件明细
     */
    String editDetailFlag;
    /**
     * 事件明细
     */
    List<OperateEventDetailReqDTO> eventDetailList;
}
