package com.wzmtr.dom.dataobject.traffic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * 客运部-服务热线汇总
 *
 * @TableName TRAFFIC_HOTLINE_SUMMARY
 */
@Data
@TableName("TRAFFIC_HOTLINE_SUMMARY")
public class TrafficHotlineSummaryDO implements Serializable {

    /**
     * id
     */
    @TableId("ID")
    private String id;
    /**
     * 总投诉
     */
    @ApiModelProperty("总投诉")
    private Long complaintTotal;
    /**
     * 建议
     */
    @ApiModelProperty("建议")
    private Long suggest;
    /**
     * 总表扬
     */
    @ApiModelProperty("总表扬")
    private Long praiseTotal;
    /**
     * 接听总量
     */
    @ApiModelProperty("接听总量")
    private Long answerTotal;
    /**
     * app回复
     */
    @ApiModelProperty("app回复")
    private Long appAnswer;
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
     * 数据起始日期
     */
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
    /**
     * 说明
     */
    @ApiModelProperty("说明")
    private String remark;


    public Long getComplaintTotal() {
        return Optional.ofNullable(complaintTotal).orElse(0L);
    }

    public Long getSuggest() {
        return Optional.ofNullable(suggest).orElse(0L);
    }

    public Long getPraiseTotal() {
        return Optional.ofNullable(praiseTotal).orElse(0L);
    }

    public Long getAnswerTotal() {
        return Optional.ofNullable(answerTotal).orElse(0L);
    }

    public Long getAppAnswer() {
        return Optional.ofNullable(appAnswer).orElse(0L);
    }

    public Long getConsult() {
        return Optional.ofNullable(consult).orElse(0L);
    }

    public Long getResort() {
        return Optional.ofNullable(resort).orElse(0L);
    }

    public Long getType1Complaint() {
        return Optional.ofNullable(type1Complaint).orElse(0L);
    }

    public Long getType2Complaint() {
        return Optional.ofNullable(type2Complaint).orElse(0L);
    }

    public Long getType1Praise() {
        return Optional.ofNullable(type1Praise).orElse(0L);
    }

    public Long getType2Praise() {
        return Optional.ofNullable(type2Praise).orElse(0L);
    }

    public Long getPennant() {
        return Optional.ofNullable(pennant).orElse(0L);
    }

    public Long getS1Switch() {
        return Optional.ofNullable(s1Switch).orElse(0L);
    }

    public Long getOther() {
        return Optional.ofNullable(other).orElse(0L);
    }
}
