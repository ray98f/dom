package com.wzmtr.dom.dto.req.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 线网车票过闸使用情况请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketUseReqDTO extends BaseEntity {

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
