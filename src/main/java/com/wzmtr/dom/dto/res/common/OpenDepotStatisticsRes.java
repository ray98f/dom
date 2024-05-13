package com.wzmtr.dom.dto.res.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/11 14:55
 */
@Data
public class OpenDepotStatisticsRes {

    @ApiModelProperty("市域动车组计划发车列数/列（下塘）")
    private Integer xtPlanDepartureCount;
    @ApiModelProperty("市域动车组实际发车列数/数（下塘）")
    private Integer xtActualDepartureCount;
    @ApiModelProperty("市域动车组计划接车列数/列（下塘）")
    private Integer xtPlanArrivalCount;
    @ApiModelProperty("市域动车组实际接车列数/数（下塘）")
    private Integer xtActualArrivalCount;
    @ApiModelProperty("市域动车组计划发车列数/列（汀田）")
    private Integer ttPlanDepartureCount;
    @ApiModelProperty("市域动车组实际发车列数/数（汀田）")
    private Integer ttActualDepartureCount;
    @ApiModelProperty("市域动车组计划接车列数/列（汀田）")
    private Integer ttPlanArrivalCount;
    @ApiModelProperty("市域动车组实际接车列数/数（汀田）")
    private Integer ttActualArrivalCount;
    @ApiModelProperty("调车件数/件（下塘）")
    private Integer xtShuntingCount;
    @ApiModelProperty("调车勾数/勾（下塘）")
    private Integer xtShuntingHookCount;
    @ApiModelProperty("调车时间/分（下塘）")
    private Integer xtShuntingTime;
    @ApiModelProperty("洗车列数（下塘）")
    private Integer xtWashCount;
    @ApiModelProperty("调车件数/件（汀田）")
    private Integer ttShuntingCount;
    @ApiModelProperty("当日运行时刻表")
    private String schedule;
    @ApiModelProperty("发车间隔")
    private Integer departureInterval;
    @ApiModelProperty("计划开行列次")
    private Integer scheduleCount;
}
