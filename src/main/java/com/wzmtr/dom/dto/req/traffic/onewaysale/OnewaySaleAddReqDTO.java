package com.wzmtr.dom.dto.req.traffic.onewaysale;

import com.wzmtr.dom.dataobject.traffic.TrafficOnewaySaleDO;
import com.wzmtr.dom.utils.BeanUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 15:03
 */
@Data
public class OnewaySaleAddReqDTO {
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


    public TrafficOnewaySaleDO toDO(OnewaySaleAddReqDTO req) {
        return BeanUtils.convert(req, TrafficOnewaySaleDO.class);
    }
}
