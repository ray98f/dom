package com.wzmtr.dom.dto.res.operate.passengerflow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/26 17:37
 */
@Data
public class PassengerFlowListResDTO {
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 数据所属日期
     */
    @ApiModelProperty("数据所属日期")
    private Date dataDate;
    /**
     * 数据类型 1日报 2周报 3月报
     */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;
    /**
     * 数据起始日期
     */
    @ApiModelProperty("数据起始日期")
    private Date startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    private Date endDate;
    /**
     * 删除标识
     */
    @ApiModelProperty("删除标识")
    private String delFlag;
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private Date createDate;
    /**
     * 创建者
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("创建者")
    @Length(max = 255, message = "编码长度不能超过255")
    private String createBy;
    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    private Date updateDate;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private String updateBy;

    /**
     * 月累计客运量
     */
    @ApiModelProperty("月累计客运量")
    private Integer monthPass;

    /**
     * 本月日均客运量
     */
    @ApiModelProperty("本月日均客运量")
    private Integer dayAvgThisMonth;

    /**
     * 本日进站
     */
    private Integer todayInCount;
    /**
     * 本日出站
     */
    private Integer todayOutCount;

}
