package com.wzmtr.dom.dto.req.traffic;

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
public class PassengerInfoReqDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * S2出站量
     * */
    @ApiModelProperty(value = "站点编码")
    private String stationCode;

    /**
     * 车站客流量
     * */
    @ApiModelProperty(value = "车站客流量")
    private Double passenger;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

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
