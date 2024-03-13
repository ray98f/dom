package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 10:14
 */
@Data
public class DepotConstructRecordResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 车场编码
     */
    @ApiModelProperty(value = "车场编码")
    private String depotCode;

    /**
     * 计划施工数
     */
    @ApiModelProperty(value = "计划施工数")
    private Integer planConstruct;

    /**
     * 实际施工数
     */
    @ApiModelProperty(value = "实际施工数")
    private Integer realConstruct;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 编辑标记
     * */
    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

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

}
