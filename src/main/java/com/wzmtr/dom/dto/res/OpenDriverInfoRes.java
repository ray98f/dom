package com.wzmtr.dom.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/5/24 16:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenDriverInfoRes {

    /**
     * 司机总人数
     * */
    private Integer driverNumber;

    /**
     * 当日应出勤人数
     * */
    private Integer shouldWorkNumber;

    /**
     * 当日实出勤人数
     * */
    private Integer didWorkNumber;

    /**
     * 当日司机驾驶公里数
     * */
    private String totalDistance;

    /**
     * 当日司机人均公里数
     * */
    private String averageDistance;

    /**
     * 司机长（白班）人员
     * */
    private String dayHeadDriver;

    /**
     * 司机长（夜班）人员
     * */
    private String nightHeadDriver;

    /**
     * 指导司机（白班）人员
     * */
    private String dayGuideDriver;

    /**
     * 指导司机（夜班）人员
     * */
    private String nightGuideDriver;

    /**
     * 事假人员
     * */
    private String personalLeave;

    /**
     * 病假人员
     * */
    private String sickLeave;

    /**
     * 年休人员
     * */
    private String annualLeave;

    /**
     * 其他假人员
     * */
    private String otherLeave;

    public Integer getDriverNumber() {
        return Optional.ofNullable(driverNumber).orElse(0);
    }

    public Integer getShouldWorkNumber() {
        return Optional.ofNullable(shouldWorkNumber).orElse(0);
    }

    public Integer getDidWorkNumber() {
        return Optional.ofNullable(didWorkNumber).orElse(0);
    }

    public String getTotalDistance() {
        return Optional.ofNullable(totalDistance).orElse("0.00");
    }

    public String getAverageDistance() {
        return Optional.ofNullable(averageDistance).orElse("0.00");
    }
}
