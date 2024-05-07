package com.wzmtr.dom.dto.res.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IndicatorPowerResDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = " 记录ID")
    private String recordId;

    /**
     * 总能耗
     * */
    @ApiModelProperty(value = " 总能耗")
    private String energyConsumption;

    /**
     * 人民路主所能耗
     * */
    @ApiModelProperty(value = " 人民路主所能耗")
    private Integer rmlConsumption;

    /**
     * 下塘主所能耗
     * */
    @ApiModelProperty(value = " 下塘主所能耗")
    private Integer xtConsumption;

    /**
     * 灵昆主所能耗
     * */
    @ApiModelProperty(value = " 灵昆主所能耗")
    private Integer lkConsumption;

    /**
     * 汀田主所能耗
     * */
    @ApiModelProperty(value = " 汀田主所能耗")
    private Integer ttConsumption;

    /**
     * 运营期间牵引能耗
     * */
    @ApiModelProperty(value = " 运营期间牵引能耗")
    private Integer traction1Consumption;

    /**
     * 百车公里牵引能耗
     * */
    @ApiModelProperty(value = " 百车公里牵引能耗")
    private Integer traction2Consumption;

    /**
     * 牵引能耗
     * */
    @ApiModelProperty(value = " 牵引能耗")
    private Integer traction3Consumption;

    /**
     * 百车公里能耗
     * */
    @ApiModelProperty(value = " 百车公里能耗")
    private Integer vehicleConsumption;

    /**
     * 牵引能耗占比
     * */
    @ApiModelProperty(value = " 牵引能耗占比")
    private String tractionRate;

    /**
     * 照明能耗
     * */
    @ApiModelProperty(value = " 照明能耗")
    private Integer lightConsumption;

    /**
     * 照明能耗占比
     * */
    @ApiModelProperty(value = " 照明能耗占比")
    private String lightRate;

    /**
     * 备注
     * */
    @ApiModelProperty(value = " 备注")
    private String remark;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

}
