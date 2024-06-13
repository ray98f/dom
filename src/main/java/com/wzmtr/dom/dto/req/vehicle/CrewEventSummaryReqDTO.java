package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrewEventSummaryReqDTO extends BaseEntity {

    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    /**
     * 对标不准类事件数
     * */
    @ApiModelProperty(value = "对标不准类事件数")
    private Integer type1Count;

    /**
     * 误操作类事件数
     * */
    @ApiModelProperty(value = "误操作类事件数")
    private Integer type2Count;

    /**
     * 作业标准类事件数
     * */
    @ApiModelProperty(value = "作业标准类事件数")
    private Integer type3Count;

    /**
     * 其他非乘务原因类事件数
     * */
    @ApiModelProperty(value = "其他非乘务原因类事件数")
    private Integer type4Count;

    /**
     * 说明
     * */
    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "版本号")
    private String version;
}
