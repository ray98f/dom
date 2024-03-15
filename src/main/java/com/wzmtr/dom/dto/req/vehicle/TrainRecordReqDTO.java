package com.wzmtr.dom.dto.req.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 班组培训情况请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TrainRecordReqDTO extends BaseEntity {

    /**
     * 一组
     */
    @ApiModelProperty(value = "一组")
    private String groupOne;

    /**
     * 二组
     */
    @ApiModelProperty(value = "二组")
    private String groupTwo;

    /**
     * 三组
     */
    @ApiModelProperty(value = "三组")
    private String groupThree;

    /**
     * 四组
     */
    @ApiModelProperty(value = "四组")
    private String groupFour;

    /**
     * 车场组
     */
    @ApiModelProperty(value = "车场组")
    private String groupPark;

    /**
     * 脱产培训司机
     */
    @ApiModelProperty(value = "脱产培训司机")
    private String trainDriver;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
}
