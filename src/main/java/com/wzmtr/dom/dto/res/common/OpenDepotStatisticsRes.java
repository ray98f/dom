package com.wzmtr.dom.dto.res.common;

import com.wzmtr.dom.constant.CommonConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Optional;

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

    public Integer getXtPlanDepartureCount() {
        return Optional.ofNullable(xtPlanDepartureCount).orElse(0);
    }

    public Integer getXtActualDepartureCount() {
        return Optional.ofNullable(xtActualDepartureCount).orElse(0);
    }

    public Integer getXtPlanArrivalCount() {
        return Optional.ofNullable(xtPlanArrivalCount).orElse(0);
    }

    public Integer getXtActualArrivalCount() {
        return Optional.ofNullable(xtActualArrivalCount).orElse(0);
    }

    public Integer getTtPlanDepartureCount() {
        return Optional.ofNullable(ttPlanDepartureCount).orElse(0);
    }

    public Integer getTtActualDepartureCount() {
        return Optional.ofNullable(ttActualDepartureCount).orElse(0);
    }

    public Integer getTtPlanArrivalCount() {
        return Optional.ofNullable(ttPlanArrivalCount).orElse(0);
    }

    public Integer getTtActualArrivalCount() {
        return Optional.ofNullable(ttActualArrivalCount).orElse(0);
    }

    public Integer getXtShuntingCount() {
        return Optional.ofNullable(xtShuntingCount).orElse(0);
    }

    public Integer getXtShuntingHookCount() {
        return Optional.ofNullable(xtShuntingHookCount).orElse(0);
    }

    public Integer getXtShuntingTime() {
        return Optional.ofNullable(xtShuntingTime).orElse(0);
    }

    public Integer getXtWashCount() {
        return Optional.ofNullable(xtWashCount).orElse(0);
    }

    public Integer getTtShuntingCount() {
        return Optional.ofNullable(ttShuntingCount).orElse(0);
    }

    public String getSchedule() {
        return Optional.ofNullable(schedule).orElse("");
    }

    public Integer getDepartureInterval() {
        return Optional.ofNullable(departureInterval).orElse(0);
    }

    public Integer getScheduleCount() {
        return Optional.ofNullable(scheduleCount).orElse(0);
    }
}
