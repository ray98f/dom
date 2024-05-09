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

}
