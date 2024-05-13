package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanStatisticsReqDTO {

    private String dataType;

    private String startDate;

    private String endDate;

    private Integer plan1Count;

    private Integer plan2Count;

    private Integer plan3Count;

    private Integer real1Count;

    private Integer real2Count;

    private Integer real3Count;

    private Integer totalCount;
    private Integer realCount;

    private Integer linePlan1Count;
    private Integer linePlan2Count;
    private Integer linePlan3Count;

    private Integer lineCanceledCount;
    private Integer lineCanceledCount1;
    private Integer lineCanceledCount2;
    private Integer lineCanceledCount3;

    
    private Integer lineExpiredCount;
    private Integer lineExpiredCount1;
    private Integer lineExpiredCount2;
    private Integer lineExpiredCount3;

    
    private Integer lineChangedCount;
    private Integer lineChangedCount1;
    private Integer lineChangedCount2;
    private Integer lineChangedCount3;

    
    private Integer lineAddCount;
    private Integer lineAddCount1;
    private Integer lineAddCount2;
    private Integer lineAddCount3;

    
    private Integer lineReal1Count;
    private Integer lineReal2Count;
    private Integer lineReal3Count;

    
    private Integer lineCancledByair;

    
    private Integer lineDelayCount;
    private Integer lineDelayCount1;
    private Integer lineDelayCount2;
    private Integer lineDelayCount3;
    
    private Integer depotPlan1Count;
    private Integer depotPlan2Count;
    private Integer depotPlan3Count;

    private Integer depotCanceledCount;
    private Integer depotCanceledCount1;
    private Integer depotCanceledCount2;
    private Integer depotCanceledCount3;

    private Integer depotExpiredCount;
    private Integer depotExpiredCount1;
    private Integer depotExpiredCount2;
    private Integer depotExpiredCount3;

    private Integer depotChangedCount;
    private Integer depotChangedCount1;
    private Integer depotChangedCount2;
    private Integer depotChangedCount3;
    
    private Integer depotAddCount;
    private Integer depotAddCount1;
    private Integer depotAddCount2;
    private Integer depotAddCount3;
    
    private Integer depotReal1Count;
    private Integer depotReal2Count;
    private Integer depotReal3Count;

    
    private Integer depotCancledByair;

    
    private Integer depotDelayCount;
    private Integer depotDelayCount1;
    private Integer depotDelayCount2;
    private Integer depotDelayCount3;

    public Integer getPlan1Count() {
        return Optional.ofNullable(plan1Count).orElse(0);
    }

    public Integer getPlan2Count() {
        return Optional.ofNullable(plan2Count).orElse(0);
    }

    public Integer getPlan3Count() {
        return Optional.ofNullable(plan3Count).orElse(0);
    }

    public Integer getReal1Count() {
        return Optional.ofNullable(real1Count).orElse(0);
    }

    public Integer getReal2Count() {
        return Optional.ofNullable(real2Count).orElse(0);
    }

    public Integer getReal3Count() {
        return Optional.ofNullable(real3Count).orElse(0);
    }

    public Integer getTotalCount() {
        return Optional.ofNullable(totalCount).orElse(0);
    }

    public Integer getRealCount() {
        return Optional.ofNullable(realCount).orElse(0);
    }

    public Integer getLinePlan1Count() {
        return Optional.ofNullable(linePlan1Count).orElse(0);
    }

    public Integer getLinePlan2Count() {
        return Optional.ofNullable(linePlan2Count).orElse(0);
    }

    public Integer getLinePlan3Count() {
        return Optional.ofNullable(linePlan3Count).orElse(0);
    }

    public Integer getLineCanceledCount() {
        return Optional.ofNullable(lineCanceledCount).orElse(0);
    }

    public Integer getLineCanceledCount1() {
        return Optional.ofNullable(lineCanceledCount1).orElse(0);
    }

    public Integer getLineCanceledCount2() {
        return Optional.ofNullable(lineCanceledCount2).orElse(0);
    }

    public Integer getLineCanceledCount3() {
        return Optional.ofNullable(lineCanceledCount3).orElse(0);
    }

    public Integer getLineExpiredCount() {
        return Optional.ofNullable(lineExpiredCount).orElse(0);
    }

    public Integer getLineExpiredCount1() {
        return Optional.ofNullable(lineExpiredCount1).orElse(0);
    }

    public Integer getLineExpiredCount2() {
        return Optional.ofNullable(lineExpiredCount2).orElse(0);
    }

    public Integer getLineExpiredCount3() {
        return Optional.ofNullable(lineExpiredCount3).orElse(0);
    }

    public Integer getLineChangedCount() {
        return Optional.ofNullable(lineChangedCount).orElse(0);
    }

    public Integer getLineChangedCount1() {
        return Optional.ofNullable(lineChangedCount1).orElse(0);
    }

    public Integer getLineChangedCount2() {
        return Optional.ofNullable(lineChangedCount2).orElse(0);
    }

    public Integer getLineChangedCount3() {
        return Optional.ofNullable(lineChangedCount3).orElse(0);
    }

    public Integer getLineAddCount() {
        return Optional.ofNullable(lineAddCount).orElse(0);
    }

    public Integer getLineAddCount1() {
        return Optional.ofNullable(lineAddCount1).orElse(0);
    }

    public Integer getLineAddCount2() {
        return Optional.ofNullable(lineAddCount2).orElse(0);
    }

    public Integer getLineAddCount3() {
        return Optional.ofNullable(lineAddCount3).orElse(0);
    }

    public Integer getLineReal1Count() {
        return Optional.ofNullable(lineReal1Count).orElse(0);
    }

    public Integer getLineReal2Count() {
        return Optional.ofNullable(lineReal2Count).orElse(0);
    }

    public Integer getLineReal3Count() {
        return Optional.ofNullable(lineReal3Count).orElse(0);
    }

    public Integer getLineCancledByair() {
        return Optional.ofNullable(lineCancledByair).orElse(0);
    }

    public Integer getLineDelayCount() {
        return Optional.ofNullable(lineDelayCount).orElse(0);
    }

    public Integer getLineDelayCount1() {
        return Optional.ofNullable(lineDelayCount1).orElse(0);
    }

    public Integer getLineDelayCount2() {
        return Optional.ofNullable(lineDelayCount2).orElse(0);
    }

    public Integer getLineDelayCount3() {
        return Optional.ofNullable(lineDelayCount3).orElse(0);
    }

    public Integer getDepotPlan1Count() {
        return Optional.ofNullable(depotPlan1Count).orElse(0);
    }

    public Integer getDepotPlan2Count() {
        return Optional.ofNullable(depotPlan2Count).orElse(0);
    }

    public Integer getDepotPlan3Count() {
        return Optional.ofNullable(depotPlan3Count).orElse(0);
    }

    public Integer getDepotCanceledCount() {
        return Optional.ofNullable(depotCanceledCount).orElse(0);
    }

    public Integer getDepotCanceledCount1() {
        return Optional.ofNullable(depotCanceledCount1).orElse(0);
    }

    public Integer getDepotCanceledCount2() {
        return Optional.ofNullable(depotCanceledCount2).orElse(0);
    }

    public Integer getDepotCanceledCount3() {
        return Optional.ofNullable(depotCanceledCount3).orElse(0);
    }

    public Integer getDepotExpiredCount() {
        return Optional.ofNullable(depotExpiredCount).orElse(0);
    }

    public Integer getDepotExpiredCount1() {
        return Optional.ofNullable(depotExpiredCount1).orElse(0);
    }

    public Integer getDepotExpiredCount2() {
        return Optional.ofNullable(depotExpiredCount2).orElse(0);
    }

    public Integer getDepotExpiredCount3() {
        return Optional.ofNullable(depotExpiredCount3).orElse(0);
    }

    public Integer getDepotChangedCount() {
        return Optional.ofNullable(depotChangedCount).orElse(0);
    }

    public Integer getDepotChangedCount1() {
        return Optional.ofNullable(depotChangedCount1).orElse(0);
    }

    public Integer getDepotChangedCount2() {
        return Optional.ofNullable(depotChangedCount2).orElse(0);
    }

    public Integer getDepotChangedCount3() {
        return Optional.ofNullable(depotChangedCount3).orElse(0);
    }

    public Integer getDepotAddCount() {
        return Optional.ofNullable(depotAddCount).orElse(0);
    }

    public Integer getDepotAddCount1() {
        return Optional.ofNullable(depotAddCount1).orElse(0);
    }

    public Integer getDepotAddCount2() {
        return Optional.ofNullable(depotAddCount2).orElse(0);
    }

    public Integer getDepotAddCount3() {
        return Optional.ofNullable(depotAddCount3).orElse(0);
    }

    public Integer getDepotReal1Count() {
        return Optional.ofNullable(depotReal1Count).orElse(0);
    }

    public Integer getDepotReal2Count() {
        return Optional.ofNullable(depotReal2Count).orElse(0);
    }

    public Integer getDepotReal3Count() {
        return Optional.ofNullable(depotReal3Count).orElse(0);
    }

    public Integer getDepotCancledByair() {
        return Optional.ofNullable(depotCancledByair).orElse(0);
    }

    public Integer getDepotDelayCount() {
        return Optional.ofNullable(depotDelayCount).orElse(0);
    }

    public Integer getDepotDelayCount1() {
        return Optional.ofNullable(depotDelayCount1).orElse(0);
    }

    public Integer getDepotDelayCount2() {
        return Optional.ofNullable(depotDelayCount2).orElse(0);
    }

    public Integer getDepotDelayCount3() {
        return Optional.ofNullable(depotDelayCount3).orElse(0);
    }
}
