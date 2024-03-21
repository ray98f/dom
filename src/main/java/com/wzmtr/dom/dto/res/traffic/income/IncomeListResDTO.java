package com.wzmtr.dom.dto.res.traffic.income;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 10:34
 */
@Data
public class IncomeListResDTO {

    /**
     * 站点编码
     */
    @ApiModelProperty(value = "本日总收益")
    private Double dayIncome;

    /**
     * 站点名称
     */
    @ApiModelProperty(value = "本月累计收益")
    private Double monthIncome;

    /**
     * 车站客流量
     */
    @ApiModelProperty(value = "本年累计收益")
    private Double yearIncome;


    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

}
