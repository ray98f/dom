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
    private IncomeListResDTO totalIncome;
    @ApiModelProperty(value = "现金")
    private Double type1Income;
    @ApiModelProperty(value = "一票通收益")
    private Double type2Income;
    @ApiModelProperty(value = "一卡通收益")
    private Double type3Income;
    @ApiModelProperty(value = "二维码收益")
    private Double type4Income;
    @ApiModelProperty(value = "银联收益")
    private Double type5Income;
    @ApiModelProperty(value = "划账数S1")
    private Double s1Remittance;
    @ApiModelProperty(value = "划账数S2")
    private Double s2Remittance;
    @ApiModelProperty(value = "平均票价S2")
    private Double s2AvgFare;
    @ApiModelProperty(value = "平均票价现网")
    private Double wiringAvgFare;
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;
}
