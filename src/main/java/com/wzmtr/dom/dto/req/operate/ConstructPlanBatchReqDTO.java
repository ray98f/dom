package com.wzmtr.dom.dto.req.operate;

import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.DepotConstructPlanReqDTO;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstructPlanBatchReqDTO extends BaseEntity {

    private List<ConstructPlanReqDTO> planList;

    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 车场编码
     */
    @ApiModelProperty(value = "车场编码")
    private String depotCode;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String dataType;

    /**
     * 数据所属日期
     * */
    private String dataDate;

    /**
     * 数据起始日期
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String startDate;

    /**
     * 数据终止日期
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String endDate;
}
