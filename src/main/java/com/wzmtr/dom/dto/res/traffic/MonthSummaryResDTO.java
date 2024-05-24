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
     * 施工异常:前月件数
     * */
    @ApiModelProperty(value = "施工异常:前日/周/月件数")
    private Integer type1PreCount;

    /**
     * 施工异常:本月件数
     * */
    @ApiModelProperty(value = "施工异常:本日/周/月件数")
    private Integer type1Count;

    /**
     * 施工异常:概述列表
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type1Desc;

    /**
     * 运检异常:前月件数
     * */
    @ApiModelProperty(value = "运检异常:前日/周/月件数")
    private Integer type2PreCount;

    /**
     * 运检异常:本月件数
     * */
    @ApiModelProperty(value = "运检异常:本日/周/月件数")
    private Integer type2Count;

    /**
     * 运检异常:概述列表
     * */
    @ApiModelProperty(value = "运检异常:概述")
    private List<String> type2Desc;

    /**
     * 行车异常:前月件数
     * */
    @ApiModelProperty(value = "行车异常:前日/周/月件数")
    private Integer type3PreCount;

    /**
     * 行车异常:本月件数
     * */
    @ApiModelProperty(value = "行车异常:本日/周/月件数")
    private Integer type3Count;

    /**
     * 行车异常:概述列表
     * */
    @ApiModelProperty(value = "行车异常:概述")
    private List<String> type3Desc;

    /**
     * 其他突发事件:前月件数
     * */
    @ApiModelProperty(value = "其他突发事件:前日/周/月/周/月件数")
    private Integer type4PreCount;

    /**
     * 其他突发事件:本月件数
     * */
    @ApiModelProperty(value = "其他突发事件:本日/周/月件数")
    private Integer type4Count;

    /**
     * 其他突发事件:概述
     * */
    @ApiModelProperty(value = "其他突发事件:概述")
    private List<String> type4Desc;

    /**
     * 其他事件:前月件数
     * */
    @ApiModelProperty(value = "其他事件:前日/周/月件数")
    private Integer type5PreCount;

    /**
     * 其他事件:本月件数
     * */
    @ApiModelProperty(value = "其他事件:本日/周/月件数")
    private Integer type5Count;

    /**
     * 其他事件:概述列表
     * */
    @ApiModelProperty(value = "其他事件:概述")
    private List<String> type5Desc;

    /**
     * 设备缺陷、故障:前月件数
     * */
    @ApiModelProperty(value = "其他事件:前日/周/月件数")
    private Integer type6PreCount;

    /**
     * 设备缺陷、故障:本月件数
     * */
    @ApiModelProperty(value = "设备缺陷、故障:本日/周/月件数")
    private Integer type6Count;

    /**
     * 设备缺陷、故障:概述列表
     * */
    @ApiModelProperty(value = "设备缺陷、故障:概述")
    private List<String> type6Desc;


    /**
     * 施工异常:关键词列表
     * */
    @ApiModelProperty(value = "施工异常:关键词")
    private List<String> type1Keyword;

    /**
     * 运检异常:关键词列表
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type2Keyword;

    /**
     * 行车异常:关键词列表
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type3Keyword;

    /**
     * 其他突发事件:关键词列表
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type4Keyword;

    /**
     * 其他事件:关键词列表
     * */
    @ApiModelProperty(value = "施工异常:概述")
    private List<String> type5Keyword;
    /**
     * 设备缺陷、故障:  关键词列表
     * */
    @ApiModelProperty(value = "设备缺陷、故障:键词")
    private List<String> type6Keyword;

    public List<String> getType1Desc() {
        return Optional.ofNullable(type1Desc).orElse(new ArrayList<>());
    }

    public List<String> getType2Desc() {
        return Optional.ofNullable(type2Desc).orElse(new ArrayList<>());
    }

    public List<String> getType3Desc() {
        return Optional.ofNullable(type3Desc).orElse(new ArrayList<>());
    }

    public List<String> getType4Desc() {
        return Optional.ofNullable(type4Desc).orElse(new ArrayList<>());
    }

    public List<String> getType5Desc() {
        return Optional.ofNullable(type5Desc).orElse(new ArrayList<>());
    }

    public List<String> getType6Desc() {
        return Optional.ofNullable(type6Desc).orElse(new ArrayList<>());
    }

    public List<String> getType1Keyword() {
        return Optional.ofNullable(type1Keyword).orElse(new ArrayList<>());
    }

    public List<String> getType2Keyword() {
        return Optional.ofNullable(type2Keyword).orElse(new ArrayList<>());
    }

    public List<String> getType3Keyword() {
        return Optional.ofNullable(type3Keyword).orElse(new ArrayList<>());
    }

    public List<String> getType4Keyword() {
        return Optional.ofNullable(type4Keyword).orElse(new ArrayList<>());
    }

    public List<String> getType5Keyword() {
        return Optional.ofNullable(type5Keyword).orElse(new ArrayList<>());
    }

    public List<String> getType6Keyword() {
        return Optional.ofNullable(type6Keyword).orElse(new ArrayList<>());
    }
}
