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
public class MainWorkReqDTO extends BaseEntity {

    /**
     * 工作进展
     * */
    @ApiModelProperty(value = "工作进展")
    private String workDesc;

    /**
     * 存在问题
     * */
    @ApiModelProperty(value = "存在问题")
    private String problemDesc;

    /**
     * 下一步计划
     * */
    @ApiModelProperty(value = "下一步计划")
    private String nextPlan;

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
