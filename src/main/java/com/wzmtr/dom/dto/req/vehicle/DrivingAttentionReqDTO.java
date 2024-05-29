package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 行车注意事项请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DrivingAttentionReqDTO extends BaseEntity {

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
    private Date dataDate;
}
