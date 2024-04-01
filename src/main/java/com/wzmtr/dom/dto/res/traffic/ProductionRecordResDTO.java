package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionRecordResDTO extends BaseEntity {

    /**
     * 车站编码
     * */
    @ApiModelProperty(value = "车站编码")
    private String stationCode;

    /**
     * 车站名称
     * */
    @ApiModelProperty(value = "车站名称")
    private String stationName;

    /**
     * 施工异常:本日/周/月件数
     * */
    @ApiModelProperty(value = "施工异常:本日/周/月件数")
    private Integer type1Count;

    /**
     * 运检异常:本日/周/月件数
     * */
    @ApiModelProperty(value = "运检异常:本日/周/月件数")
    private Integer type2Count;

    /**
     * 行车异常:本日/周/月件数
     * */
    @ApiModelProperty(value = "行车异常:本日/周/月件数")
    private Integer type3Count;

    /**
     * 其他突发事件:本日/周/月件数
     * */
    @ApiModelProperty(value = "其他突发事件:本日/周/月件数")
    private Integer type4Count;

    /**
     * 其他事件:本日件数
     * */
    @ApiModelProperty(value = "其他事件:本日/周/月件数")
    private Integer type5Count;

    /**
     * 设备缺陷、故障:本日/周/月件数
     * */
    @ApiModelProperty(value = "设备缺陷、故障:本日/周/月件数")
    private Integer type6Count;

    /**
     * 状态
     * */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 编辑标记
     * */
    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    /**
     * 数据类型:1:日报,2周报,3月报
     * */
    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;
}
