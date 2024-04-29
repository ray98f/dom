package com.wzmtr.dom.dto.req.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 安检及保洁情况请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityCleaningReqDTO extends BaseEntity {

    /**
     * 安检情况
     */
    @ApiModelProperty(value = "安检情况")
    private String securityDesc;

    /**
     * 保洁情况
     */
    @ApiModelProperty(value = "保洁情况")
    private String cleaningDesc;

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

    /**
     * 数据类型 1:日报 2:周报 3:月报
     */
    @ApiModelProperty(value = "数据类型 1:日报 2:周报 3:月报")
    private String dataType;

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
}
