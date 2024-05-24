package com.wzmtr.dom.dto.res.traffic.oneway;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 14:42
 */
@Data
public class OnewaySaleListResDTO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("现金购票")
    private Long cash;
    @ApiModelProperty("银联卡")
    private Long unionCard;
    @ApiModelProperty("免费福利票")
    private Long freeTicket;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;
    @ApiModelProperty(value = "数据起始日期")
    private Date startDate;
    @ApiModelProperty(value = "数据终止日期")
    private Date endDate;

}
