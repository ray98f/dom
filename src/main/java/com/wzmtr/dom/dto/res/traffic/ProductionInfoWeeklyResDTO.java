package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@Data
public class ProductionInfoWeeklyResDTO {

    /**
     * 车站编码
     */
    @ApiModelProperty(value = "车站编码")
    private String stationCode;

    /**
     * 车站名称
     */
    @ApiModelProperty(value = "车站名称")
    private String stationName;

    /**
     * 运检异常列表
     */
    @ApiModelProperty(value = "运检异常列表")
    private List<ProductionTwo> productionTwoList;

    @Data
    public static class ProductionTwo {
        /**
         * 施工作业时间
         */
        @ApiModelProperty(value = "施工作业时间")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(
                pattern = "yyyy-MM-dd",
                timezone = "GMT+8"
        )
        private Date time;
        /**
         * 事件描述
         */
        @ApiModelProperty(value = "事件描述")
        private String eventDesc;
    }

}
