package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 调度命令详情结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@Data
public class DispatchOrderResDTO {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 发令时间
     */
    @ApiModelProperty(value = "发令时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date orderTime;

    /**
     * 发令号码
     */
    @ApiModelProperty(value = "发令号码")
    private String orderCode;

    /**
     * 命令内容
     */
    @ApiModelProperty(value = "命令内容")
    private String orderDesc;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
