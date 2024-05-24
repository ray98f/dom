package com.wzmtr.dom.dto.res.traffic.hotline;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Li.Wang
 * Date: 2024/3/22 16:58
 */
@Data
public class HotLineSummaryDetailResDTO {
    /**
     * id
     */
    @TableId("ID")
    private String id;
    /**
     * 接听总量
     */
    @ApiModelProperty("接听总量")
    private Long answerTotal;
    /**
     * 咨询
     */
    @ApiModelProperty("咨询")
    private Long consult;
    /**
     * 求助
     */
    @ApiModelProperty("求助")
    private Long resort;
    /**
     * 建议
     */
    @ApiModelProperty("建议")
    private Long suggest;

    /**
     * app回复
     */
    @ApiModelProperty("app回复")
    private Long appAnswer;

    /**
     * 有责投诉
     */
    @ApiModelProperty("有责投诉")
    private Long type1Complaint;
    /**
     * 无责投诉
     */
    @ApiModelProperty("无责投诉")
    private Long type2Complaint;
    /**
     * 总投诉
     */
    @ApiModelProperty("总投诉")
    private Long complaintTotal;
    /**
     * 热线表扬
     */
    @ApiModelProperty("热线表扬")
    private Long type1Praise;
    /**
     * 车站表扬
     */
    @ApiModelProperty("车站表扬")
    private Long type2Praise;
    /**
     * 总表扬
     */
    @ApiModelProperty("总表扬")
    private Long praiseTotal;
    /**
     * 锦旗
     */
    @ApiModelProperty("锦旗")
    private Long pennant;
    /**
     * S1转接
     */
    @ApiModelProperty("S1转接")
    private Long s1Switch;

    /**
     * 其他
     */
    @ApiModelProperty("其他")
    private Long other;
    /**
     * 数据所属日期
     */
    @ApiModelProperty("数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date dataDate;
    /**
     * 数据类型 1日报 2周报 3月报
     */
    @ApiModelProperty("数据类型 1日报 2周报 3月报")
    private String dataType;

    /**
     * 版本号(乐观锁)
     */
    @ApiModelProperty("版本号(乐观锁)")
    private String version;
    /**
     * 说明
     */
    @ApiModelProperty("说明")
    private String remark;

    @ApiModelProperty("数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startDate;
    /**
     * 数据结束日期
     */
    @ApiModelProperty("数据结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;
}
