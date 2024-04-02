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
public class IndicatorRecordReqDTO extends BaseEntity {

    /**
     * 列车运行图编号
     * */
    @ApiModelProperty(value = " 列车运行图编号")
    private String runCode;

    /**
     * 上线列车数及备车数
     * */
    @ApiModelProperty(value = " 上线列车数及备车数")
    private Integer trainCount;

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

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

}
