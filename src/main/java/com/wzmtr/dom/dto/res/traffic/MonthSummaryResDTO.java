package com.wzmtr.dom.dto.res.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/18 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthSummaryResDTO extends BaseEntity {

    /**
     * 施工异常:前日/周/月件数
     * */
    @ApiModelProperty(value = "施工异常:前日/周/月件数")
    private Integer type1PreCount;

    /**
     * 施工异常:本日件数
     * */
    @ApiModelProperty(value = "施工异常:本日/周/月件数")
    private Integer type1Count;

    /**
     * 施工异常:概述
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type1Desc;

    /**
     * 施工异常:前日/周/月件数
     * */
    @ApiModelProperty(value = "施工异常:前日/周/月件数")
    private Integer type2PreCount;

    /**
     * 运检异常:本日件数
     * */
    @ApiModelProperty(value = "运检异常:本日/周/月件数")
    private Integer type2Count;

    /**
     * 运检异常:概述
     * */
    @ApiModelProperty(value = "运检异常:概述")
    private List<String> type2Desc;

    /**
     * 行车异常:前日/周/月件数
     * */
    @ApiModelProperty(value = "行车异常:前日/周/月件数")
    private Integer type3PreCount;

    /**
     * 行车异常:本日件数
     * */
    @ApiModelProperty(value = "行车异常:本日/周/月件数")
    private Integer type3Count;

    /**
     * 行车异常:概述
     * */
    @ApiModelProperty(value = "行车异常:概述")
    private List<String> type3Desc;

    /**
     * 其他突发事件:前日/周/月件数
     * */
    @ApiModelProperty(value = "其他突发事件:前日/周/月/周/月件数")
    private Integer type4PreCount;

    /**
     * 其他突发事件:本日件数
     * */
    @ApiModelProperty(value = "其他突发事件:本日/周/月件数")
    private Integer type4Count;

    /**
     * 其他突发事件:概述
     * */
    @ApiModelProperty(value = "其他突发事件:概述")
    private List<String> type4Desc;

    /**
     * 其他事件:前日/周/月件数
     * */
    @ApiModelProperty(value = "其他事件:前日/周/月件数")
    private Integer type5PreCount;

    /**
     * 其他事件:本日件数
     * */
    @ApiModelProperty(value = "其他事件:本日/周/月件数")
    private Integer type5Count;

    /**
     * 其他事件:概述
     * */
    @ApiModelProperty(value = "其他事件:概述")
    private List<String> type5Desc;

    /**
     * 其他事件:前日/周/月件数
     * */
    @ApiModelProperty(value = "其他事件:前日/周/月件数")
    private Integer type6PreCount;

    /**
     * 设备缺陷、故障:本日/周/月件数
     * */
    @ApiModelProperty(value = "设备缺陷、故障:本日/周/月件数")
    private Integer type6Count;

    /**
     * 设备缺陷、故障:概述
     * */
    @ApiModelProperty(value = "设备缺陷、故障:概述")
    private List<String> type6Desc;


    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:关键词")
    private List<String> type1Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type2Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type3Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type4Keyword;

    /**
     * 施工异常:关键词
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type5Keyword;
    /**
     * 设备缺陷、故障:  关键词
     * */
    @ApiModelProperty(value = "设备缺陷、故障:键词")
    private List<String> type6Keyword;

    public List<String> getType1Desc() {
        return type1Desc;
    }

    public List<String> getType2Desc() {
        return type2Desc;
    }

    public List<String> getType3Desc() {
        return type3Desc;
    }

    public List<String> getType4Desc() {
        return type4Desc;
    }

    public List<String> getType5Desc() {
        return type5Desc;
    }

    public List<String> getType6Desc() {
        return type6Desc;
    }

    public List<String> getType1Keyword() {
        return type1Keyword;
    }

    public List<String> getType2Keyword() {
        return type2Keyword;
    }

    public List<String> getType3Keyword() {
        return type3Keyword;
    }

    public List<String> getType4Keyword() {
        return type4Keyword;
    }

    public List<String> getType5Keyword() {
        return type5Keyword;
    }

    public List<String> getType6Keyword() {
        return Optional.ofNullable(type6Keyword).orElse(new ArrayList<>());
    }
}
