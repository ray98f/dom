package com.wzmtr.dom.dto.res.operate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 调试情况录结果类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@Data
public class DebugInfoResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 记录id
     */
    @ApiModelProperty(value = "记录id")
    private String recordId;

    /**
     * 外部id
     */
    @ApiModelProperty(value = "外部id")
    private String externalId;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer serialNo;

    /**
     * 数据类型 1 信号调试 2 车辆调试
     */
    @ApiModelProperty(value = "数据类型 1 信号调试 2 车辆调试")
    private String dataType;

    /**
     * 施工计划
     */
    @ApiModelProperty(value = "施工计划")
    private String csmPlan;

    /**
     * 限速开始时间
     */
    @ApiModelProperty(value = "限速开始时间")
    private String status;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;
}
