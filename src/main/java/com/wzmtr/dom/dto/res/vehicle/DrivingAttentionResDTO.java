package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 行车注意事项结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@Data
public class DrivingAttentionResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 正线注意事项
     */
    @ApiModelProperty(value = "正线注意事项")
    private String lineAttention;

    /**
     * 车场注意事项
     */
    @ApiModelProperty(value = "车场注意事项")
    private String depotAttention;

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
