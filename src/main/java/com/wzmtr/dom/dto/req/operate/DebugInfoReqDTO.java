package com.wzmtr.dom.dto.req.operate;

import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 调试情况录请求类
 * @author  Ray
 * @version 1.0
 * @date 2024/04/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DebugInfoReqDTO extends BaseEntity {

    /**
     * 记录id
     */
    @ApiModelProperty(value = "记录id")
    private String recordId;

    /**
     * 数据类型 1 信号调试 2 车辆调试
     */
    @ApiModelProperty(value = "数据类型 1 信号调试 2 车辆调试")
    private String dataType;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty(value = "版本号(乐观锁)")
    private String version;

    /**
     * 施工计划列表
     */
    @ApiModelProperty(value = "施工计划列表")
    private List<DebugCsm> csmList;


    /**
     * 施工计划
     */
    @Data
    public static class DebugCsm {
        /**
         * 外部id
         */
        @ApiModelProperty(value = "外部id")
        private String constructPlanId;

        /**
         * 序号
         */
        @ApiModelProperty(value = "序号")
        private Integer serialNo;

        /**
         * 施工计划
         */
        @ApiModelProperty(value = "施工计划")
        private String workName;

        /**
         * 限速开始时间
         */
        @ApiModelProperty(value = "状态")
        private String planStatus;
    }
}
