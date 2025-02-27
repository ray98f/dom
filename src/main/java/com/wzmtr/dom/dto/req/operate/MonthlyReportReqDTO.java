package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 运营月报结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthlyReportReqDTO extends BaseEntity {

    /**
     * 选用客流数据 0否1是
     */
    @ApiModelProperty(value = "选用客流数据 0否1是")
    private String ifPassenger;

    /**
     * 选用能耗 0否1是
     */
    @ApiModelProperty(value = "选用能耗 0否1是")
    private String ifEnergy;

    /**
     * 选用事件及故障统计 0否1是
     */
    @ApiModelProperty(value = "选用事件及故障统计 0否1是")
    private String ifEventFault;

    /**
     * 选用施工 0否1是
     */
    @ApiModelProperty(value = "选用施工 0否1是")
    private String ifConstruct;

    /**
     * 选用热线 0否1是
     */
    @ApiModelProperty(value = "选用热线 0否1是")
    private String ifHotline;

    /**
     * 选用安检保洁 0否1是
     */
    @ApiModelProperty(value = "选用安检保洁 0否1是")
    private String ifSecurityClean;

    /**
     * 选用列车运营指标 0否1是
     */
    @ApiModelProperty(value = "选用列车运营指标 0否1是")
    private String ifIndicator;

    /**
     * 周报起始日期
     */
    @ApiModelProperty(value = "周报起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 周报终止日期
     */
    @ApiModelProperty(value = "周报终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 周报状态
     */
    @ApiModelProperty(value = "周报状态")
    private String status;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
