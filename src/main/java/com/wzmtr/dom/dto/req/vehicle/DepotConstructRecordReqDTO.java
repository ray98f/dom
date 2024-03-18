package com.wzmtr.dom.dto.req.vehicle;

import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 日数据请求类
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepotConstructRecordReqDTO extends BaseEntity {

    /**
     * id
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.modify.class)
    private String id;

    /**
     * 数据类型:1日报 2月报 3周报
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String dataType;

    /**
     * 数据所属日期
     * */
    private String dataDate;

    /**
     * 数据起始日期
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String startDate;

    /**
     * 数据终止日期
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String endDate;

    /**
     * 停车场编码
     * */
    @NotNull(message = "32000006",groups = ValidationGroup.create.class)
    private String depotCode;


    /**
     * 计划施工数
     * */
    private Integer planConstruct;

    /**
     * 实际施工数
     * */
    private Integer realConstruct;

    /**
     * A1计划数
     * */
    private Integer a1Plan;

    /**
     * B计划数
     * */
    private Integer bPlan;

    /**
     * 日补充计划数
     * */
    private Integer daySupPlan;

    /**
     * 临时计划数
     * */
    private Integer tempPlan;

    /**
     * A1类完成数
     * */
    private Integer a1Complete;

    /**
     * B类完成数
     * */
    private Integer bComplete;

    /**
     * 日补充类完成数
     * */
    private Integer daySupComplete;

    /**
     * 临时类完成数
     * */
    private Integer tempComplete;

    /**
     * 单股道停送电次数
     * */
    private Integer powerSupply;

    /**
     * 调车件数
     * */
    private Integer shuntCount;

    /**
     * 调车钩数
     * */
    private Integer shuntHook;

    /**
     * 调车时间(分钟)
     * */
    private Integer shuntTime;

    /**
     * 出入检查库次数
     * */
    private Integer inOutStorage;

    /**
     * 施工情况分析
     * */
    private String remark;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    @NotNull(message = "32000006",groups = ValidationGroup.modify.class)
    private String version;
}
