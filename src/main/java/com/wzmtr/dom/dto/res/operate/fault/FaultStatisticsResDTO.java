package com.wzmtr.dom.dto.res.operate.fault;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 运营日报-故障统计记录
 *
 * @TableName OPERATE_FAULT_STATISTICS
 */
@Data
public class FaultStatisticsResDTO {
    /**
     *
     */
    @ApiModelProperty("")
    private String id;
    @ApiModelProperty(value = "新增时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createDate;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("更新日期")
    private Date updateDate;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private String updateBy;
    /**
     * 删除标记
     */
    @ApiModelProperty("删除标记")
    private String delFlag;
    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
     * 所属日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty("所属日期")
    private Date dataDate;
    /**
     * 车辆故障数量
     */
    @ApiModelProperty("车辆故障数量")
    private Integer vehicleNum;
    /**
     * 供电
     */
    @ApiModelProperty("供电")
    private Integer powerNum;
    /**
     * 信号
     */
    @ApiModelProperty("信号")
    private Integer signalNum;
    /**
     * 通信
     */
    @ApiModelProperty("通信")
    private Integer communicationNum;
    /**
     * 工建
     */
    @ApiModelProperty("工建")
    private Integer industryNum;
    /**
     * 机电
     */
    @ApiModelProperty("机电")
    private Integer mechanismNum;
    /**
     * AFC
     */
    @ApiModelProperty("AFC")
    private Integer afcNum;
    /**
     * 其他
     */
    @ApiModelProperty("其他")
    private Integer elseNum;

    @ApiModelProperty("总数")
    private Long sum;

    @ApiModelProperty(value = "数据所属类型")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    private String startDate;

    @ApiModelProperty(value = "数据终止日期")
    private String endDate;

}
