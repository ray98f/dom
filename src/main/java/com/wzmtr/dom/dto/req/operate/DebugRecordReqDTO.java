package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 调试情况请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DebugRecordReqDTO extends BaseEntity {

    /**
     * 信号调试数量
     */
    @ApiModelProperty(value = "信号调试数量")
    private Integer signalNum;

    /**
     * 车辆调试数量
     */
    @ApiModelProperty(value = "车辆调试数量")
    private Integer vehicleNum;

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
    private Date dataDate;
}
