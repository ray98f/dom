package com.wzmtr.dom.dataobject.traffic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
* 客运部-单程票发售情况表
* @TableName TRAFFIC_ONEWAY_SALE
*/
@Data
@TableName("TRAFFIC_ONEWAY_SALE")
public class TrafficOnewaySaleDO implements Serializable {

    /**
    * ID
    */
    @ApiModelProperty("ID")
    @TableId
    private String id;
    /**
    * 现金购票
    */
    @ApiModelProperty("现金购票")
    private Long cash;
    /**
    * 现金购票占比
    */
    @ApiModelProperty("现金购票占比")
    private Double cashRatio;
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
    * TVM聚合码
    */
    @ApiModelProperty("TVM聚合码")
    private Long tvmCode;
    /**
    * TVM聚合码占比
    */
    @ApiModelProperty("TVM聚合码占比")
    private Double tvmCodeRatio;
    /**
    * ITVM聚合码
    */
    @ApiModelProperty("ITVM聚合码")
    private Long itvmCode;
    /**
    * ITVM聚合码占比
    */
    @ApiModelProperty("ITVM聚合码占比")
    private Double itvmCodeRatio;
    /**
    * BOM聚合码
    */
    @ApiModelProperty("BOM聚合码")
    private Long bomCode;
    /**
    * BOM聚合码占比
    */
    @ApiModelProperty("BOM聚合码占比")
    private Double bomCodeRatio;
    /**
    * IBOM聚合码
    */
    @ApiModelProperty("IBOM聚合码")
    private Long ibomCode;
    /**
    * IBOM聚合码占比
    */
    @ApiModelProperty("IBOM聚合码占比")
    private Double ibomCodeRatio;
    /**
    * 免费福利票
    */
    @ApiModelProperty("免费福利票")
    private Long freeTicket;
    /**
    * 免费福利票占比
    */
    @ApiModelProperty("免费福利票占比")
    private Double freeTicketRatio;
    /**
    * 8折福利票
    */
    @ApiModelProperty("8折福利票")
    private Long off20Ticket;
    /**
    * 8折福利票占比
    */
    @ApiModelProperty("8折福利票占比")
    private Double off20TicketRatio;
    /**
    * 付费出站票
    */
    @ApiModelProperty("付费出站票")
    private Long payExit;
    /**
    * 付费出站占比
    */
    @ApiModelProperty("付费出站占比")
    private Double payExitRatio;
    /**
    * 免费出站票
    */
    @ApiModelProperty("免费出站票")
    private Long freeExit;
    /**
    * 免费出站占比
    */
    @ApiModelProperty("免费出站占比")
    private Double freeExitRatio;
    /**
    * 预赋值票
    */
    @ApiModelProperty("预赋值票")
    private Long preAssign;
    /**
    * 预赋值票占比
    */
    @ApiModelProperty("预赋值票占比")
    private Double preAssignRatio;
    /**
    * 数据所属日期
    */
    @ApiModelProperty("数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;
    /**
    * 数据结束日期
    */
    @ApiModelProperty("数据结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
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
