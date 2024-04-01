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
public class ProductionApprovalReqDTO extends BaseEntity {

    /**
     * 安全生产记录ID
     * */
    @ApiModelProperty(value = "安全生产记录ID")
    private String recordId;

    /**
     * 审核操作:1驳回,2通过
     * */
    @ApiModelProperty(value = "审核操作:1驳回,2通过")
    private String approvalStatus;

    /**
     * 已提交站点
     * */
    @ApiModelProperty(value = "已提交站点")
    private String submitStation;

    /**
     * 标题
     * */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 审核站
     * */
    @ApiModelProperty(value = "审核站")
    private String approvalStation;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @ApiModelProperty(value = "数据类型:1日报 2月报 3周报")
    private String dataType;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    private String dataDate;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;
}
