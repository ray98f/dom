package com.wzmtr.dom.dto.res.traffic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
        private String eventTime;
        /**
         * 事件描述
         */
        @ApiModelProperty(value = "事件描述")
        private String eventDesc;
    }

}
