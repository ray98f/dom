package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 车场调度员交接班情况结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Data
public class DispatchHandoverResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 设备故障情况
     */
    @ApiModelProperty(value = "设备故障情况")
    private String faultDesc;

    /**
     * 其他描述
     */
    @ApiModelProperty(value = "其他描述")
    private String otherDesc;

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
