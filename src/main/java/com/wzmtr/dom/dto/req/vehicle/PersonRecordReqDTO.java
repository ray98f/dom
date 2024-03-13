package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 当日人员情况请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonRecordReqDTO extends BaseEntity {

    /**
     * 下塘场调(白)
     */
    @ApiModelProperty(value = "下塘场调(白)")
    private String dispatchXtDay;

    /**
     * 下塘场调(夜)
     */
    @ApiModelProperty(value = "下塘场调(夜)")
    private String dispatchXtNight;

    /**
     * 汀田场调(白)
     */
    @ApiModelProperty(value = "汀田场调(白)")
    private String dispatchTtDay;

    /**
     * 汀田场调(夜)
     */
    @ApiModelProperty(value = "汀田场调(夜)")
    private String dispatchTtNight;

    /**
     * 下塘DCC值班员(白)
     */
    @ApiModelProperty(value = "下塘DCC值班员(白)")
    private String dccXtDay;

    /**
     * 下塘DCC值班员(夜)
     */
    @ApiModelProperty(value = "下塘DCC值班员(夜)")
    private String dccXtNight;

    /**
     * 汀田DCC值班员(白)
     */
    @ApiModelProperty(value = "汀田DCC值班员(白)")
    private String dccTtDay;

    /**
     * 汀田DCC值班员(夜)
     */
    @ApiModelProperty(value = "汀田DCC值班员(夜)")
    private String dccTtNight;

    /**
     * 司机长(白)
     */
    @ApiModelProperty(value = "司机长(白)")
    private String mainDriverDay;

    /**
     * 司机长(夜)
     */
    @ApiModelProperty(value = "司机长(夜)")
    private String mainDriverNight;

    /**
     * 指导司机(白)
     */
    @ApiModelProperty(value = "指导司机(白)")
    private String guideDriverDay;

    /**
     * 指导司机(夜)
     */
    @ApiModelProperty(value = "指导司机(夜)")
    private String guideDriverNight;

    /**
     * 替班
     */
    @ApiModelProperty(value = "替班")
    private String relief;

    /**
     * 事假
     */
    @ApiModelProperty(value = "事假")
    private String leavePersonal;

    /**
     * 病假
     */
    @ApiModelProperty(value = "病假")
    private String leaveSick;

    /**
     * 年休
     */
    @ApiModelProperty(value = "年休")
    private String leaveAnnual;

    /**
     * 其他假
     */
    @ApiModelProperty(value = "其他假")
    private String leaveOther;

    /**
     * 待岗
     */
    @ApiModelProperty(value = "待岗")
    private String postWaiting;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;

    /**
     * 所属日期
     */
    @ApiModelProperty(value = "所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date belongDate;
}
