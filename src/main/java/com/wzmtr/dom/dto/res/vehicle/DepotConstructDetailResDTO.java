package com.wzmtr.dom.dto.res.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * description:
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/13 13:38
 */
@Data
public class DepotConstructDetailResDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 车场编码
     */
    @ApiModelProperty(value = "车场编码")
    private String depotCode;

    /**
     * 计划施工数
     */
    @ApiModelProperty(value = "计划施工数")
    private Integer planConstruct;

    /**
     * 实际施工数
     */
    @ApiModelProperty(value = "实际施工数")
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
     * 洗车列数
     * */
    private Integer cleanCount;

    /**
     * 施工情况分析
     * */
    private String remark;

    /**
     * 版本号
     * */
    @ApiModelProperty(value = "版本号")
    private String version;

    /**
     * 数据类型:1:日报,2周报,3月报
     * */
    @ApiModelProperty(value = "数据类型:1:日报,2周报,3月报")
    private String dataType;

    /**
     * 数据起始日期
     * */
    @ApiModelProperty(value = "数据起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startDate;

    /**
     * 数据终止日期
     * */
    @ApiModelProperty(value = "数据终止日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endDate;

    /**
     * 数据所属日期
     * */
    @ApiModelProperty(value = "数据所属日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date dataDate;

}
