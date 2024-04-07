package com.wzmtr.dom.dto.req.operate;

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
public class IndicatorInfoReqDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = " 记录ID")
    private String recordId;

    /**
     * 八项指标类型:1件数/次数,2指标值,3指标值(国标)4运营服务指标
     * */
    @ApiModelProperty(value = " 八项指标类型:1件数/次数,2指标值,3指标值(国标)4运营服务指标")
    private String indicatorType;

    /**
     * 正点率
     * */
    @ApiModelProperty(value = " 正点率")
    private String punctualityRate;

    /**
     * 兑现率
     * */
    @ApiModelProperty(value = " 兑现率")
    private String fulfillmentRate;

    /**
     * 列车服务可靠度
     * */
    @ApiModelProperty(value = " 列车服务可靠度")
    private String serviceReliability;

    /**
     * 列车退出正线故障率
     * */
    @ApiModelProperty(value = " 列车退出正线故障率")
    private String lineFaultRate;

    /**
     * 车辆系统故障率
     * */
    @ApiModelProperty(value = " 车辆系统故障率")
    private String vehicleFaultRate;

    /**
     * 信号系统故障率
     * */
    @ApiModelProperty(value = " 信号系统故障率")
    private String signalFaultRate;

    /**
     * 站台门故障率
     * */
    @ApiModelProperty(value = " 站台门故障率")
    private String gateFaultRate;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

}
