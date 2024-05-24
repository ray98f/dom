package com.wzmtr.dom.dto.res.traffic.oneway;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.utils.BeanUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:42
 */
@Data
public class OnewaySaleDetailResDTO {
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
    @ApiModelProperty("现金购票环比")
    private Double cashRatioQoqRatio;
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
    @ApiModelProperty("银联卡环比")
    private Double unionCardRatioQoqRatio;
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
    @ApiModelProperty("TVM聚合码环比")
    private Double tvmCodeRatioQoqRatio;
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
    @ApiModelProperty("ITVM聚合码环比")
    private Double itvmCodeRatioQoqRatio;
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
    @ApiModelProperty("BOM聚合码环比")
    private Double bomCodeRatioQoqRatio;
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
    @ApiModelProperty("IBOM聚合码环比")
    private Double ibomCodeRatioQoqRatio;
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
    @ApiModelProperty("免费福利票环比")
    private Double freeTicketRatioQoqRatio;
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
    @ApiModelProperty("8折福利票环比")
    private Double off20TicketRatioQoqRatio;
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
    @ApiModelProperty("付费出站环比")
    private Double payExitRatioQoqRatio;
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
    @ApiModelProperty("免费出站环比")
    private Double freeExitRatioQoqRatio;
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
    @ApiModelProperty("预赋值票环比")
    private Double preAssignRatioQoqRatio;
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
     * 前一日/周/月 详情
     */
    private OnewaySaleDetailResDTO preOnewaySaleDetail;

    public static OnewaySaleDetailResDTO buildRes(TrafficOnewaySaleDO trafficOnewaySaleDO) {
        return BeanUtils.convert(trafficOnewaySaleDO, OnewaySaleDetailResDTO.class);
    }
}
