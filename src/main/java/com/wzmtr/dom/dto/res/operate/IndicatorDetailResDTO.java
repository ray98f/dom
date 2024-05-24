package com.wzmtr.dom.dto.res.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IndicatorDetailResDTO extends BaseEntity {

    /**
     * 列车运行图编号
     * */
    @ApiModelProperty(value = " 列车运行图编号")
    private String runCode;

    /**
     * 上线列车数及备车数
     * */
    @ApiModelProperty(value = " 上线列车数及备车数")
    private String trainCount;

    /**
     * 最小发车时间间隔(秒)
     * */
    @ApiModelProperty(value = " 最小发车时间间隔(秒)")
    private Integer sendTimeInterval;

    /**
     * 无事故运营天数(天)
     * */
    @ApiModelProperty(value = " 无事故运营天数(天)")
    private Integer noAccidentDay;

    /**
     * 计划开行列次
     * */
    @ApiModelProperty(value = " 计划开行列次")
    private Integer planRunCount;

    /**
     * 计划兑现列次
     * */
    @ApiModelProperty(value = " 计划兑现列次")
    private Integer planPromiseCount;

    /**
     * 加开列次
     * */
    @ApiModelProperty(value = " 加开列次")
    private Integer addCount;

    /**
     * 实际开行列次
     * */
    @ApiModelProperty(value = " 实际开行列次")
    private Integer realRunCount;

    /**
     * 停运列次
     * */
    @ApiModelProperty(value = " 停运列次")
    private Integer stopCount;

    /**
     * 清客列次
     * */
    @ApiModelProperty(value = " 清客列次")
    private Integer rutineGuestCount;

    /**
     * 救援列次
     * */
    @ApiModelProperty(value = " 救援列次")
    private Integer rescueCount;

    /**
     * 掉线列次
     * */
    @ApiModelProperty(value = " 掉线列次")
    private Integer offLineCount;

    /**
     * 晚点列次
     * */
    @ApiModelProperty(value = " 晚点列次")
    private Integer delayCount;

    /**
     * 3-5分钟晚点列次
     * */
    @ApiModelProperty(value = " 3-5分钟晚点列次")
    private Integer delay2Count;

    /**
     * 5-15分钟延误
     * */
    @ApiModelProperty(value = " 5-15分钟延误")
    private Integer delay3Count;

    /**
     * 15分钟延误
     * */
    @ApiModelProperty(value = " 15分钟延误")
    private Integer delay4Count;

    /**
     * 运营车公里(万列公里)
     * */
    @ApiModelProperty(value = " 运营车公里(万列公里)")
    private Double operate1Kilometer;

    /**
     * 运营车公里(万车公里)
     * */
    @ApiModelProperty(value = " 运营车公里(万车公里)")
    private Double operate2Kilometer;

    /**
     * 走行车公里(万列公里)
     * */
    @ApiModelProperty(value = " 走行车公里(万列公里)")
    private Double run1Kilometer;

    /**
     * 走行车公里(万车公里)
     * */
    @ApiModelProperty(value = " 走行车公里(万车公里)")
    private Double run2Kilometer;

    @ApiModelProperty(value = "电能消耗情况")
    private IndicatorPowerResDTO indicatorPower;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 列车退出正线数
     * */
    private Integer operateEvent1;

    /**
     * 车辆系统故障
     * */
    private Integer operateEvent2;

    /**
     * (信号系统故障)数
     * */
    private Integer operateEvent3;

    /**
     * (供电系统故障)数
     * */
    private Integer operateEvent4;

    /**
     * 站台门故障数
     * */
    private Integer operateEvent5;

    /**
     * 列车退出正线率
     * */
    private String event1Rate;

    /**
     * 车辆系统故障率
     * */
    private String event2Rate;

    /**
     * 信号系统故障率
     * */
    private String event3Rate;

    /**
     * 供电系统故障率
     * */
    private String event4Rate;
    /**
     * 站台门故障率
     * */
    private String event5Rate;

    /**
     * 正点率
     * */
    private String punctualityRate;

    /**
     * 兑现率
     * */
    private String fulfillmentRate;

    /**
     * 列车服务可靠度
     * */
    private String serviceReliability;

    public String getTrainCount() {
        return Optional.ofNullable(trainCount).orElse("");
    }

    public Integer getSendTimeInterval() {
        return Optional.ofNullable(sendTimeInterval).orElse(0);
    }

    public Integer getNoAccidentDay() {
        return Optional.ofNullable(noAccidentDay).orElse(0);
    }

    public Integer getPlanRunCount() {
        return Optional.ofNullable(planRunCount).orElse(0);
    }

    public Integer getPlanPromiseCount() {
        return Optional.ofNullable(planPromiseCount).orElse(0);
    }

    public Integer getAddCount() {
        return Optional.ofNullable(addCount).orElse(0);
    }

    public Integer getRealRunCount() {
        return Optional.ofNullable(realRunCount).orElse(0);
    }

    public Integer getStopCount() {
        return Optional.ofNullable(stopCount).orElse(0);
    }

    public Integer getRutineGuestCount() {
        return Optional.ofNullable(rutineGuestCount).orElse(0);
    }

    public Integer getRescueCount() {
        return Optional.ofNullable(rescueCount).orElse(0);
    }

    public Integer getOffLineCount() {
        return Optional.ofNullable(offLineCount).orElse(0);
    }

    public Integer getDelayCount() {
        return Optional.ofNullable(delayCount).orElse(0);
    }

    public Integer getDelay2Count() {
        return Optional.ofNullable(delay2Count).orElse(0);
    }

    public Integer getDelay3Count() {
        return Optional.ofNullable(delay3Count).orElse(0);
    }

    public Integer getDelay4Count() {
        return Optional.ofNullable(delay4Count).orElse(0);
    }

    public Double getOperate1Kilometer() {
        return Optional.ofNullable(operate1Kilometer).orElse(0.0);
    }

    public Double getOperate2Kilometer() {
        return Optional.ofNullable(operate2Kilometer).orElse(0.0);
    }

    public Double getRun1Kilometer() {
        return Optional.ofNullable(run1Kilometer).orElse(0.0);
    }

    public Double getRun2Kilometer() {
        return Optional.ofNullable(run2Kilometer).orElse(0.0);
    }

    public Integer getOperateEvent1() {
        return Optional.ofNullable(operateEvent1).orElse(0);
    }

    public Integer getOperateEvent2() {
        return Optional.ofNullable(operateEvent2).orElse(0);
    }

    public Integer getOperateEvent3() {
         return Optional.ofNullable(operateEvent3).orElse(0);
    }

    public Integer getOperateEvent4() {
        return Optional.ofNullable(operateEvent4).orElse(0);
    }

    public Integer getOperateEvent5() {
        return Optional.ofNullable(operateEvent5).orElse(0);
    }
}
