package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 调度命令记录请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DispatchRecordReqDTO extends BaseEntity {

    /**
     * 调度命令数
     */
    @ApiModelProperty(value = "调度命令数")
    private Integer orderNum;

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

    /**
     * 数据类型 1:日报 2:周报 3:月报
     */
    @ApiModelProperty(value = "数据类型 1:日报 2:周报 3:月报")
    private Integer dataType;

    /**
     * 数据起始日期
     */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据结束日期
     */
    @ApiModelProperty(value = "数据结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
