package com.wzmtr.dom.dto.req.traffic.hotline;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 17:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HotLineHandoverAddReqDTO extends BaseEntity {
    /**
     * 需转交件数
     */
    private Long handoverCount;
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
