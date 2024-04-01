package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 线网车票过闸使用情况结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@Data
public class TicketUseResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 单程票数
     */
    @ApiModelProperty(value = "单程票数")
    private Integer oneWayTicket;

    /**
     * 单程票占比
     */
    @ApiModelProperty(value = "单程票占比")
    private Double oneWayRatio;

    /**
     * 单程票环比
     */
    @ApiModelProperty(value = "单程票环比")
    private Double oneWayQoqRatio;

    /**
     * 市民卡-普通卡
     */
    @ApiModelProperty(value = "市民卡-普通卡")
    private Integer citizenCardRegular;

    /**
     * 普通卡占比
     */
    @ApiModelProperty(value = "普通卡占比")
    private Double citizenCardRegularRatio;

    /**
     * 普通卡环比
     */
    @ApiModelProperty(value = "普通卡环比")
    private Double citizenCardRegularQoqRatio;

    /**
     * 市民卡-优惠卡
     */
    @ApiModelProperty(value = "市民卡-优惠卡")
    private Integer citizenCardDiscount;

    /**
     * 优惠卡占比
     */
    @ApiModelProperty(value = "优惠卡占比")
    private Double citizenCardDiscountRatio;

    /**
     * 优惠卡环比
     */
    @ApiModelProperty(value = "优惠卡环比")
    private Double citizenCardDiscountQoqRatio;

    /**
     * 市民卡-爱心卡
     */
    @ApiModelProperty(value = "市民卡-爱心卡")
    private Integer citizenCardLove;

    /**
     * 爱心卡占比
     */
    @ApiModelProperty(value = "爱心卡占比")
    private Double citizenCardLoveRatio;

    /**
     * 爱心卡环比
     */
    @ApiModelProperty(value = "爱心卡环比")
    private Double citizenCardLoveQoqRatio;

    /**
     * 市民卡-优待卡
     */
    @ApiModelProperty(value = "市民卡-优待卡")
    private Integer citizenCardPrefer;

    /**
     * 优待卡占比
     */
    @ApiModelProperty(value = "优待卡占比")
    private Double citizenCardPreferRatio;

    /**
     * 优待卡环比
     */
    @ApiModelProperty(value = "优待卡环比")
    private Double citizenCardPreferQoqRatio;

    /**
     * 交通部一卡通
     */
    @ApiModelProperty(value = "交通部一卡通")
    private Integer oneCard;

    /**
     * 一卡通占比
     */
    @ApiModelProperty(value = "一卡通占比")
    private Double oneCardRatio;

    /**
     * 一卡通环比
     */
    @ApiModelProperty(value = "一卡通环比")
    private Double oneCardQoqRatio;

    /**
     * 银联卡
     */
    @ApiModelProperty(value = "银联卡")
    private Integer unionCard;

    /**
     * 银联卡占比
     */
    @ApiModelProperty(value = "银联卡占比")
    private Double unionCardRatio;

    /**
     * 银联卡环比
     */
    @ApiModelProperty(value = "银联卡环比")
    private Double unionCardQoqRatio;

    /**
     * 二维码
     */
    @ApiModelProperty(value = "二维码")
    private Integer qrcode;

    /**
     * 二维码占比
     */
    @ApiModelProperty(value = "二维码占比")
    private Double qrcodeRatio;

    /**
     * 二维码环比
     */
    @ApiModelProperty(value = "二维码环比")
    private Double qrcodeQoqRatio;

    /**
     * 工作卡
     */
    @ApiModelProperty(value = "工作卡")
    private Integer empCard;

    /**
     * 工作卡占比
     */
    @ApiModelProperty(value = "工作卡占比")
    private Double empCardRatio;

    /**
     * 工作卡环比
     */
    @ApiModelProperty(value = "工作卡环比")
    private Double empCardQoqRatio;

    /**
     * 计时票
     */
    @ApiModelProperty(value = "计时票")
    private Integer timeTicket;

    /**
     * 计时票占比
     */
    @ApiModelProperty(value = "计时票占比")
    private Double timeTicketRatio;

    /**
     * 计时票环比
     */
    @ApiModelProperty(value = "计时票环比")
    private Double timeTicketQoqRatio;

    /**
     * 计次票
     */
    @ApiModelProperty(value = "计次票")
    private Integer countingTicket;

    /**
     * 计次票占比
     */
    @ApiModelProperty(value = "计次票占比")
    private Double countingTicketRatio;

    /**
     * 计次票环比
     */
    @ApiModelProperty(value = "计次票环比")
    private Double countingTicketQoqRatio;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;

    /**
     * 所属日期
     */
    @ApiModelProperty(value = "所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date dataDate;

    /**
     * 数据类型 1:日报 2:周报 3:月报
     */
    @ApiModelProperty(value = "数据类型 1:日报 2:周报 3:月报")
    private String dataType;

    /**
     * 数据起始日期
     */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据结束日期
     */
    @ApiModelProperty(value = "数据结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;
}
