package com.wzmtr.dom.dto.res.operate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@NoArgsConstructor
@Data
public class PlanStatisticsResDTO {

    @ApiModelProperty("准确率")
    private String accuracyrate;
    @ApiModelProperty("兑现率")
    private String cash;

    @ApiModelProperty("计划类型")
    private String planType;
    @ApiModelProperty("计划件数")
    private Integer totalnum;
    @ApiModelProperty("计划件数A")
    private Integer totalnumA;
    @ApiModelProperty("计划件数B")
    private Integer totalnumB;
    @ApiModelProperty("计划件数C")
    private Integer totalnumC;
    @ApiModelProperty("计划取消件数")
    private Integer cancelednum;
    @ApiModelProperty("计划取消件数A")
    private Integer cancelednumA;
    @ApiModelProperty("计划取消件数B")
    private Integer cancelednumB;
    @ApiModelProperty("计划取消件数C")
    private Integer cancelednumC;
    @ApiModelProperty("实际完成件数")
    private Integer finishednum;
    @ApiModelProperty("实际完成件数A")
    private Integer finishednumA;
    @ApiModelProperty("实际完成件数B")
    private Integer finishednumB;
    @ApiModelProperty("实际完成件数C")
    private Integer finishednumC;
    @ApiModelProperty("擅自取消")
    private Integer expirednum;
    @ApiModelProperty("擅自取消A")
    private Integer expirednumA;
    @ApiModelProperty("擅自取消B")
    private Integer expirednumB;
    @ApiModelProperty("擅自取消C")
    private Integer expirednumC;
    @ApiModelProperty("变更数")
    private Integer changednum;
    @ApiModelProperty("变更数A")
    private Integer changednumA;
    @ApiModelProperty("变更数B")
    private Integer changednumB;
    @ApiModelProperty("变更数C")
    private Integer changednumC;
    @ApiModelProperty("延点件数")
    private Integer delaynum;
    @ApiModelProperty("延点件数A")
    private Integer delaynumA;
    @ApiModelProperty("延点件数B")
    private Integer delaynumB;
    @ApiModelProperty("延点件数C")
    private Integer delaynumC;
    private String constructplanId;

    public Integer getTotalnum() {
        return Optional.ofNullable(totalnum).orElse(0);
    }

    public Integer getTotalnumA() {
        return Optional.ofNullable(totalnumA).orElse(0);
    }

    public Integer getTotalnumB() {
        return Optional.ofNullable(totalnumB).orElse(0);
    }
    public Integer getTotalnumC() {

        return Optional.ofNullable(totalnumC).orElse(0);
    }

    public Integer getCancelednum() {
        return Optional.ofNullable(cancelednum).orElse(0);
    }

    public Integer getCancelednumA() {
        return Optional.ofNullable(cancelednumA).orElse(0);
    }

    public Integer getCancelednumB() {
        return Optional.ofNullable(cancelednumB).orElse(0);
    }

    public Integer getCancelednumC() {
        return Optional.ofNullable(cancelednumC).orElse(0);
    }

    public Integer getFinishednum() {
        return Optional.ofNullable(finishednum).orElse(0);
    }

    public Integer getFinishednumA() {
        return Optional.ofNullable(finishednumA).orElse(0);
    }

    public Integer getFinishednumB() {
        return Optional.ofNullable(finishednumB).orElse(0);
    }

    public Integer getFinishednumC() {
        return Optional.ofNullable(finishednumC).orElse(0);
    }

    public Integer getExpirednum() {
        return Optional.ofNullable(expirednum).orElse(0);
    }

    public Integer getExpirednumA() {
        return Optional.ofNullable(expirednumA).orElse(0);
    }

    public Integer getExpirednumB() {
        return Optional.ofNullable(expirednumB).orElse(0);
    }

    public Integer getExpirednumC() {
        return Optional.ofNullable(expirednumC).orElse(0);
    }

    public Integer getChangednum() {
        return Optional.ofNullable(changednum).orElse(0);
    }

    public Integer getChangednumA() {
        return Optional.ofNullable(changednumA).orElse(0);
    }

    public Integer getChangednumB() {
        return Optional.ofNullable(changednumB).orElse(0);
    }

    public Integer getChangednumC() {
        return Optional.ofNullable(changednumC).orElse(0);
    }

    public Integer getDelaynum() {
        return Optional.ofNullable(delaynum).orElse(0);
    }

    public Integer getDelaynumA() {
        return Optional.ofNullable(delaynumA).orElse(0);
    }

    public Integer getDelaynumB() {
        return Optional.ofNullable(delaynumB).orElse(0);
    }

    public Integer getDelaynumC() {
        return Optional.ofNullable(delaynumC).orElse(0);
    }
}
