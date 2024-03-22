package com.wzmtr.dom.dto.req.traffic.income;

import com.wzmtr.dom.dto.res.traffic.income.IncomeListResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 17:46
 */
@Data
public class IncomeAddReqDTO {
    @ApiModelProperty("运营总收益")
    private IncomeListResDTO totalIncomeList;
    @ApiModelProperty(value = "现金")
    private Double cashIncome;
    @ApiModelProperty(value = "一票通收益")
    private Double oneTicketPassIncome;
    @ApiModelProperty(value = "一卡通收益")
    private Double oneCardPassIncome;
    @ApiModelProperty(value = "二维码收益")
    private Double qrIncome;
    @ApiModelProperty(value = "银联收益")
    private Double unionPayIncome;
    @ApiModelProperty(value = "划账数S1")
    private Double TransferNumberS1;
    @ApiModelProperty(value = "划账数S2")
    private Double TransferNumberS2;
    @ApiModelProperty(value = "平均票价S2")
    private Double AvgPriceS2;
    @ApiModelProperty(value = "平均票价现网")
    private Double AvgPriceNet;
}
