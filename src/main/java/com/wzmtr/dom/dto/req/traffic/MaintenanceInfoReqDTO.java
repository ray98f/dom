package com.wzmtr.dom.dto.req.traffic;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MaintenanceInfoReqDTO extends BaseEntity {

    /**
     * 记录ID
     * */
    @ApiModelProperty(value = "记录ID")
    private String recordId;

    /**
     * 车站
     * */
    @ApiModelProperty(value = "车站")
    private String stationName;

    /**
     * 故障描述
     * */
    @ApiModelProperty(value = "故障描述")
    private String faultDesc;

    /**
     * 故障影响
     * */
    @ApiModelProperty(value = "故障影响")
    private String faultAffect;

    /**
     * 修复情况
     * */
    @ApiModelProperty(value = "修复情况")
    private String repairDesc;

    /**
     * 关键词
     * */
    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;
}
