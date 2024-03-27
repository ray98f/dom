package com.wzmtr.dom.dto.res.operate.passengerflow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/26 17:37
 */
@Data
public class PassengerFlowTopThreeListResDTO {
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 站点编码
     */
    @ApiModelProperty("站点编码")
    private String stationCode;
    /**
     * 车站名称
     */
    @ApiModelProperty(value = "车站名称")
    private String stationName;
    /**
     * 车站客流量
     */
    @ApiModelProperty("车站客流量")
    private Long passenger;

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
    @ApiModelProperty("创建者")
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


}
