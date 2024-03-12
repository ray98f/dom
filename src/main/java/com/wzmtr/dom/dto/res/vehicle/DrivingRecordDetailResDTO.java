package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/8 16:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DrivingRecordDetailResDTO extends BaseEntity {

    @ApiModelProperty(value = "车场情况")
    private List<DrivingDepotResDTO> depotList;

    @ApiModelProperty(value = "司机驾驶情况")
    private DrivingInfoResDTO driveInfo;

    @ApiModelProperty(value = "情况说明")
    private String remark;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;
}
