package com.wzmtr.dom.dto.req.traffic;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 日数据请求类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRecordReqDTO extends BaseEntity {


    /**
     * 本日客流量(线网)/万人
     * */
    @ApiModelProperty(value = "本日客流量(线网)/万人")
    private Double wiringDayPassenger;

    /**
     * 本周客流量(线网)/万人
     * */
    @ApiModelProperty(value = "本周客流量(线网)/万人")
    private Double wiringWeekPassenger;

    /**
     * 本月客流量(线网)/万人
     * */
    @ApiModelProperty(value = "本月客流量(线网)/万人")
    private Double wiringMonthPassenger;

    /**
     * 本年客流量(线网)/万人
     * */
    @ApiModelProperty(value = "本年客流量(线网)/万人")
    private Double wiringYearPassenger;

    /**
     * 换乘客运量
     * */
    @ApiModelProperty(value = "换乘客运量")
    private Double transferCount;

    /**
     * S2线换入客运量
     * */
    @ApiModelProperty(value = "S2线换入客运量")
    private Double s2TransferIn;

    /**
     * S2线换出客运量
     * */
    @ApiModelProperty(value = "S2线换出客运量")
    private Double s2TransferOut;

    /**
     * S2线进站量
     * */
    @ApiModelProperty(value = "S2线进站量")
    private Double s2In;

    /**
     * 进站正常量
     * */
    @ApiModelProperty(value = "进站正常量")
    private Double inSuccess;

    /**
     * 进站工作卡
     * */
    @ApiModelProperty(value = "进站工作卡")
    private Double cardIn;

    /**
     * S2出站量
     * */
    @ApiModelProperty(value = "S2出站量")
    private Double s2Out;

    /**
     * 出站正常量
     * */
    @ApiModelProperty(value = "出站正常量")
    private Double outSuccess;

    /**
     * 出站工作卡
     * */
    @ApiModelProperty(value = "出站工作卡")
    private Double cardOut;

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
