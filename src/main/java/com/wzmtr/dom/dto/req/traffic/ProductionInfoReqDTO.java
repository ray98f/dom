package com.wzmtr.dom.dto.req.traffic;

import com.wzmtr.dom.dto.req.operate.OperateEventDetailReqDTO;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * description:
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionInfoReqDTO extends BaseEntity {

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 车站编码
     */
    @ApiModelProperty(value = "车站编码")
    private String stationCode;

    /**
     * 生产事件类型1施工异常2运检异常3行车异常4其他突发事件5其他事件6设备缺陷、故障
     */
    @ApiModelProperty(value = "生产事件类型1施工异常2运检异常3行车异常4其他突发事件5其他事件6设备缺陷、故障")
    private String productionType;

    /**
     * 事件标题
     */
    @ApiModelProperty(value = "事件标题")
    private String title;

    /**
     * 施工作业时间
     */
    @ApiModelProperty(value = "施工作业时间")
    private String workTime;

    /**
     * 施工作业结束时间
     */
    @ApiModelProperty(value = "施工作业时间")
    private String workTimeEnd;

    /**
     * 施工作业代码
     */
    @ApiModelProperty(value = "施工作业代码")
    private String workCode;

    /**
     * 施工作业单位
     */
    @ApiModelProperty(value = "施工作业单位")
    private String workDept;

    /**
     * 施工作业内容
     */
    @ApiModelProperty(value = "施工作业内容")
    private String workContent;

    /**
     * 事件时间
     */
    @ApiModelProperty(value = "事件时间")
    private String eventTime;

    /**
     * 事件描述
     */
    @ApiModelProperty(value = "事件描述")
    private String eventDesc;

    /**
     * 事件影响
     */
    @ApiModelProperty(value = "事件影响")
    private String eventAffect;

    /**
     * 现场处置
     */
    @ApiModelProperty(value = "现场处置")
    private String eventHandle;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String picUrl;

    /**
     * 是否闭环:0否,1是
     */
    @ApiModelProperty(value = "是否闭环:0否,1是")
    private String isClose;

    /**
     * 现场处置及处理结果
     */
    @ApiModelProperty(value = "现场处置及处理结果")
    private String result;

    /**
     * 数据类型:1日报 2月报 3周报
     */
    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    /**
     * 数据所属日期
     */
    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    /**
     * 数据起始日期
     */
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    /**
     * 数据终止日期
     */
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 是否编辑事件明细
     */
    String editDetailFlag;
    /**
     * 事件明细
     */
    List<ProEventDetailReqDTO> eventDetailList;
}
