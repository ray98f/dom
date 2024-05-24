package com.wzmtr.dom.dto.req.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Li.Wang
 * Date: 2024/3/21 14:46
 */
@Data
public class SidReqDTO {
    @ApiModelProperty("id")
    private String id;
}
