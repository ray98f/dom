package com.wzmtr.dom.dto.res.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 日数据结果类
 * @author  zhangxin
 * @version 1.0
 * @date 2024/3/12 08:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstructRecordResDTO extends BaseEntity {

    /**
     * 周施工计划数
     * */
    @ApiModelProperty(value = " 周施工计划数")
    private Integer plan1Count;

    /**
     * 日补充施工计划数
     * */
    @ApiModelProperty(value = " 日补充施工计划数")
    private Integer plan2Count;

    /**
     * 临时施工计划数
     * */
    @ApiModelProperty(value = " 临时施工计划数")
    private Integer plan3Count;

    /**
     * 周施工计划实际完成数
     * */
    @ApiModelProperty(value = " 周施工计划实际完成数")
    private Integer real1Count;

    /**
     * 日补充施工计划实际完成数
     * */
    @ApiModelProperty(value = " 日补充施工计划实际完成数")
    private Integer real2Count;

    /**
     * 临时施工计划实际完成数
     * */
    @ApiModelProperty(value = " 临时施工计划实际完成数")
    private Integer real3Count;

    /**
     * 施工计划总数
     * */
    @ApiModelProperty(value = " 施工计划总数")
    private Integer totalCount;

    /**
     * 紧急维修数
     * */
    @ApiModelProperty(value = " 紧急维修数")
    private Integer realCount;

    /**
     * 紧急维修数
     * */
    @ApiModelProperty(value = " 紧急维修数")
    private Integer emergencyRepair;

    /**
     * 抢修数
     * */
    @ApiModelProperty(value = " 抢修数")
    private Integer rushRepair;

    /**
     * 施工计划兑现率
     * */
    @ApiModelProperty(value = "施工计划兑现率")
    private String fulfillmentRate;

    /**
     * 施工计划准确率
     * */
    @ApiModelProperty(value = "施工计划准确率")
    private String accuracy;

    /**
     * 施工概况
     * */
    @ApiModelProperty(value = "施工概况")
    private String remark;

    /**
     * 施工问题描述：异物遗留、侵限
     * */
    @ApiModelProperty(value = "施工问题描述：异物遗留、侵限")
    private String problem1;

    /**
     * 施工问题描述：晚请点、延时销点
     * */
    @ApiModelProperty(value = "施工问题描述：晚请点、延时销点")
    private String problem2;

    /**
     * 施工问题描述：违规施工
     * */
    @ApiModelProperty(value = "施工问题描述：违规施工")
    private String problem3;

    /**
     * 施工问题描述：擅自取消施工
     * */
    @ApiModelProperty(value = "施工问题描述：擅自取消施工")
    private String problem4;

    /**
     * 施工问题描述：擅自施工
     * */
    @ApiModelProperty(value = "施工问题描述：擅自施工")
    private String problem5;

    /**
     * 施工总结
     * */
    @ApiModelProperty(value = "施工总结")
    private String summarize;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "编辑标记")
    private String editFlag;

    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 不饱和施工列表
     * */
    private List<UnsaturationConstructResDTO> unsaturationConstruct;
}
