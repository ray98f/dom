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
 * Date: 2024/3/27 15:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HandoverAddDataReqDTO extends BaseEntity {
    /**
     * 记录ID
     */
    @ApiModelProperty("记录ID")
    private String recordId;
    /**
     * 主要内容
     */
    @ApiModelProperty("主要内容")
    private String mainContent;
    /**
     * 来源
     */
    @ApiModelProperty("来源")
    private String source;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Long count;
    /**
     * 处理结果
     */
    @ApiModelProperty("处理结果")
    private String result;
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
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
}
