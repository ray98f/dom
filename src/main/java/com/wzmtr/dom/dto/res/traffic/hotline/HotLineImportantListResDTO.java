package com.wzmtr.dom.dto.res.traffic.hotline;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 16:58
 */
@Data
public class HotLineImportantListResDTO {
    @ApiModelProperty("ID")
    private String id;
    /**
     * 总投诉
     */
    @ApiModelProperty("总投诉")
    private Long complaintTotal;
    /**
     * 总表扬
     */
    @ApiModelProperty("总表扬")
    private Long praiseTotal;

    /**
     * 锦旗
     */
    @ApiModelProperty("锦旗")
    private Long pennant;


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
