package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 限速情况详情请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpeedLimitInfoReqDTO extends BaseEntity {

    /**
     * 限速记录id
     */
    @ApiModelProperty(value = "限速记录id")
    private String recordId;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer serialNo;

    /**
     * 限速原因
     */
    @ApiModelProperty(value = "限速原因")
    private String reason;

    /**
     * 限速开始时间
     */
    @ApiModelProperty(value = "限速开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date limitStartTime;

    /**
     * 限速结束时间
     */
    @ApiModelProperty(value = "限速结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date limitEndTime;

    /**
     * 限速区段
     */
    @ApiModelProperty(value = "限速区段")
    private String limitSection;

    /**
     * 限速值
     */
    @ApiModelProperty(value = "限速值")
    private String limitValue;

    /**
     * 申请单位id
     */
    @ApiModelProperty(value = "申请单位id")
    private String applicationDept;

    /**
     * 申请单位名称
     */
    @ApiModelProperty(value = "申请单位名称")
    private String applicationDeptName;

    /**
     * 责任单位/责任人
     */
    @ApiModelProperty(value = "责任单位/责任人")
    private String duty;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
