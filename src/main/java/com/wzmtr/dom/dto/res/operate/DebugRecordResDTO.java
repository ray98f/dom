package com.wzmtr.dom.dto.res.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 调试情况结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Data
public class DebugRecordResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

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
