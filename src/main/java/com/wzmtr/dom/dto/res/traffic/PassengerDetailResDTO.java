package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PassengerDetailResDTO extends BaseEntity {

    /**
     * 车站客流列表
     * */
    @ApiModelProperty(value = "车站客流列表")
    private List<PassengerInfoResDTO> stationPassengerList;

    /**
     * 本日客流量(S2)
     * */
    @ApiModelProperty(value = "本日客流量(S2)")
    private Double dayPassenger;

    /**
     * 本日客流量(S2)
     * */
    @ApiModelProperty(value = "本日客流量(S2)")
    private Double weekPassenger;

    /**
     * 本日客流量(S2)
     * */
    @ApiModelProperty(value = "本日客流量(S2)")
    private Double monthPassenger;

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
}
