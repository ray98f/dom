package com.wzmtr.dom.dataobject.traffic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName TRAFFIC_HOTLINE_IMPORTANT
 */
@TableName("TRAFFIC_HOTLINE_IMPORTANT")
@Data
public class TrafficHotlineImportantDO implements Serializable {

    /**
     * ID
     */
    @TableId("ID")
    @ApiModelProperty("ID")
    private String id;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Long count;
    /**
     * 主要内容
     */
    @ApiModelProperty("主要内容")
    private String content;
    /**
     * 处理结果
     */
    @ApiModelProperty("处理结果")
    private String result;
    /**
     * 关键词
     */
    @ApiModelProperty("关键词")
    private String keyword;
    /**
     * 热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他
     */
    @ApiModelProperty("热线类型:1投诉,2表扬,3建议4咨询5求助6转接S1,7APP,8锦旗,9其他")
    private String hotlineType;
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
