package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日数据请求类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityReqDTO extends BaseEntity {

    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    /**
     * 概述
     * */
    @ApiModelProperty(value = "概述")
    private String remark;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

}
