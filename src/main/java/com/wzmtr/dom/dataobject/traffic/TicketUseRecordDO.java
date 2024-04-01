package com.wzmtr.dom.dataobject.traffic;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 客运部-车票过闸使用记录表
* @TableName TRAFFIC_TICKET_USE_RECORD
*/
@Data
@TableName("TRAFFIC_TICKET_USE_RECORD")
public class TicketUseRecordDO implements Serializable {
    /**
    * ID
    */
    @ApiModelProperty("ID")
    private String id;
    /**
    * 单程票数
    */
    @ApiModelProperty("单程票数")
    private Long oneWayTicket;
    /**
    * 单程票占比
    */
    @ApiModelProperty("单程票占比")
    private Double oneWayRatio;
    /**
    * 市民卡-普通卡
    */
    @ApiModelProperty("市民卡-普通卡")
    private Long citizenCardRegular;
    /**
    * 普通卡占比
    */
    @ApiModelProperty("普通卡占比")
    private Double citizenCardRegularRatio;
    /**
    * 市民卡-优惠卡
    */
    @ApiModelProperty("市民卡-优惠卡")
    private Long citizenCardDiscount;
    /**
    * 优惠卡占比
    */
    @ApiModelProperty("优惠卡占比")
    private Double citizenCardDiscountRatio;
    /**
    * 市民卡-爱心卡
    */
    @ApiModelProperty("市民卡-爱心卡")
    private Long citizenCardLove;
    /**
    * 爱心卡占比
    */
    @ApiModelProperty("爱心卡占比")
    private Double citizenCardLoveRatio;
    /**
    * 市民卡-优待卡
    */
    @ApiModelProperty("市民卡-优待卡")
    private Long citizenCardPrefer;
    /**
    * 优待卡占比
    */
    @ApiModelProperty("优待卡占比")
    private Double citizenCardPreferRatio;
    /**
    * 交通部一卡通
    */
    @ApiModelProperty("交通部一卡通")
    private Long oneCard;
    /**
    * 一卡通占比
    */
    @ApiModelProperty("一卡通占比")
    private Double oneCardRatio;
    /**
    * 银联卡
    */
    @ApiModelProperty("银联卡")
    private Long unionCard;
    /**
    * 银联卡占比
    */
    @ApiModelProperty("银联卡占比")
    private Double unionCardRatio;
    /**
    * 二维码
    */
    @ApiModelProperty("二维码")
    private Long qrcode;
    /**
    * 二维码占比
    */
    @ApiModelProperty("二维码占比")
    private Double qrcodeRatio;
    /**
    * 工作卡
    */
    @ApiModelProperty("工作卡")
    private Long empCard;
    /**
    * 工作卡占比
    */
    @ApiModelProperty("工作卡占比")
    private Double empCardRatio;
    /**
    * 计时票
    */
    @ApiModelProperty("计时票")
    private Long timeTicket;
    /**
    * 计时票占比
    */
    @ApiModelProperty("计时票占比")
    private Double timeTicketRatio;
    /**
    * 计次票
    */
    @ApiModelProperty("计次票")
    private Long countingTicket;
    /**
    * 计次票占比
    */
    @ApiModelProperty("计次票占比")
    private Double countingTicketRatio;
    /**
    * 数据所属日期
    */
    @ApiModelProperty("数据所属日期")
    private Date dataDate;
    /**
    * 数据类型 1日报 2周报 3月报
    */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;
    /**
    * 数据起始日期
    */
    @ApiModelProperty("数据起始日期")
    private Date startDate;
    /**
    * 数据结束日期
    */
    @ApiModelProperty("数据结束日期")
    private Date endDate;
    /**
    * 删除标识
    */
    @ApiModelProperty("删除标识")
    private String delFlag;
    /**
    * 版本号(乐观锁)
    */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
    * 创建日期
    */
    @ApiModelProperty("创建日期")
    private Date createDate;
    /**
    * 创建者
    */
    @ApiModelProperty("创建者")
    private String createBy;
    /**
    * 更新日期
    */
    @ApiModelProperty("更新日期")
    private Date updateDate;
    /**
    * 更新者
    */
    @ApiModelProperty("更新者")
    private String updateBy;


}
